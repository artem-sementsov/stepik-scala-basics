package lectures.week1basics

import scala.annotation.tailrec

object Recursion extends App {
  var i = 0
  val whileVal = while (i < 3) {
    println("hello")
    i += 1
  }

  def factorial(n: Int): Int = {
    if (n <= 0) 1
    else n * factorial(n - 1)
  }

  println(factorial(3))

  def factorialWithTailRecursion(n: Int): Int = {
    def loop(x: Int, accumulator: Int): Int = {
      if (x <= 1) accumulator
      else loop(x - 1, x * accumulator)
    }

    loop(n, 1)
  }

  println(factorialWithTailRecursion(3))

  def fibtail(n: Int): Int = {
    if (n < 2) 1
    else {

      @tailrec
      def loop(n: Int, a: Int, b: Int): Int = {
        if (n <= 2) a + b
        else loop(n - 1, b, a + b)
      }

      loop(n, 1, 1)
    }
  }

  for (i <- 0 to 10) {
    println(fibtail(i))
  }

  def powerOfTwo(n: Int): BigInt = {

    @tailrec
    def loop(n: Int, acc: BigInt): BigInt = {
      if (n <= 0) acc
      else loop(n - 1, 2 * acc)
    }

    loop(n, 1)
  }

  assert(powerOfTwo(0) == 1)
  assert(powerOfTwo(1) == 2)
  assert(powerOfTwo(2) == 4)

  val fArgs = List(1, 1, 1)
  val x = fArgs(0)
  val y = fArgs(1)
  val n = fArgs(2)

  def inc(x: Int, y: Int, n: Int): Unit = {

    @tailrec
    def loop(acc: Int, y: Int, n: Int): Int = {
      if (n == 0) acc
      else loop(acc + y, y, n - 1)
    }

    val res = loop(x, y, n)
    for (i <- 1 to res.toString.length) {
      print(s"$res ")
    }
    println("is the result")
  }

  inc(x, y, n)

  val input = "I like     Scala"

  println(input.split("\\s+").reverse.mkString(" "))
}
