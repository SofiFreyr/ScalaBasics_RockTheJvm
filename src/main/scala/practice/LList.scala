package practice

import oop.Exceptions.MyException

// singly linked list
// [1,2,3] = [1] -> [2] -> [3] -> |

abstract class LList[A] {
  def head: A
  def tail: LList[A]
  def isEmpty: Boolean
  def add(element: A): LList[A]

  def map[B](transformer: Transformer[A,B]): LList[B]
  def filter(predicate: Predicate[A]): LList[A]
  def flatMap[B](transformer: Transformer[A, LList[B]]): LList[B]


  def ++(list: LList[A]): LList[A]

  def toString: String

  // find test
  def find(predicate: Predicate[A]): A
}

case class Cons[A](override val head: A, override val tail: LList[A]) extends LList[A]{

  override def isEmpty: Boolean = false

  override def add(element: A): Cons[A] = Cons(element, this)

  override def toString: String = {
    def concatElements(remainder: LList[A], acc: String): String = {
      if(remainder.isEmpty) acc
      else concatElements(remainder.tail,s"$acc , ${remainder.head}")
    }

    s"[ ${concatElements(this.tail,s"$head")} ]"
  }

  override def map[B](transformer: Transformer[A, B]): LList[B] = Cons[B](transformer.transform(head),tail.map(transformer))

  override def ++(list: LList[A]): LList[A] = {
    Cons(head, tail ++ list)
  }


  override def filter(predicate: Predicate[A]): LList[A] = if (predicate.test(head)) Cons(head, tail.filter(predicate)) else tail.filter(predicate)

  override def flatMap[B](transformer: Transformer[A, LList[B]]): LList[B] = {

    def doFlatMap(remailnder: LList[A], acc: LList[B]): LList[B] ={
      if(remailnder.isEmpty) return acc
      doFlatMap(remailnder.tail,acc.++(transformer.transform(remailnder.head)))
    }

    doFlatMap(this, Empty())
  }

  override def find(predicate: Predicate[A]): A = {

    def doFind(remainder: LList[A]): A = {
      if(remainder.isEmpty) remainder.find(predicate)
      if (predicate.test(remainder.head)) remainder.head
      else doFind(remainder.tail)
    }

    doFind(this)
  }

}

case class Empty[A]() extends LList[A] {

  /** Throwing errors */
  override def head = throw new NullPointerException("Tried to access HEAD of Empty Linked List Node")

  override def tail = throw new NullPointerException("Tried to access TAIL of Empty Linked List Node")

  override def find(predicate: Predicate[A]): A = throw new NullPointerException("Tried search an Empty Linked List Node")


  /** Actual methods */
  override def isEmpty: Boolean = true

  override def add(element: A): Cons[A] = Cons(element, Empty())

  override def toString: String = "[]"

  override def map[B](transformer: Transformer[A, B]): LList[B] = Empty()

  override def filter(predicate: Predicate[A]): LList[A] = Empty()

  override def flatMap[B](transformer: Transformer[A, LList[B]]): LList[B] = Empty()

  override def ++(list: LList[A]): LList[A] = list
}


/** Predicates */
trait Predicate[T]{
  def test(element: T): Boolean
}

object EvenPredicate extends Predicate[Int]{
  override def test(element: Int): Boolean = element % 2 == 0
}

trait Transformer[A,+B]{
  def transform(element: A): B
}


object LListTest {
  def main(args: Array[String]): Unit = {
    val listNotEmpty = Cons(4, Empty());
    println(listNotEmpty)
    val newList = listNotEmpty.add(3).add(2).add(1)

    println("Original List: " + newList)
    println("Map :" + newList.map(_ * 2))
    println("Flatmap :" + newList.flatMap((element: Int) => Cons(element, Cons(element + 1, Empty()))))
    println("Filter :" + newList.filter(EvenPredicate))
    println(newList.find(_ == 99))

  }
}
