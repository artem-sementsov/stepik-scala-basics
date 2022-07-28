package lectures.week2oop.Inheritance

object Person extends App {
  val student = new Student
  println(student.greet)

  val person = new Person
  println(person.greet)
}

class Person(name: String = "", age: Int= 0) {
  def greet: String = s"Hello"
}

class Student(name: String = "", age: Int = 0, id: Int = 0) extends Person(name, age) {
  println(greet)
}


