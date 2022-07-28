package exercises

object FPPractice extends App {
  val config: Map[String, String] = Map()

  val host: Option[String] = config.get("host")
  val port: Option[String] = config.get("port")
  val connection: Option[Connection] = host.flatMap(host => port.flatMap(port => Connection(host, port)))
  val filtered: Option[String] = connection.map(connection => connection.connect)
  filtered.foreach(println)

  val forConnectionStatus = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect

  forConnectionStatus.foreach(println)
}

class Connection {
  def connect: String = "Connected"
}

object Connection {
  def apply(host: String, port: String): Option[Connection] = Some(new Connection)
}