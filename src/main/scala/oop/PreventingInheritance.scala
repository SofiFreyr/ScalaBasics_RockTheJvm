package oop

object PreventingInheritance {

  class Person(name: String) {
    final def enjoyLife(): Int = 42
  }

  class Adult(name: String) extends Person(name){
    //override def enjoyLife(): Int = 0
  }


  // sealing a type hierarchy
  class Guitar(nString: Int)
  class ElectricGuitar(nStrings: Int)
  class AcousticGuitar extends Guitar(6)

  // no modifier = "not encouraging" ingeritance


  def main(args: Array[String]): Unit = {

  }
}
