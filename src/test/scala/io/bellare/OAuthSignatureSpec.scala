import org.scalatest.flatspec.AnyFlatSpec
import io.bellare.OAuthSignature
import io.bellare.OAuthConfig
import io.bellare.OAuthRequest

class OAuthSignatureSpec extends AnyFlatSpec {

  "Generated oauth_signature no parameters - GET Request" should "be 4eNPFqUFQROv3ITc5Fv9/5J+AV0=" in {
    val s = OAuthSignature(
      OAuthConfig(
        oauth_consumer_key = "1234",
        oauth_consumer_secret = "1234",
        oauth_token = "1234",
        oauth_token_secret = "1234",
        oauth_nonce = "1234",
        oauth_timestamp = "1234"
      ),
      OAuthRequest(
        http_method = "GET",
        http_url = "1234",
        query_parameters = Map()
      )
    )

    val signature = s.getSignature
    val signedAuthHeader = s.getSignedAuthorizationHeader

    assert(signature.equals("p+/Ao+ZGt8uaTKqGXwP0YozsvR8="))
    assert(
      signedAuthHeader.equals(
        """OAuth oauth_consumer_key="1234", oauth_nonce="1234", oauth_signature="p%2B%2FAo%2BZGt8uaTKqGXwP0YozsvR8%3D", oauth_signature_method="HMAC-SHA1", oauth_timestamp="1234", oauth_token="1234", oauth_version="1.0""""
      )
    )
  }

  "Generated oauth_signature no parameters - POST Request" should "be bbr4AkCDTrvw6FG0h605oHtX8tM=" in {
    val s = OAuthSignature(
      OAuthConfig(
        oauth_consumer_key = "1234",
        oauth_consumer_secret = "1234",
        oauth_token = "1234",
        oauth_token_secret = "1234",
        oauth_nonce = "1234",
        oauth_timestamp = "1234"
      ),
      OAuthRequest(
        http_method = "POST",
        http_url = "1234",
        query_parameters = Map()
      )
    )

    val signature = s.getSignature
    val signedAuthHeader = s.getSignedAuthorizationHeader
    assert(signature.equals("bbr4AkCDTrvw6FG0h605oHtX8tM="))
    assert(
      signedAuthHeader.equals(
        """OAuth oauth_consumer_key="1234", oauth_nonce="1234", oauth_signature="bbr4AkCDTrvw6FG0h605oHtX8tM%3D", oauth_signature_method="HMAC-SHA1", oauth_timestamp="1234", oauth_token="1234", oauth_version="1.0""""
      )
    )
  }

  "Generated oauth_signature - query parameter 'q' and value 'RedArmy' - POST Request" should "be QL+6AW8JEv0OBZO77kHlsRpsmcE=" in {
    val s = OAuthSignature(
      OAuthConfig(
        oauth_consumer_key = "1234",
        oauth_consumer_secret = "1234",
        oauth_token = "1234",
        oauth_token_secret = "1234",
        oauth_nonce = "1234",
        oauth_timestamp = "1234"
      ),
      OAuthRequest(
        http_method = "POST",
        http_url = "1234",
        query_parameters = Map("q" -> "#RedArmy")
      )
    )
    val signature = s.getSignature
    val signedAuthHeader = s.getSignedAuthorizationHeader

    assert(signature.equals("QL+6AW8JEv0OBZO77kHlsRpsmcE="))
    assert(
      signedAuthHeader.equals(
        """OAuth oauth_consumer_key="1234", oauth_nonce="1234", oauth_signature="QL%2B6AW8JEv0OBZO77kHlsRpsmcE%3D", oauth_signature_method="HMAC-SHA1", oauth_timestamp="1234", oauth_token="1234", oauth_version="1.0""""
      )
    )
  }
}
