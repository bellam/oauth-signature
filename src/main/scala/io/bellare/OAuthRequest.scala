package io.bellare

case class OAuthRequest(
    http_method: String,
    http_url: String,
    query_parameters: Map[String, String]
)
