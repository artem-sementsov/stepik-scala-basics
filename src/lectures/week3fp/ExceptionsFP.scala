package lectures.week3fp

import scala.util.{Try, Failure, Success}

object ExceptionsFP extends App {
  {
    def unsafeMethod(): String = throw new RuntimeException("Sorry, not your day")

    val potentialFailure = Try(unsafeMethod())
    println(potentialFailure) // Failure(java.lang.RuntimeException: Sorry, not your day)
    println(potentialFailure.isSuccess) // false
  }
  {
    def unsafeMethod(): String = throw new RuntimeException("Sorry, not your day")

    val anotherPotentialFailure = Try {
      Try(unsafeMethod())
      "another failure"
    }

    println(anotherPotentialFailure)
  }

  {
    def unsafeMethod(): String = throw new RuntimeException("Sorry, not your day")

    def myMethod(): String = "My method is valid"

    val executeWithTry = Try(unsafeMethod()).orElse(Try(myMethod()))

    println(executeWithTry) // Success(My method is valid)


    def methodWhichFails(): Try[String] = Failure(new RuntimeException("Ooops..."))

    def methodWhichSucceeds(): Try[String] = Try("Everything is OK")

    val tryMethods: Try[String] = methodWhichFails() orElse methodWhichSucceeds()

    println(tryMethods) // Success(Everything is OK)
  }

  {
    val someVal = Success(3)

    println(someVal.filter(_ > 5))

  }
}
