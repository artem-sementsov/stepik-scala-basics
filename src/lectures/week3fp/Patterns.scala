package lectures.week3fp

object Patterns extends App {
  {
    val someVal = 3

    val description = someVal match {
      case 1 => "action 1"
      case 2 => "action 2"
      case 3 => "action three"
      case _ => "default action"
    }

    println(description) // action three

    val x: Any = "Scala"
    val constants = x match {
      case 1 => "число"
      case "Scala" => "строка"
      case true => "булевое значение"
    }

    println(constants)


    val aTuple = (5, 2)
    val matchATuple = aTuple match {
      case (1, 1) => "полное совпадение"
      case (something, 2) => s"я нашел $something"
    }

    println(matchATuple) // я нашел 5

    val nestedTuple = (1, (2, 3))
    val matchNestedTuple = nestedTuple match {
      case (_, (2, v)) => s"тут есть число $v"
    }

    println(matchNestedTuple) // тут есть число 3

    val aStandardList = List(5, 4)
    val listMatching = aStandardList match {
      case List(1, _, _, _) => "список из 4 элементов, где первый элемент равен 1"
      case List(2, _*) => "список произвольной длины, где первый элемент равен 2"
      case List(3, 2, 1) :+ 0 => "список List(3, 2, 1, 0)"
      case 5 :: List(_) => "список, где первым идет число 5 и еще один элемент"
      case _ => "default"
    }

    val unknown: Any = List(1, 2)
    val typeMatch = unknown match {
      case list: List[Int] => "list of INTs"
      case _ => "default"
    }

    println(typeMatch) // list of INTs

    case class Person(name: String, age: Int)

    val bob = Person("Bob", 30)
    val greeting = bob match {
      case Person(n, a) if a < 18 => s"I'm $n and I'm under 18"
      case Person(n, a) if n != "name" => s"I'm $n and I am $a years old"
      case _ => "I have no name"
    }

    println(greeting) // I'm Bob and I am 30 years old

    val nameBindingMatch = List(6, 2) match {
      case nonEmptyList@List(1, _, _, _) => s"нашли $nonEmptyList"
      case someList@List(6, _*) => s"нашли список $someList"
    }

    println(nameBindingMatch) // нашли список List(6, 2)

    val numbers = List(1, 2, 3)
    val numbersMatch = numbers match {
      case listOfStrings: List[String] => "a list of strings"
      case listOfNumbers: List[Int] => "a list of integers"
      case _ => "default case"
    }

    println(numbersMatch)

    for (i <- 1 to 5) {
      i match {
        case i if i % 2 == 0 => println(s"$i - четное число")
        case i if i % 2 == 1 => println(s"$i - нечетное число")
      }
    }

    val l = List(1, 2)
    val lMatch = l match {
      case List(_, _*) => "matched"
      case _ => "not matched"
    }
    println(lMatch)
  }

  {
    def guard(data: Any, maxLength: Int): String = {
      data match {
        case list: List[_] if list.length <= maxLength => "Задан список List допустимой длины"
        case list: List[_] => "Длина списка больше максимально допустимого значения"
        case str: String if str.length <= maxLength => "Длина строки не превышает максимально допустимого значения"
        case str: String => "Получена строка недопустимой длины"
        case _ => "Что это? Это не строка и не список"
      }
    }

    println(guard("Hello", 5))
  }
}
