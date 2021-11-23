package oop
import scala.language.postfixOps

object MethodNotation {

  class Person(val name: String, age: Int,favouriteMovie: String) {

    def likes(movie: String): Boolean =
      movie == favouriteMovie

    def +(person: Person): String =
      s"${this.name} is hanging out with ${person.name}"

    def !!(progLanguage: String): String =
      s"$name wonders how can $progLanguage be so cool!"


    // prefix position
    // unary ops: -,+,~,!
    def unary_- :String = s"$name's alter ego"

    // postfix notation
    def isAlive: Boolean = true


    def apply(): String = s"Hi my name is, hi my name is, hi my name is $name"
  }

  val mary = new Person("Mary",34, "Inception")
  val jill = new Person("Jill", 36, "Fight Club")

  def main(args: Array[String]): Unit = {
    println(mary.likes("Fight Club"))

    // infix notation - for methods with ONE argument
    println(mary likes "Fight Club")  // identical

    // "operator"
    println(mary + jill)
    println(mary.+(jill)) // identical

    println(2.+(3))
    println(2 + 3) // identical

    println(mary !! "Scala")

    //prefix position
    println(-mary)
    println(mary.unary_-) // identical

    //postfix notation
    println(mary.isAlive)
    println(mary isAlive) // discouraged

    //apply is special
    println(mary.apply())
    println(mary())
  }
}
