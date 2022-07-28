package playground

import scala.math.BigInt.probablePrime
import scala.util.Random

object ScalaPlayground {
  def main(args: Array[String]): Unit = {
    println("Hello, Scala!")
  }

  {
    "Hello".intersect("World")
  }
  {
    println(probablePrime(100, Random))
  }

  {
    (1 to 10).foreach(_ => {
      val randomBigInt: BigInt = probablePrime(100, Random)
      val baseString: String = randomBigInt.toString(36)
      println(baseString)
    }
    )
  }

  {
    println("Hello"(0))
    println("Hello".take(0))

  }
}
