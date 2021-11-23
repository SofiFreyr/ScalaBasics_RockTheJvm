package oop

object Generics {

  abstract class MyList[T]{
    def head: T
    def tail: MyList[T]
  }

  class Empty[A] extends MyList[A]{
    override def head: A = throw new NoSuchElementException

    override def tail: MyList[A] = throw new NoSuchElementException
  }

  class NonEmpty[T](override val head: T, override val tail: MyList[T]) extends MyList[T]

  // multiple generic types
  trait MyMap[Key, Value]

  // generic methods
  object MyList{
    def from2Elements[A](elem1: A, elem2: A): MyList[A] =
      new NonEmpty[A](elem1, new NonEmpty[A](elem2, new Empty[A]))
  }

  // calling methods
  val first2Numbers = MyList.from2Elements[Int](1,2)
  val first2Numbers_v2 = MyList.from2Elements(1,2)
  val first2Numbers_v3 = new NonEmpty(1, new NonEmpty(2, new Empty))

  /**
   * Excercise:
   * Make linked list generic
   */

  def main(args: Array[String]): Unit = {

  }
}
