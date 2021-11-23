package practice

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

  override def toString: String
}

class Cons[A](override val head: A, override val tail: LList[A]) extends LList[A]{

  override def isEmpty: Boolean = false

  override def add(element: A): Cons[A] = new Cons(element, this)

  override def toString: String = {
    def concatElements(remainder: LList[A], acc: String): String = {
      if(remainder.isEmpty) acc
      else concatElements(remainder.tail,s"$acc , ${remainder.head}")
    }

    s"[ ${concatElements(this.tail,s"$head")} ]"
  }

  override def map[B](transformer: Transformer[A, B]): LList[B] = new Cons[B](transformer.transform(head),tail.map(transformer))

  override def ++(list: LList[A]): LList[A] = {
    new Cons(head, tail ++ list)
  }


  override def filter(predicate: Predicate[A]): LList[A] = if (predicate.test(head)) new Cons(head,tail.filter(predicate)) else tail.filter(predicate)

  override def flatMap[B](transformer: Transformer[A, LList[B]]): LList[B] = {

    def doFlatMap(remailnder: LList[A], acc: LList[B]): LList[B] ={
      if(remailnder.isEmpty) return acc
      doFlatMap(remailnder.tail,acc.++(transformer.transform(remailnder.head)))
    }

    doFlatMap(this,new Empty[B])
  }
}

class Empty[A] extends LList[A] {

  /** Throwing errors */
  override def head = throw new NullPointerException("Tried to access HEAD of Empty Linked List Node")

  override def tail = throw new NullPointerException("Tried to access TAIL of Empty Linked List Node")


  /** Actual methods */
  override def isEmpty: Boolean = true

  override def add(element: A): Cons[A] = new Cons(element, new Empty[A])

  override def toString: String = "[]"

  override def map[B](transformer: Transformer[A, B]): LList[B] = new Empty[B]

  override def filter(predicate: Predicate[A]): LList[A] = new Empty[A]

  override def flatMap[B](transformer: Transformer[A, LList[B]]): LList[B] = new Empty[B]

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
    val listNotEmpty = new Cons(4, new Empty);
    println(listNotEmpty)
    val newList = listNotEmpty.add(3).add(2).add(1)

    println("Original List: " + newList)
    println("Map :" + newList.map(_ * 2))
    println("Flatmap :" + newList.flatMap(new Transformer[Int,Cons[Int]] {
      override def transform(element: Int): Cons[Int] = new Cons(element, new Cons(element+1, new Empty))
    }))
    println("Filter :" + newList.filter(EvenPredicate))
  }
}
