package lectures.week2oop.Generics

object TestAdvanced extends App {
  // 1
  {
    abstract class Customer {
      def isVip: Boolean
    }

    class Regular(val vipStatus: Boolean) extends Customer {
      override def isVip: Boolean = vipStatus
    }

    class Vip(val vipStatus: Boolean) extends Customer {
      override def isVip: Boolean = vipStatus
    }

    class Order[T <: Customer](val person: T) {
      def offer: String = if (person.isVip) "free gift" else "no gift"
    }
    val anOrder = new Order[Vip](new Vip(true)) // по логике приложения использование Int должно быть недопустимо
    println(anOrder.person)
    println(anOrder.offer)
  }


  // 2
  {
    abstract class Customer {
      def isVip: Boolean
    }

    class Regular(val vipStatus: Boolean) extends Customer {
      override def isVip: Boolean = vipStatus
    }

    class Vip(val vipStatus: Boolean) extends Customer {
      override def isVip: Boolean = vipStatus
    }

    class Friend extends Vip(false)

    class Order[T >: Vip <: Customer](val person: T) {
      def offer: String = if (person.isVip) "free gift" else "no gift"
    }

//    val vipsFriend = new Order[Friend](new Friend)
//    println(vipsFriend.offer)
    val vip = new Order[Vip](new Vip(true))
    println(vip.offer)
  }

  //3
  {
    abstract class Customer {
      def isVegetarian: Boolean
    }


    class Order[T <: Customer](val person: T) {
      def showMenu: String = if (person.isVegetarian) "vegetarian menu" else "ordinary menu"
    }
  }

  //4
  class Programmer(name: String)
  case class DataAnalyst(val name: String) extends Programmer(name)
  {
//    class Employee[-T](val employee: T) {
//      def info() = println(employee)
//    }
//    val employee: Employee[DataAnalyst] = new Employee[Programmer](new Programmer("Bob"))
//    employee.info()
  }
  {
//    class Employee[T](val employee: T) {
//      def info() = println(employee)
//    }
//    val employee: Employee[DataAnalyst] = new Employee[Programmer](new Programmer("Bob"))
//    employee.info()
  }
  {
//    class Employee[+T](val employee: T) {
//      def info() = println(employee)
//    }
//    val employee: Employee[DataAnalyst] = new Employee[Programmer](DataAnalyst("Bob"))
//    employee.info()
  }
  {
    class Employee[+T](val employee: T) {
      def info() = println(employee)
    }
    val employee: Employee[DataAnalyst] = new Employee[DataAnalyst](DataAnalyst("Bob"))
    employee.info()
  }
}

