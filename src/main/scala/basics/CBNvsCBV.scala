package basics

object CBNvsCBV {

  // CBV = call by value => arguments are evaluated before function invocation
  def aFunction(arg: Int): Int = arg + 1

  val aComputation = aFunction(23 + 67) // == 90 + 1

  // CBN = call by name => arguments are passed LITERALLY (as an expression), evaluated at every reference
  def aByNameFunction(arg: => Int): Int = arg + 1

  val anotherComputation = aByNameFunction(23 + 67) // == 23 + 67 + 1

  def printTwiceByValue(x: Long): Unit = {
    println("By value: :" + x)
    println("By value: :" + x)
  }

  def printTwiceByName(x: => Long): Unit = {
    println("By name: :" + x)
    println("By name: :" + x)
  }

  def infinite(): Int = 1 + infinite()

  def printFirst(x: Int, y: => Int) = println(x)

  def main(args: Array[String]): Unit = {
    printFirst(42, infinite())
  }
}
