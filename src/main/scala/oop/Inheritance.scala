package oop

object Inheritance {

  class Animal {
    val creatureType = "wild"
    def eat(): Unit = println("Nomnomnom")
  }

  class Domestic extends Animal{
    override val creatureType: String = "domestic"
  }


  def main(args: Array[String]): Unit = {
    val animal = new Animal;
    animal.eat()
  }
}
