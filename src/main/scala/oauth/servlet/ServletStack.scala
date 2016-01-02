package oauth.servlet

import org.json4s.{DefaultFormats, Formats}
import org.scalatra._
import org.scalatra.json.JacksonJsonSupport

trait ServletStack extends ScalatraServlet with JacksonJsonSupport {

  override def jsonpCallbackParameterNames = Some("callback")

  protected implicit lazy val jsonFormats: Formats = DefaultFormats

  before() {
    contentType = formats("json")
  }

  notFound {
    halt(404)
  }

}
