package lectures.week2oop.ObjectsOOP

object Companions extends App {
  // опускаем наименование метода apply и сразу пишем необхоимые для этого метода параметры
  println(MyString("hello", "world")) // helloworld
  println(MyString("welcome")) // welcomeextraData
}

class MyString(val str: String) {
  private var extra = "extraData"

  override def toString: String = str + extra
}

object MyString {
  def apply(base: String, extras: String): MyString = {
    val s = new MyString(base)
    s.extra = extras
    s
  }

  def apply(base: String) = new MyString(base)
}
