import javax.servlet.ServletContext

import oauth.servlet.RootServlet
import org.scalatra._

class ScalatraBootstrap extends LifeCycle {

  override def init(context: ServletContext) {
    context.mount(new RootServlet, "/service/*")
  }

}
