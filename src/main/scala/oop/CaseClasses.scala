package oop

object CaseClasses {

  // lightweight data structures
  case class Person(name: String, age: Int)

  // 1 - class arguments are now fields
  val daniel = new Person("Daniel", 99)
  val danielsAge = daniel.age

  // 2 - toString, equals and hashCode
  val danielToString = daniel.toString // Person("Daniel",99)
  val danielDuped = new Person("Daniel", 99)
  val isSameDaniel = daniel == danielDuped

  // 3 - utility methods
  val danielCopied = daniel.copy(age = 98) // new Person("Daniel", 98)

  // 4 - all case classes have companion objects
  val thePersonSingleton = Person
  val daniel_v2 = Person.apply("Daniel", 99)  // "constructor"

  // 5 - case classes are serializable
  // useful for Akka

  // 6 - CCs have extractor patterns for PATTERN MATCHING
  // CCs with no constructor arguments are not allowed

  case object UnitedKingdom {
    // fields and methods
    def name: String = "The UK of GB and NI"
  }

  case class CCWithNoArgs(){

  }

  val ccna = new CCWithNoArgs
  val ccna_v2 = new CCWithNoArgs
  val ccna_equality = ccna == ccna_v2



  def main(args: Array[String]): Unit = {
    println(danielToString)
    println(isSameDaniel)
    println(ccna_equality)
  }
}
