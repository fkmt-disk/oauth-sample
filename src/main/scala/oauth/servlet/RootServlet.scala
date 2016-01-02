package oauth.servlet

import oauth.service.TokenRequest._
import oauth.service.{UserProfile, AuthRequest, TokenRequest}

class RootServlet extends ServletStack {

  get("/oauth2url") {
    params.get("redirect").map(oauth2url).getOrElse(halt(400, "params.redirect is require"))
  }

  private def oauth2url(redirect: String) = {
    session.invalidate()

    val auth = AuthRequest(redirect)

    // あとで確認するのでとっておく
    session.setAttribute("state", auth.state)
    session.setAttribute("nonce", auth.nonce)

    Map("content" -> auth.url)
  }

  get("/login") {
    (params.get("state"), params.get("code"), params.get("redirect")) match {
      case (None, _, _) =>
        halt(400, "params.state is required")

      case (_, None, _) =>
        halt(400, "params.code is required")

      case (_, _, None) =>
        halt(400, "params.redirect is required")

      case (Some(state), _, _) if session.as[String]("state") != state =>
        halt(403, "state is unmatched")

      case (_, Some(code), Some(redirect)) =>
        TokenRequest.send(code, redirect)
          .getPayloadWithVerify(session.as[String]("nonce"))
          .map(_.getEmail)
          .fold(l => halt(403, l), r => login(r))
    }
  }

  private def login(email: String) = {
    val profile = UserProfile(email)

    session.invalidate()

    session.setAttribute("profile", profile)

    Map("content" -> profile)
  }

}
