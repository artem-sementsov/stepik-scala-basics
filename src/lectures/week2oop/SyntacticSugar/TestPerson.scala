package lectures.week2oop.SyntacticSugar

import scala.language.postfixOps

object TestPerson extends App {
  val bob = new Person("Bob", "Developer")
  println(bob.worksAs("Developer")) // true
  println(bob worksAs "Developer") // true

  println(bob.speaksEnglish) // точечная нотация
  println(bob speaksEnglish) // постфиксная нотация

  val alice = new Person("Alice", "Data Engineer")

  println(bob.&(alice)) // точечная нотация
  println(bob & alice) // инфиксная нотация

  println(!bob)
  println(bob.unary_!)
  println(bob unary_!)

  println(bob.apply())
  println(bob())

  println(bob isDeveloper)

  {
    class Person(val name: String) {
      def unary_+ : Person = {
        new Person(name + " NoSurname")
      }
    }

    val person = new Person("Bob")
    println((+person).name)
  }
}

class Person(val name: String, occupation: String) {
  def worksAs(jobName: String): String = s"$name is a $jobName"

  def speaksEnglish: Boolean = true

  def &(person: Person): String = s"${this.name} and ${person.name} are colleagues"

  def unary_! : String = s"$name is not real"

  def apply(): String = s"$name works as a $occupation"

  def isDeveloper = this worksAs ("Scala Developer")

}
