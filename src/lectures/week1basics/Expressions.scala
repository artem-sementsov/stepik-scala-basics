package lectures.week1basics

object Expressions extends App {
  val expr = if (true) "True" else "False"
  println(expr)

  val x = print("")
  val y = println("")

  println(x.getClass, y.getClass)

  val someVal = print("It is just a value")
  print(someVal)
}
