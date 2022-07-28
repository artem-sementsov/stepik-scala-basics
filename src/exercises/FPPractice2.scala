package exercises

import scala.util.Try

class FPPractice2 extends App {
  val host = ""
  val port = ""

  def render(page: String): Unit = ???

  def safeConnection(host: String, port: String): Try[Connection] = {
    Try(HttpService(host, port))
  }

  val html = for {
    connection <- safeConnection(host, port)
    html <- Try(connection.get(""))
  } yield html

  html.foreach(render)

  class Connection {
    def get(url: String): String = {
      throw new RuntimeException("Your Connection was Interrupted")
    }
  }
  class HttpService

  object HttpService {
    def apply(host: String, port: String): Connection = ???
  }

}


