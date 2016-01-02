package oauth.service

import com.typesafe.config.ConfigFactory

object OAuthMetadata {

  // endpointのurlは
  // https://accounts.google.com/.well-known/openid-configuration
  // に書いてある

  val AuthEndPoint = "https://accounts.google.com/o/oauth2/v2/auth"

  val TokenEndPoint = "https://www.googleapis.com/oauth2/v4/token"

  //private val conf = ConfigFactory.load("application.gitignore.conf").getConfig("oauth")
  private val conf = ConfigFactory.load("application.conf").getConfig("oauth")

  private[oauth] val ClientId = conf.getString("clientId")

  private[oauth] val ClientSecret = conf.getString("clientSecret")

}
