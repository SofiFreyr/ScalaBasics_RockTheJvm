package oop

import oop.CaseClasses.Person

object Exceptions {

  val aString: String = null

  // 1 - throw exceptions
  //val aWeirdValue = throw new NullPointerException  // returns Nothing

  // type Throwable
  //     Error, e.g. StackOverflowError, OutOfMemoryError
  //     Exception (error in the logic), e.g. NullPointerException, NoSuchElementException

  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw  new NullPointerException("No Int for you! Jail for mother!")
    else 42

  val potentialFail = try{
    // code that might fail
    getInt(true)
  } catch {
    // most specific exceptions should come first
    case e: NullPointerException => "OwO" // a String
    case e: RuntimeException => 54  // an Int
  } finally {
    // runs no matter what
    // used for closing resources
    // Unit here
    println("Finally")
  }

  class MyException extends RuntimeException {
    // fields or methods
    override  def getMessage = "MY EXCEPTION"
  }

  // custom exceptions
  val myException = new MyException
  //val throwingMyException = throw new MyException

  // Excercises

  // 1
  def stackOverflowThrower(): String = {
    stackOverflowThrower() + "123"
  }

  // 2
  def outOfMemoryThrower(per: Person, perper: List[Person]): List[Person] = {
    if(perper.length >= 999999) return perper
    val obj1 = per.copy(age = per.age + 1)
    val obj2 = per.copy(age = per.age  + 2)
    val obj3 = per.copy(age = per.age + 3)
    val obj4 = per.copy(age = per.age  + 4)
    val string = obj1.name + obj2.name
    outOfMemoryThrower(obj4.copy("bloat1"),perper.appended(obj1).appended(obj2).appended(obj3).appended(obj4))
  }

  def main(args: Array[String]): Unit = {
    //println(potentialFail)

    //println(stackOverflowThrower())

    println(outOfMemoryThrower(Person("Sofie", 1),List[Person]()).mkString(", "))
  }
}
