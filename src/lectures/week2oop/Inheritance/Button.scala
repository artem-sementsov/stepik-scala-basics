package lectures.week2oop.Inheritance

object TestButton extends Button {

}

class Button(label: String = "test") {
  def click(): String = {
    s"button -$label- has been clicked"
  }
}

class RoundedButton(label: String) extends Button(label) {
  override def click(): String = "rounded " + super.click()
}


