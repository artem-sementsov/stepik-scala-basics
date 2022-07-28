//package exercises
//
//import scala.annotation.tailrec
//
//
//object LinkedList extends App {
//  val list = new Log("INFO_1", new Log("INFO_2", new Log("INFO_3", Empty)))
//
//  println(list.previous.last)
//
//  val x = new Log("m1", new Log("m2", new Log("m3", Empty)))
//  println(x.all)
//}
//
//abstract class LogList {
//  def add(msg: String): LogList
//
//  def isEmpty: Boolean
//
//  def last: String
//
//  def previous: LogList
//
//  def all: String
//}
//
//object Empty extends LogList {
//  def add(msg: String): LogList = new Log(msg, Empty)
//
//  override def isEmpty: Boolean = true
//
//  override def last: String = throw new NoSuchElementException
//
//  override def previous: LogList = throw new NoSuchElementException
//
//  override def all: String = ""
//}
//
//class Log(head: String, tail: LogList) extends LogList {
//
//  def add(msg: String): LogList = new Log(msg, this)
//
//  def last: String = head
//
//  def previous: LogList = tail
//
//  def isEmpty: Boolean = false
//
//  def all: String = {
//    @tailrec
//    def loop(logList: LogList, acc: String): String = {
//      if (logList.isEmpty) {
//        acc
//      }
//      else {
//        loop(logList.previous, acc + " " + logList.last)
//      }
//    }
//
//    loop(tail, head)
//  }
//
//}
