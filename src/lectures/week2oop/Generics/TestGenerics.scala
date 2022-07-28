package lectures.week2oop.Generics

object TestGenerics extends App {
  def randomItem[A](items: List[A]): A = {
    val randomIndex = util.Random.nextInt(items.length)
    items(randomIndex)
  }

  println(randomItem(List("a", "bc", "def")))
  println(randomItem(List(1.5, 2.75, 3.8)))

  val contravariantList: ContravariantList[Apple] = new ContravariantList[Fruit]

  val guitar = Guitar(2021)
}

class Fruit

class Apple extends Fruit

class Banana extends Fruit


class ContravariantList[-A]

class MusicInstrument[+A](val productionYear: Int)

object MusicInstrument {
  def apply(productionYear: Int) = new MusicInstrument(productionYear)
}

case class Guitar(override val productionYear: Int) extends MusicInstrument(productionYear)

case class Piano(override val productionYear: Int) extends MusicInstrument(productionYear)


