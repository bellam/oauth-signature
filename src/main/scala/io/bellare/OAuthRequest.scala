package io.bellare

/**
  * Defines structure of the request
  *
  * @param http_method - the HTTP method - POST, GET etc
  * @param http_url - the Request URL
  * @param query_parameters - the query parameters to be sent in the URL
  */
case class OAuthRequest(
    http_method: String,
    http_url: String,
    query_parameters: Map[String, String]
)
