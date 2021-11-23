package oop

object OOBasics {

  class Person(val name: String, age: Int) { // construction signature

    // fields
    val allCaps = name.toUpperCase()


    // methods
    def greet(name: String): String = s"${this.name} says: Hi, $name"

    // signature differs
    // overloading
    def greet(): String = s"Hi everyone, my name is $name"

    // aux constructors
//    def this(name: String) =
//      this(name, 0)
//
//    def this() =
//      this("Jane Doe")
  }

  val aPerson: Person = new Person("Sofie", 24)
  val sofie = aPerson.name // class parameter != field
  val SOFIE = aPerson.allCaps
  val sofieSayHiToDaniel = aPerson.greet("Daniel")
  val sofieSayHi = aPerson.greet()


  class Writer(firstName: String, surname: String,val year: Int){

    def fulname() = firstName + " " + surname
  }

  class Novel(name: String, year: Int, author: Writer){
    def authorAge = author.year - year
    def isWrittenBy(author: Writer): Boolean = this.author.equals(author)
    def copy(year: Int) = new Novel(name,year,author)
  }


  /**
   * an immutable counter class
   * - constructed with an initial count
   * - increment/decerment => return new instance
   * - increment(n)/decrement(n) => return new instance
   * - print() => return count
   */

  class Counter(count: Int){
    def increment(): Counter = new Counter(this.count + 1)
    def decrement(): Counter = new Counter(this.count - 1)


    def increment(n: Int): Counter = {
      if(n <= 0) this
      else increment().increment(n - 1)
    }

    def decrement(n: Int): Counter = {
      if(n <= 0) this
      else decrement().decrement(n - 1)
    }

    def print() = println(this.count)
  }

  val newCounter = new Counter(0)


  def main(args: Array[String]): Unit = {
    println(sofieSayHiToDaniel)
    println(sofieSayHi)

    newCounter.print()
    newCounter.increment().print()
    newCounter.increment(3).print()
  }
}
