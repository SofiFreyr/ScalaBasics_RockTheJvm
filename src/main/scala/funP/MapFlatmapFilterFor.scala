package funP

object MapFlatmapFilterFor {

  // standard List
  val aList = List(1,2,3,4,5) // [1] -> [2] -> [3] -> [4] -> [5] -> Nil // [1,2,3,4,5]
  val firstElement = aList.head
  val restOfElements = aList.tail

  // map
  val anIncrementedList = aList.map(_ + 1)

  // filter
  val onlyOddNumbers = aList.filter(_ % 2 != 0)

  // flatMap
  val toPair = (x: Int) => List (x, x + 1)
  val flatMappedList = aList.flatMap(toPair)
  val flatMappedList_v2 = aList.flatMap(x => List(x, x + 1))

  // All the possible combinations of all elements of these lists in the format "1a - black"
  val numbers = List(1,2,3,4)
  val chars = List('a','b','c','d')
  val colors = List("black", "white", "red")

  val combinations = numbers.withFilter(_ % 2 == 0).flatMap(num => chars.flatMap(cha => colors.map(col => s"$num$cha - $col")))


  // for-comprehension = IDENTICAL to flatMap + map chains
  val combinationsFor = for {
    num <- numbers if num % 2 == 0
    char <- chars
    color <- colors
  }yield s"$num$char - $color"

  // for-comprehensions with Unit
  // if Collection has foreach

  /**
   * Excercises
   * 1. A small collection of AT MOST ONE element - Maybe[A]
   *  - map
   *  - flatMap
   *  - filter
   */

  abstract class Maybe[A]{
    def map[B](f: A => B): Maybe[B]
    def flatMap[B](f: A => Maybe[B]): Maybe[B]
    def filter(f: A => Boolean): Maybe[A]
  }

  case class Nada[A]() extends Maybe[A] {
    def getElement() = throw NullPointerException()
    override def map[B](f: A => B): Nada[B] = Nada[B]()
    override def flatMap[B](f: A => Maybe[B]): Nada[B] = Nada[B]()
    override def filter(f: A => Boolean): Nada[A] = this
  }

  case class Some[A](element: A) extends Maybe[A]{
    def getElement() = this.element
    override def map[B](f: A => B): Some[B] = new Some[B](f(element))
    override def filter(f: A => Boolean): Maybe[A] = if (f(element)) this else Nada[A]()
    override def flatMap[B](f: A => Maybe[B]): Maybe[B] = f(element)
  }

  def main(args: Array[String]): Unit = {
    numbers.foreach(println)
    println(combinations)
    println(combinationsFor)
  }
}
