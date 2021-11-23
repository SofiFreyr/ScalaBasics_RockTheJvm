package oop

object Objects {

  object MySingleton{ // type + the only instance of this type
    val aField = 45
    def aMethod(x: Int) = x + 1
  }

  val theSingleton = MySingleton
  val anotherSingleton = MySingleton
  val isSameSingleton = theSingleton == anotherSingleton

  // objects can have fields and methods
  val theSingletonField = MySingleton.aField
  val theSingleronMethodCall = MySingleton.aMethod(99)


  // in Scala object != instance of a class

  class Person(name: String){
    def sayHi(): String = s"Hi, my name is $name"
  }

  // companions = class + object with the same name in the same file
  object Person{// companion object
    // can access each other's private fields and methods
    val N_EYES = 2
    def canFly(): Boolean = false
  }

  // methods and fields in classes are used for instance-dependent functionality
  val mary = new Person("Mary")
  val maryV2 = new Person("Mary")
  val marysGreeting = mary.sayHi()

  // methods ad fields in objects are used for instance-independent functionality - "static"
  val humansCanFly = Person.canFly()
  val nEyesHuman = Person.N_EYES


  // equality
  // 1 - equality of reference  - usually defined as ==
  val sameMary = mary eq maryV2 // false -- different instances
  val sameSingleton = MySingleton eq MySingleton // true
  // 2 - equality of "sameness"   - usually defined as .equals
  val sameMary_v2 = mary equals maryV2 // false
  val sameMary_v3 = mary == maryV2 // same as equals
  val sameSingleton_v2 = MySingleton == MySingleton // true


  // objects can extend classes
  object BigFoot extends Person("Big Foot")


  // Scala application = object + def main(args: Array[String]): Unit
  // JAVA BELOW
  /*
      public class Objects {
        public static void main(Strings[] args) { ... }
      }
   */
  def main(args: Array[String]): Unit = {
    println(isSameSingleton)
    println(sameMary_v3)
  }
}
