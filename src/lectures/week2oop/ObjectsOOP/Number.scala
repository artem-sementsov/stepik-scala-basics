package lectures.week2oop.ObjectsOOP

class Number(val num: Int)

object Number {
  val Pi = 3.14

  def apply(x: Number, y: Number): Number = {
    new Number(x.num + y.num)
  }
}

object run extends App {
  val a = new Number(1)
  val b = new Number(2)
  val c = Number(a, b)

  println(a.num)
  println(b.num)
  println(c.num)
}
