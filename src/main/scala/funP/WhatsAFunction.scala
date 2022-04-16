package funP

object WhatsAFunction {

  //FP -> functions are "first-class" citizens
  //JVM -> no first-class function

  trait MyFunction[A, B]{
    def apply(arg: A): B
  }

  val doubler = new MyFunction[Int, Int] {
    override def apply(arg: Int): Int = arg * 2
  }

  val meaningOfLife = 42
  val meaningDoubled = doubler.apply(meaningOfLife)


  // function types

  val doublerStandard = new Function[Int,Int] {
    override def apply(arg: Int): Int = arg * 2
  }
  val meaningDoubledV2 = doublerStandard(meaningOfLife)


  val adder = new Function2[Int,Int,Int] {
    override def apply(v1: Int, v2: Int): Int = v1 + v2
  }

  val anAddition = adder(2,67)


  // (Int, String, Double, Boolean) => Int ===== Function4[Int, String, Double, Boolean, Int]
  val aThreeArgFunction = new Function4[Int,String,Double,Boolean,Int] {
    override def apply(v1: Int, v2: String, v3: Double, v4: Boolean): Int = ???
  }

  
  // all functions are instances of FunctionX with apply methods

  def afunction(i: Int): Int = i*2
  val afunction2: (String, String) => String = { (a,b) =>
    a + b
  }
  
  val supperAdded = new Function[Int, Function1[Int, Int]] {
    override def apply(x: Int) = new Function1[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }

  def main(args: Array[String]): Unit = {
    println(afunction2("a","b"))
  }

}
