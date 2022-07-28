package exercises

object FPPractice3 extends App {

}

object AviaCompany {

  import scala.collection.immutable.Queue
  import scala.annotation.tailrec

  type Location = String
  type Network = Map[Location, Set[Location]]

  def add(network: Network, location: Location): Network = {
    network + (location -> Set())
  }

  def remove(network: Network, location: Location): Map[Location, Set[Location]] = {
    @tailrec
    def loop(destinations: Set[Location], acc: Network): Network =
      if (destinations.isEmpty) acc
      else loop(destinations.tail, disconnect(acc, location, destinations.head))

    val disconnected = loop(network(location), network)

    disconnected - location
  }

  def connect(network: Network, pointA: Location, pointB: Location): Network = {
    val routesForA: Set[Location] = network(pointA)
    val routesForB: Set[Location] = network(pointB)
    network + (pointA -> (routesForA + pointB)) + (pointB -> (routesForB + pointA))
  }

  def disconnect(network: Network, pointA: Location, pointB: String): Network = {
    val routesForA: Set[Location] = network(pointA)
    val routesForB: Set[Location] = network(pointB)

    network + (pointA -> (routesForA - pointB)) + (pointB -> (routesForB - pointA))
  }

  def nFlights(network: Network, location: Location): Int = network(location).size

  def mostFlights(network: Network): Location = {
    network.maxBy(pair => pair._2.size)._1
  }

  def nLocationsWithNoFlights(network: Network): Int = {
    network.count(_._2.isEmpty)
  }


  def isConnected(network: Network, pointA: Location, pointB: Location): Boolean = {
    @tailrec
    def loop(q: Queue[Location], visited: Set[Location]): Boolean = {
      if (q.isEmpty) {
        false
      } else {
        val (head, tail) = q.dequeue
        if (head == pointB) true
        else {
          val notVisited: Set[Location] = network(head).diff(visited)
          loop(tail ++ notVisited, visited + head)
        }
      }
    }

    val destinationsA: Set[Location] = network(pointA)
    loop(Queue.empty ++ destinationsA, Set(pointA))
  }
}

object Tests extends App {

  import exercises.AviaCompany.{disconnect, isConnected}

  {
    val network: Map[String, Set[String]] = Map("A" -> Set("B"), "B" -> Set("A", "C"), "C" -> Set("B"), "D" -> Set("F"), "F" -> Set("D"))

    assert(!isConnected(network, "A", "D"))
    assert(isConnected(network, "A", "C"))
  }

  {
    val network = Map("A" -> Set("B"), "B" -> Set("A"))
    val res = disconnect(network, "A", "B")
    assert(Map("A" -> Set(), "B" -> Set()) == res)
  }
}
