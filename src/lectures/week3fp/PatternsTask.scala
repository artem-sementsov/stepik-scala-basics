package lectures.week3fp

import scala.io.StdIn.readLine
import scala.util.{Success, Try}

object PatternsTask extends App {
  val rawInput: String = readLine()
  val input = Try(rawInput.split(" ").toList)

  val person = input match {
    case Success(List(name, surname)) => Person(name, surname)
    case _ => "Oops, something is wrong"
  }

  person match {
    case Person(name) => println(name)
    case _ => println("Oops, something is wrong")
  }
}

object Person {
  def apply(name: String, surname: String): (String, String) = (name, surname)

  def unapply(tuple2: (String, String)): Option[String] = {
    tuple2 match {
      case (name, surname) => Some(s"${name(0)}. ${surname(0)}.")
      case _ => None
    }
  }
}