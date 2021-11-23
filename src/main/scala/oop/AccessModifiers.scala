package oop

object AccessModifiers {

  class Person(val name: String) {
    protected def sayHi(): String = s"Hi, my name is $name."
    private  def watchNetflix(): String = "I'm binge watching my favorite series..."
  }


  val aPerson = new Person("Alice")

  class Kid(override val name: String, age: Int) extends Person(name) {
    def greetPolitely(): String = sayHi() + "I love to play!" // no midifier => public
  }

  val aKid = new Kid("Jon",5)


  // complication
  class KidWithParents(override val name: String,age: Int, momName: String, secondMomName: String) extends Person(name) {
    val mom = new Person(momName)
    val mom2 = new Person(secondMomName)

    def everyoneSayHi(): String = s"Hi, I'm $name and here are my parents: "
  }


  def main(args: Array[String]): Unit = {
    aKid.greetPolitely()
  }

}
