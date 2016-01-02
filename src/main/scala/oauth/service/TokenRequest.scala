package oauth.service

import com.google.api.client.googleapis.auth.oauth2.{GoogleAuthorizationCodeTokenRequest => GTokenRequest, GoogleIdToken, GoogleIdTokenVerifier}
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import oauth.service.OAuthMetadata._

import scala.language.implicitConversions

import scalaz.Scalaz._

object TokenRequest {

  private val transport = new NetHttpTransport
  private val jackson = new JacksonFactory
  private val verifier = new GoogleIdTokenVerifier(transport, jackson)

  def send(code: String, redirect: String) =
    new GTokenRequest(transport, jackson, TokenEndPoint, ClientId, ClientSecret, code, redirect).execute.parseIdToken


  implicit class GoogleIdTokenImplicits(token: GoogleIdToken) {

    def getPayloadWithVerify(nonce: String) = {
      if (!verifier.verify(token))
        "invalid token".left
      else if (token.getPayload.getNonce != nonce)
        "nonce unmatched".left
      else
        token.getPayload.right
    }

  }
}

