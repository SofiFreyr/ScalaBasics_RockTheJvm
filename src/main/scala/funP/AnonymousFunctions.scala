package funP

object AnonymousFunctions {

  // instances of functions
  val doubler: Int => Int  = new Function[Int,Int] {
    override def apply(v1: Int): Int = v1 * 2
  }

  // lambdas = anonymous function instances
  val doublerV2: Int => Int = (x: Int) => x * 2 // identical
  val adder: (Int,Int) => Int = (x: Int, y: Int) => x + y //  new Function[Int,Int,Int] = override apply
  // zero argument functions
  val doSomething: () => Int = () => 49
  val anInvocation = doSomething()

  def intVariable(x: Int,y: Int) = {
    x + y
  }
  // alt syntax
  val stringToInt = {(str: String) =>
    //implementation: code block
    str.toInt
  }

  // type inference
  val doublerV3: Int => Int = x => x * 2 //type inferred by compiler
  val addedV2: (Int,Int) => Int = (x,y) => x + y

  // shortest lambdas
  val doubler_v4: Int => Int = _ * 2
  val adder_v3: (Int, Int) => Int = _ + _

  /** Excercises
   *  1. Replace all function instantiations with lambdas in LList
   *  2. Re-implement supperAdded from previous lesson with lambdas
   */
  //val supperAdder: Int => Int => Int = (x: Int) => x+_
  val supperAdder = (x:Int) => (y: Int) => x+y


  def main(args: Array[String]): Unit = {
    println(doSomething)
    println(doSomething())
    println(doublerV3(3))
  }
}
