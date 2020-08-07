# OAuth-Signature

[![Build Status](https://travis-ci.org/bellam/oauth-signature.svg?branch=master)](https://travis-ci.org/bellam/oauth-signature)

oauth-signature is a lightweight Scala library to generate OAuth1.0 signature using HMAC-SHA algorithm. Works well for Twitter and Flickr APIs that need OAuth1.0 authentication.

## Step 1: Configure OAuthConfig object with four OAuth parameters

1. Consumer Key
2. Consumer Secret
3. Access Token
4. Access Token Secret

```scala
val config = OAuthConfig(
        oauth_consumer_key = env("TWITTER_CONSUMER_KEY"),
        oauth_consumer_secret = env("TWITTER_CONSUMER_SECRET"),
        oauth_token = env("TWITTER_ACCESS_TOKEN"),
        oauth_token_secret = env("TWITTER_ACCESS_TOKEN_SECRET")
      )
```

## Step 2: Configure OAuthRequest object with request parameters

1. HTTP method (GET, POST, etc.)
2. HTTP URL
3. Query parameters to the request (pass an empty Map incase of no parameters)

```scala
val request = OAuthRequest(
        http_method = "GET",
        http_url = "https://api.twitter.com/1.1/search/tweets.json",
        query_parameters = Map("q" -> "#BlackLivesMatter")
      )
```

## Step 3: Build OAuthSignature object with the config and request objects

```scala
    val signature = OAuthSignature(config, request)
```

## Generate oauth_signature OR entire authorization header to be passed in the authentication request

```scala
scala> signature.getSignature
res14: String = "bbr4AkCDTrvw6FG0h605oHtX8tM="

scala> signature.getSignedAuthorizationHeader
res15: String = "OAuth oauth_consumer_key=\"1234\", oauth_nonce=\"1234\", oauth_signature=\"bbr4AkCDTrvw6FG0h605oHtX8tM%3D\", oauth_signature_method=\"HMAC-SHA1\", oauth_timestamp=\"1234\", oauth_token=\"1234\", oauth_version=\"1.0\""
```

This library has been very useful for me with some ammonite scripts that I wrote to analyse twitter hashtags via Twitter Standard API.

Thank you.
