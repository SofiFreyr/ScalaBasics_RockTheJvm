package oop

import scala.runtime.Nothing$

object AbstractDataTypes {

  //Abstract classes cannot be instantiated
  abstract class Animal {
    val creatureType: String   // abstract field (no implementation)
    def eat(): Unit // abstract method (no implementation)

    // non-abstract
    def prefferenceMeal: String = "anything"  // accessor methods
  }

  class Dog extends Animal{
    override val creatureType: String = "Dog"
    override def eat(): Unit = println("Chomp!")

    // overriding is legal for everything
    override val prefferenceMeal: String = "bones" // overriding accessor method (without args/parentheses) with a field
  }

  val anAnimal = new Dog


  // traits
  trait Carnivore{
    def eat(animal: Animal): Unit
  }

  trait ColdBlooded

  class TRex extends Carnivore{
    override def eat(animal: Animal): Unit = println("T-Rexes were scavengers")
  }


  //practical differences between abstract class and trait
  // one class inheritance
  // multiple traits inheritance

  class Crocodile extends Animal with Carnivore with ColdBlooded{
    override def eat(animal: Animal): Unit = println("Chompers")

    override def eat(): Unit = println("Chompers")

    override val creatureType: String = "Croc"

    override def prefferenceMeal: String = "You!"
  }


  /*philosophical difference

      - Classes are types of things

      - Traits are behaviors

   */

  /*
      Any
        AnyRef
          All classes we write
            scala.Null (the null reference)
        AnyVal
          Int, Boolean, Char


            scala.Nothing => absence of anything
   */

  val aNonExistantAnimal: Dog = null
  val anInt: Int = throw new NullPointerException


  def main(args: Array[String]): Unit = {

  }
}
