package lectures.week2oop.ClassesOOP

object OOPBasics extends App {
  val student = new Student(0, "Bob")

  println(student.name)

  val car = new Car("Seat")
  println(car.color)

  val employee = new Employee()
  println(s"${employee.name}'s salary is ${employee.salary}")

  val instructor: Instructor = new Instructor(1, "вася", "пупкин")
  println(instructor.fullName())    // Вася Пупкин

  val course: Course = new Course(2, "Course Name", "1234", instructor)
  println(course.instructor.fullName())    // Вася Пупкин
  println(course.getID)    // 21
  println(course.isTaughtBy(instructor))    // true
  println(course.isTaughtBy(new Instructor(1, "Вася", "Пупкин")))    // false

  println(course.copyCourse("4321").releaseYear)    // 4321

  println("вася".capitalize)

  println(Utils.Pi)
  println(Utils.getPi)
}

class Student(var id: Int, val name: String) {
  val uni = "University"

  println("Student class")
}

class SomeClass(a: Int, b: Int, val c: Int) {
  def someFunc(): Int = b
}

class Car(model: String) {
  val color = "blue"
}

class Employee(val name: String, var salary: Double) {
  def this() {
    this("John", 0.0d)
  }
}

class Instructor(val id: Int, val name: String, val surname: String) {
  def fullName(): String = {
    s"${name.capitalize} ${surname.capitalize}"
  }
}

class Course(courseID: Int, title: String, val releaseYear: String, val instructor: Instructor) {
  def getID: String = {
    s"$courseID${instructor.id}"
  }

  def isTaughtBy(instructor: Instructor): Boolean = {
    instructor == this.instructor
  }

  def copyCourse(newReleaseYear: String): Course = {
    new Course(courseID, title, newReleaseYear, instructor)
  }
}

object Utils {
  val Pi = 3.14

  def getPi: Double = Pi
}
