import org.scalatest.flatspec.AnyFlatSpec
import io.bellare.OAuthHelper

class OAuthHelperSpec extends AnyFlatSpec {

  "hmac algorithm" should "generate correct hashed value" in {
    assert(OAuthHelper.hmac("1234", "5678") == "+Lo7YRIktiicR1nXTzfLD6TnjQo=")
  }

  "encode function" should "generate correct encoded value" in {
    assert(
      OAuthHelper.encode(
        "https://api.twitter.com/1.1/search/tweets.json?q=#Black Lives Matter"
      ) == "https%3A%2F%2Fapi.twitter.com%2F1.1%2Fsearch%2Ftweets.json%3Fq%3D%23Black%20Lives%20Matter"
    )
  }

  "generateOAuthHeader function" should "generate the correct OAuth header to be passed in the request" in {
    val params: Map[String, Any] =
      Map("q" -> "#one", "oauth_signature" -> "two=/")
    assert(
      OAuthHelper.generateOAuthHeader(
        params
      ) == """OAuth oauth_signature="two%3D%2F", q="%23one""""
    )
  }
}
