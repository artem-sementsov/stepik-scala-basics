package lectures.week2oop.Inheritance

object OverrideOverload extends App{

}

class A {
  def apply(n: Int): String = {
    "Hello"
  }

  def apply(n: Int, m: Int): Unit = {
    println("Hi")
  }
}

class B extends A {
  override def apply(n: Int): String = super.apply(n)

}
