package exercises

import scala.annotation.tailrec

object Trees extends App {
  val tree: BinaryTree[Int] = Node(1,
    Node(2,
      Node(4,
        TreeEnd,
        TreeEnd),
      Node(5,
        TreeEnd,
        Node(8,
          TreeEnd,
          TreeEnd))),
    Node(3,
      Node(6,
        TreeEnd,
        TreeEnd),
      Node(7,
        TreeEnd,
        TreeEnd)))

  println(tree.collectLeaves.map(bt => bt.value).sorted)
  println(tree.countLeaves)

  println(tree.nodesAtLevel(0).size)
  println(tree.nodesAtLevel(1).size)
  println(tree.nodesAtLevel(2).size)
  println(tree.nodesAtLevel(3).size)

  println(tree.collectNodes())

  println(hasPath(tree, 7))
  println(hasPath(tree, 3))

  val tree2 = Node("1",
    Node("2",
      Node("4",
        TreeEnd,
        TreeEnd),
      Node("5",
        TreeEnd,
        Node("8",
          TreeEnd,
          TreeEnd))),
    Node("3",
      Node("6",
        TreeEnd,
        TreeEnd),
      Node("7",
        TreeEnd,
        TreeEnd))
  )
  println(findAllPaths(tree2, "10"))

  val tree3 = Node("1",
    Node("2",
      Node("1", TreeEnd, TreeEnd),
      TreeEnd),
    Node("1",
      Node("2", TreeEnd, TreeEnd),
      TreeEnd))

  println(findAllPaths(tree3, "4"))

  def hasPath(tree: BinaryTree[Int], target: Int): Boolean = {
    def loop(q: List[(BinaryTree[Int], Int)]): Boolean = {
      q match {
        case Nil => false
        case head :: tail if head._1.isEmpty || head._1.value > head._2 => loop(tail)
        case head :: _ if head._1.isLeaf && head._1.value == head._2 => true
        case head :: tail =>
          val left = (head._1.leftChild, head._2 - head._1.value)
          val right = (head._1.rightChild, head._2 - head._1.value)
          loop(left +: right +: tail)
      }
    }

    loop(List((tree, target)))
  }

  def findAllPaths(tree: BinaryTree[String], target: String): List[List[String]] = {
    def loop(q: List[(BinaryTree[String], Int, List[Int])], acc: List[List[Int]]): List[List[Int]] = {
      q match {
        case Nil => acc.map(list => list.reverse)
        case (tree, target, _) :: tail if tree.isEmpty || tree.value.toInt > target => loop(tail, acc)
        case (tree, target, path) :: tail if tree.isLeaf && tree.value.toInt == target => loop(tail, (tree.value.toInt +: path) +: acc)
        case (tree, target, path) :: tail =>
          val left = (tree.leftChild, target - tree.value.toInt, tree.value.toInt +: path)
          val right = (tree.rightChild, target - tree.value.toInt, tree.value.toInt +: path)
          loop(right +: left +: tail, acc)
      }
    }

    val res = loop(List((tree, target.toInt, List())), acc = List())
    res.map((list: List[Int]) => list.map((value: Int) => value.toString))
  }
}

abstract class BinaryTree[+T] {
  def value: T

  def leftChild: BinaryTree[T]

  def rightChild: BinaryTree[T]

  def isEmpty: Boolean

  def isLeaf: Boolean

  def collectLeaves: List[BinaryTree[T]]

  def countLeaves: Int

  def nodesAtLevel(level: Int): List[BinaryTree[T]]

  def collectNodes(): List[T]
}

case class Node[+T](
                     override val value: T,
                     override val leftChild: BinaryTree[T],
                     override val rightChild: BinaryTree[T])
  extends BinaryTree[T] {
  override def isEmpty: Boolean = false

  override def isLeaf: Boolean = leftChild.isEmpty && rightChild.isEmpty

  override def collectLeaves: List[BinaryTree[T]] = {
    @tailrec
    def loop(toInspect: List[BinaryTree[T]] = List(this), leaves: List[BinaryTree[T]] = List()): List[BinaryTree[T]] = {
      toInspect match {
        case Nil => leaves
        case head :: tail => if (head.isLeaf) loop(tail, head +: leaves)
        else {
          val left = if (!head.leftChild.isEmpty) List(head.leftChild) else List()
          val both = if (!head.rightChild.isEmpty) head.rightChild +: left else left
          loop(both ++ tail, leaves)
        }
      }
    }

    loop()
  }

  override def countLeaves: Int = this.collectLeaves.size

  override def nodesAtLevel(level: Int): List[BinaryTree[T]] = {

    case class NodeWithLevel(level: Int, node: BinaryTree[T]) {
      def inc(node: BinaryTree[T]): NodeWithLevel = NodeWithLevel(this.level + 1, node)
    }

    @tailrec
    def loop(q: List[NodeWithLevel], acc: List[BinaryTree[T]]): List[BinaryTree[T]] = {
      q match {
        case Nil => acc
        case head :: tail if head.node.isEmpty || head.level > level => loop(tail, acc)
        case head :: tail =>
          if (head.level == level) loop(tail, head.node +: acc)
          else {
            loop(head.inc(head.node.leftChild) +: head.inc(head.node.rightChild) +: tail, acc)
          }
      }
    }

    loop(List(NodeWithLevel(0, this)), List())
  }

  override def collectNodes(): List[T] = {
    @tailrec
    def loop(toInspect: List[BinaryTree[T]] = List(this), acc: List[T] = List()): List[T] = {
      toInspect match {
        case Nil => acc
        case head :: tail if head.isEmpty => loop(tail, acc)
        case head :: tail =>
          loop(head.leftChild +: head.rightChild +: tail, head.value +: acc)
      }
    }

    loop()
  }
}

case object TreeEnd extends BinaryTree[Nothing] {
  override def value: Nothing = throw new NoSuchElementException

  override def leftChild: BinaryTree[Nothing] = throw new NoSuchElementException

  override def rightChild: BinaryTree[Nothing] = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def isLeaf: Boolean = false

  override def collectLeaves: List[BinaryTree[Nothing]] = List()

  override def countLeaves: Int = 0

  override def nodesAtLevel(level: Int): List[BinaryTree[Nothing]] = List()

  override def collectNodes(): List[Nothing] = List()
}

object TreesTests extends App {
  {
    val tree: BinaryTree[Int] = TreeEnd

    println(tree.collectLeaves.map(bt => bt.value).sorted)
  }
}
