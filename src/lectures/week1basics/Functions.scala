package lectures.week1basics

object Functions extends App {
  def aPerson(name: String, surname: String): String = {
    s"$name $surname"
  }

  println(aPerson("x", "y"))

  def aParameterless():Unit = {
    println("None")
  }

  aParameterless
}
