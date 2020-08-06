package io.bellare

import io.bellare.OAuthHelper

/**
  * Configure OAuth credentials
  *
  * secondary params have default values AND
  * can be overriden only in tests
  *
  * @param oauth_consumer_key
  * @param oauth_consumer_secret
  * @param oauth_token
  * @param oauth_token_secret
  */
case class OAuthConfig(
    /* ... configurable values during instantiation ... */
    oauth_consumer_key: String,
    oauth_consumer_secret: String,
    oauth_token: String,
    oauth_token_secret: String,
    /* ... default values - need not be passed ... */
    oauth_nonce: String = OAuthHelper.randomString(32),
    oauth_timestamp: String = OAuthHelper.now,
    oauth_signature_method: String = "HMAC-SHA1",
    oauth_version: String = "1.0"
)
