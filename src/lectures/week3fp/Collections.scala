package lectures.week3fp

object Collections extends App {

  {
    val emptySet: Set[Int] = Set()
    val aSet = Set(10, 20, 30, 40)
    val anotherSet = Set(30, 40, 50, 60)

    println(aSet.intersect(anotherSet))
    println(aSet.&(anotherSet))

    println(aSet.++(anotherSet))
  }

  {
    val aSequence = Seq(1, 3, 2, 4)
    println(aSequence)

    aSequence.length

    aSequence ++ Seq(5, 6, 7)

    aSequence.reverse
    aSequence.sorted

    aSequence(1)

    aSequence.search(3)
  }

  {
    val aMap: Map[String, Int] = Map()

    val colors: Map[String, String] = Map(("black", "#000000"), "blue" -> "0d1ad1", ("Blue", "#161f96")).withDefaultValue("na")

    println(colors)

    println(colors.contains("black"))
    println(colors("black"))

    val color: (String, String) = "green" -> "#339616"
    val newColors: Map[String, String] = colors + color

    println(newColors)

    println(colors.toList)
    println(List(("white", "#000000")).toMap)
  }

  {
    val anArray: Array[String] = Array("h", "e", "l", "l", "o", ".")

    anArray(5) = "!"

    println(anArray.mkString("-"))

    val twoElements = Array.ofDim[Boolean](2)
    twoElements.foreach(println)

    val numberSeq: Seq[String] = anArray
    println(numberSeq)
  }

  {
    val aTuple = (100, "Scala")
    val sameTuple = Tuple2(100, "Scala")

    println(aTuple)

    aTuple._1
    aTuple._2

    val copy = aTuple.copy(_2 = "Python")

    aTuple.swap
  }

  {
    val aRange = 1 until 3

    aRange.foreach(println)

    (1 to 3).foreach(x => println("Hello"))

    val aRangeToVector = (1 to 5).toVector
    println((aRangeToVector))
  }

  {
    val fruits = List("apple", "banana")

    val mapResult: Seq[String] = fruits.map(_.toUpperCase())
    val flatResult: Seq[Char] = fruits.flatMap(_.toUpperCase())

    println(mapResult)
    println(flatResult)

    val s = "Hello"
    val newStr: String = s.map(c => c + ".").mkString
    println(newStr)

    val list1 = List(1, 2)
    val list2 = List("a", "b")

    val combinations = list1.filter(_ > 1).flatMap(n => list2.map(c => c + n))
    println(combinations)

    val forCombination = for {
      n <- list1 if n > 1
      c <- list2
    } yield c + n

    println(forCombination)
  }

  {
    val progLanguages = List("java", "scala", "python")

    val out = progLanguages.map(lang => (lang, lang.length))
    println(out)

    val out2 = for (lang <- progLanguages) yield (lang, lang.length)
    println(out2)

    val out3 = for {
      lang <- progLanguages
    } yield (lang, lang.length)
    println(out3)
  }

  {
    val progLanguages = List("java", "scala", "python")
    val lngAbbrev = List("JA", "SCA", "PY")

    val out = for {
      lng <- progLanguages
      abrv <- lngAbbrev if lng.toUpperCase().startsWith(abrv.toUpperCase)
    } yield (abrv, lng)
    println(out)

  }
  {
    val sampleTuple = Tuple2(2, "Hello, World")
    println(sampleTuple.copy(_2 = "Scala").swap._1 + 5)
  }
}
