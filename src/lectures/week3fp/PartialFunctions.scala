package lectures.week3fp

import scala.annotation.tailrec

object PartialFunctions extends App {
  {
    val whatToDo = (d: String) => d match {
      case "mon" => "Work!"
      case "fri" => "Party Time"
      case "sun" => "Relax a little"
    }

    println(whatToDo("mon"))
    //    println(whatToDo("sat"))

    val aPartialFunction: PartialFunction[String, String] = {
      case "mon" => "Work!"
      case "fri" => "Party Time"
      case "sun" => "Relax a little"
    }

    println(aPartialFunction.isDefinedAt("tue")) // false

    println(aPartialFunction("sun")) // Relax a little
    //    println(aPartialFunction("sat")) // MatchError
  }
  {
    val aPartialFunction: PartialFunction[String, String] = {
      case "mon" => "Work!"
      case "fri" => "Party Time"
      case "sun" => "Relax a little"
    }


    val lifted = aPartialFunction.lift // теперь на выходе имеем тип Option[String]

    println(lifted("fri")) // Some(Party Time)
    println(lifted("thu")) // None
  }
  {
    val bot: PartialFunction[String, String] = {
      case "hello" => "Hi, I'm bot"
      case "bye" => "Bye-bye"
      case "what's up" => "sup-sup"
    }

    val chatbot: String => Option[String] = bot.lift

    println(chatbot("hello"))
    println(chatbot("bye"))
    println(chatbot("what's up"))
    println(chatbot("wrong input"))

    List("what's up", "hi").foreach(line => println(chatbot(line)))

    //    scala.io.Source.stdin.getLines().foreach(line => println(chatbot(line)))

    //    scala.io.Source.stdin.getLines().map(chatbot).foreach(println)
  }

  {
    val input: String = "9-876-543-21-09"

    def countNumbers(s: String): Map[Char, Int] = {
      s.groupBy[Char](identity).view.mapValues(_.length).toMap - '-'
    }

    println(countNumbers(input))
  }
  {
    def compare(v1: String, v2: String): Int = {
      val vs: List[List[String]] = List(v1, v2).map(_.split("\\.").toList)
      val res: List[(String, String)] = vs(0).zipAll(vs(1), "0", "0").filter(x => x._1 != x._2)

      if (res.isEmpty || res(0)._1.toInt == res(0)._2.toInt) 0 else if (res(0)._1.toInt < res(0)._2.toInt) -1 else 1
    }

    assert(-1 == compare("0.9", "1.0"))
    assert(-1 == compare("1.0.2.03", "1.1.0"))
    assert(0 == compare("2.1", "2.01"))
    assert(0 == compare("3.0", "3.0.0.0"))
    assert(-1 == compare("4", "4.0.0.1"))
    assert(1 == compare("4.0.1", "4.0.0.1"))
  }
}
