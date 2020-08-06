package io.bellare

import OAuthHelper._

/**
  * Companion object - returns instance of OAuthSignature
  * also converts OAuthConfig case class into map and sets it in "oauthConfigAsMap"
  */
object OAuthSignature {
  def apply(config: OAuthConfig, request: OAuthRequest): OAuthSignature = {
    var o = new OAuthSignature
    o.config = config
    o.request = request
    o.oauthConfigAsMap = OAuthHelper
      .getCCParams(config)
      .filter(e => !e._1.contains("secret"))
    o
  }
}

/**
  * OAuthSignature consists of methods to create OAuth signature and authorization header.
  */
class OAuthSignature {

  var config: OAuthConfig = OAuthConfig("", "", "", "")
  var request: OAuthRequest = OAuthRequest("", "", Map())

  var oauthConfigAsMap: Map[String, Any] = Map()

  /**
    * Generate the oauth_signature
    * @return The generates oauth_signature
    */
  def getSignature: String = {
    // get encoder function
    val e: String => String = OAuthHelper.encode

    // 1. construct signature map along with query parameters
    val oauthSignature: Map[String, Any] =
      oauthConfigAsMap ++ request.query_parameters

    // 2. generate parameter string
    val parameterString: String =
      oauthSignature
        .map(e => e._1 + "=" + encode(e._2.toString))
        .toList
        .sorted
        .reduce((x, y) => x + "&" + y)

    // 3. generate signature base string
    val signatureBaseString: String =
      s"${e(request.http_method.toUpperCase)}&${e(request.http_url)}&${e(parameterString)}"

    // 4. generate signing key
    val signatureKey: String =
      s"${e(config.oauth_consumer_secret)}&${e(config.oauth_token_secret)}"

    // 5. return generated oauth_signature
    OAuthHelper.hmac(signatureBaseString, signatureKey)
  }

  /**
    * Generate OAuth1.0 authorization header
    * @return
    */
  def getSignedAuthorizationHeader: String = {
    // 1. oauth_signature
    val oauth_signature: String = getSignature

    // 2. Append signature to auth header parameters
    val authorizationHeaderParams =
      oauthConfigAsMap ++ Map("oauth_signature" -> oauth_signature)

    // 3. build authorization header to be sent in the request
    generateOAuthHeader(authorizationHeaderParams)
  }
}
