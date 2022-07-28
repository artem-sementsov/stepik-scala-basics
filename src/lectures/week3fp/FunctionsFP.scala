package lectures.week3fp

import scala.annotation.tailrec

object FunctionsFP extends App {
  val res = new (Int => Int) {
    override def apply(v1: Int): Int = v1 * 2
  }

  println(res(2))

  val product = new ((Int, Int) => Int) {
    override def apply(v1: Int, v2: Int): Int = v1 * v2
  }

  val product2 = (x: Int, y: Int) => x * y
  val product3: (Int, Int) => Int = (x, y) => x * y

  val res2: Int => Int = x => x * 2

  val product4 = (x: Int, y: Int) => if (x > y) x else y

  val len = (_: String).length
  val len2 = (s: String) => (s1: String) => s + s1

  println(len2("1"))
  println(len2("1")("2"))

  println(len("Hello, world!"))

  @tailrec
  def nTimes(f: Int => Int, x: Int, n: Int): Int = {
    if (n <= 0) x
    else nTimes(f, f(x), n - 1)
  }

  val increment = (x: Int) => x + 1
  println(nTimes(increment, 0, 3)) // выведет 3

  def add(x: Int): Int => Int = (y: Int) => x + y

  println(add(1)(2)) // 3

  def curryingNTimes(f: Int => Int, n: Int): Int => Int = {
    if (n == 0) (x: Int) => x
    else (x: Int) => curryingNTimes(f, n - 1)(f(x))
  }

  val inc = (x: Int) => x + 1

  println(curryingNTimes((x: Int) => x + 1, 3)(0))

  {
    val f: Int => Int => Int = (x: Int) => (y: Int) => x + y
    def someFunc0: Int => (Int => Int) = (x) => (y) => x + y
    def someFunc: Int => (Int => Int) = (x: Int) => (y: Int) => x + y

    def someFunc2: Int => ((Int) => Int) = (x: Int) => (y: Int) => x + y
    def someFunc3 = (x: Int) => (y: Int) => x + y
    println(someFunc(1))
    println(someFunc(1)(2))
  }
  {
    def someFunc: Int => Function1[Int, Int] = new Function1[Int, Function1[Int, Int]] {
      override def apply(x: Int): Function1[Int, Int] = new Function1[Int, Int] {
        override def apply(y: Int): Int = x + y
      }
    }

    val res = someFunc(1)

    println(res)
    println(res(4))
    println(someFunc(3)(4))
  }
}
