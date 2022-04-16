package power

import scala.util.Random

object PatternMatching {

  // switch case on steroids
  val random = new Random()
  val aValue: Int = random.nextInt(100)


  val description: String = aValue match {
    case 1 => "the first"
    case 2 => "the second"
    case 3 => "the third"
    case _ => s"something else: $aValue"
  }

  // decompose values
  case class Person(name: String, age: Int)
  val bob: Person = Person("Bob", 20)

  val greeting = bob match {
    case Person(n, a) if a < 18 => s"Hi, $n. You didn't get a good run, huh? Only $a and already dead..."
    case Person(n, a) => s"Hello, $n. You died at $a"
    case _ => "errorissimo"
  }

  /*
    Patterns are matched in order: put the most specific patterns first
    What if no cases match? MatchError
    What is the type returned? The lowest common ancestor of all types on the RHS (Right Hand Side) of each branch
  */

  // PM on sealed hierarchies
  sealed class Animal
  case class Dog(breed: String) extends Animal
  case class Cat(meowingStyle: String) extends Animal

  val anAnimal: Animal = Dog("Terra Nova")
  val animalPM = anAnimal match {
    case Dog(someBreed) => "I have detected a dog"
    case Cat(meow) => "I've detected a cat"
    case _ => "OwO"
  }

  /**
   * Exercise
   */

  trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Prod(e1: Expr, e2: Expr) extends Expr

  def show(expr: Expr): String = expr match {
    case Sum(e1, e2) => show(e1) + "+" + show(e2)
    case Prod(e1: Sum, e2) => "(" + show(e1) + ")" + "*" + show(e2)
    case Prod(e1, e2: Sum) => show(e1) + "*" + "(" + show(e2) + ")"
    case Prod(e1, e2) => show(e1) + "*" + show(e2)
    case Number(n) => n.toString
  }



  def main(args: Array[String]): Unit = {
    println(description)
    println(greeting)
    println(show(Sum(Sum(Prod(Number(5), Number(5)), Prod(Sum(Number(3), Number(2)), Number(5))),Number(99))))
  }
}
