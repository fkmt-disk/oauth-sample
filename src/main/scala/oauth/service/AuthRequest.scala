package oauth.service

import java.security.SecureRandom

import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl
import oauth.service.OAuthMetadata._

import scala.collection.JavaConverters._

case class AuthRequest(url: String, state: String, nonce: String)

object AuthRequest {

  val scope = Seq("openid", "email").asJava

  def apply(redirect: String): AuthRequest = {
    val state = getRandom
    val nonce = getRandom

    val url = new AuthorizationCodeRequestUrl(AuthEndPoint, ClientId)
      .setRedirectUri(redirect)
      .setScopes(scope)
      .setState(state)
      .set("nonce", nonce).build

    new AuthRequest(url, state, nonce)
  }

  // 130はGoogleのサンプルコードからコピペ
  def getRandom = BigInt(130, new SecureRandom).toString(Character.MAX_RADIX)

}
