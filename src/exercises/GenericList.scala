package exercises

import scala.annotation.tailrec

object GenericList extends App {
  val list = new Log("INFO_1", new Log("INFO_2", new Log("INFO_3", Empty)))

  println(list.previous.last)

  val x = new Log("m1", new Log("m2", new Log("m3", Empty)))
  println(x.all)

  val intLogs: LogList[Int] = Empty
  val strLogs: LogList[String] = Empty
}

abstract class LogList[+A] {
  def add[B >: A](msg: B): LogList[B]

  def isEmpty: Boolean

  def last: A

  def previous: LogList[A]

  def all: String
}

object Empty extends LogList[Nothing] {
  def add[B >: Nothing](msg: B): LogList[B] = new Log(msg, Empty)

  override def isEmpty: Boolean = true

  override def last: Nothing = throw new NoSuchElementException

  override def previous: LogList[Nothing] = throw new NoSuchElementException

  override def all: String = ""
}

class Log[+A](head: A, tail: LogList[A]) extends LogList[A] {

  def add[B >: A](msg: B): LogList[B] = new Log(msg, this)

  def last: A = head

  def previous: LogList[A] = tail

  def isEmpty: Boolean = false

  def all: String = {
    @tailrec
    def loop(logList: LogList[A], acc: String): String = {
      if (logList.isEmpty) {
        acc
      }
      else {
        loop(logList.previous, acc + " " + logList.last.toString)
      }
    }

    loop(tail, head.toString)
  }

}

