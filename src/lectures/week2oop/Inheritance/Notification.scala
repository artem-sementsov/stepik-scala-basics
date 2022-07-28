package lectures.week2oop.Inheritance

object Notification extends App {
  val notification: Listener = new Listener("mousedown", null)

  notification.register(
    (eventName: String) => println(s"trigger $eventName event")
  )
  notification.sendNotification //trigger mousedown event
}

abstract class Event {
  def trigger(eventName: String): Unit
}

class Listener(val eventName: String, var event: Event) {
  def register(evt: Event) { event = evt }
  def sendNotification() { event.trigger(eventName) }
}


