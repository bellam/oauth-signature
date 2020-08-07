package io.bellare

import java.util.Base64
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

/**
  * Helper functions to generate oauth_signature
  */
object OAuthHelper {

  /**
    * Generate HMAC value
    *
    * @param str - parameterized string to be signed
    * @param signatureKey - the signature key
    * @return encrypted value hashed with signatureKey using HMAC-SHA algorithm
    */
  def hmac(str: String, signatureKey: String): String = {
    val mac = Mac.getInstance("HmacSHA1")
    mac.init(new SecretKeySpec(signatureKey.getBytes, "HmacSHA1"))

    Base64
      .getEncoder()
      .encodeToString(
        mac.doFinal(str.getBytes)
      )
  }

  /**
    * "Percent Encodes" a string. Primarily used as a URL encoder.
    *
    * @return A percent-encoded string
    */
  def encode: String => String =
    s => java.net.URLEncoder.encode(s, "UTF-8").replace("+", "%20")

  /**
    * Generates a map from a case class. supports Scala ver <2.13
    * Thanks to contributors on https://stackoverflow.com/a/1227643/1266051
    *
    * @param cc the case class
    * @return - returns a map
    *           where <key = parameter name, value = value of the parameter in case class object>
    */
  def getCCParams(cc: AnyRef): Map[String, Any] =
    cc.getClass.getDeclaredFields.foldLeft(Map.empty[String, Any]) { (a, f) =>
      f.setAccessible(true)
      a + (f.getName -> f.get(cc))
    }

  /**
    * Generates a random alphanumeric string of length "n"
    *
    * @param n number of characters
    * @return the generated nonce
    */
  def randomString(n: Int): String =
    scala.util.Random.alphanumeric.take(n).mkString

  /**
    * Generate OAuth authorization header from parameter map
    *
    * @return generated oauth header
    */
  def generateOAuthHeader: Map[String, Any] => String =
    ("OAuth ") +
      _.map(e => s"""${e._1}="${encode(e._2.toString)}"""").toList.sorted
        .reduce((x, y) => x + ", " + y)

  /**
    * Generate timestamp as String
    *
    * @return current timestamp in string
    */
  def now: String =
    Math.floor(System.currentTimeMillis() / 1000).toInt.toString

  /**
    * Get a function to read environment variable
    *
    * @return function to get environment variable
    */
  def env: String => String =
    (environmentVariable) => System.getenv().get(environmentVariable)
}
