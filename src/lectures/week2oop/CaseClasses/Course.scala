package lectures.week2oop.CaseClasses

object TestCourse extends App {

  val scalaCourse = Course("Scala", "Bob")

  val course1 = Course("Scala", "Bob")
  val course2 = Course("Alice")
  val course3 = new Course("Scala", "Bob")
  val course4 = scalaCourse.copy("AdvancedScala")
  val course5 = scalaCourse.copy()

  {
    case class Course(title: String, instructor: String)
    val course = Course("Scala", "Bob")
    println(course.title)
  }

  val x: Unit = print("")
}

case class Course(title: String, instructor: String)

object Course {
  def apply(instructor: String): Course = Course("AdvancedScala", instructor)
}

