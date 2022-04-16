package funP

import scala.annotation.tailrec

object HOFsCurrying {

  // Higher order functions
  val aHof: (Int,(Int => Int)) => Int = (x, func) => x + 1
  val anotherHof: Int => (Int => Int) = x => (y => y + 2 * x)

  //exercise
  val superfunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = (x,func) => (y => x + y)

  //examples: map, flatMap, filter

  //more examples:
  @tailrec
  def nTimes(f: Int => Int, n: Int, x: Int): Int = {
    if (n <= 0) x
    else nTimes(f, n-1, f(x))
  }

  def nTimes_v2(f: Int => Int, n: Int): Int => Int = {
    if (n <= 0) (x: Int) => x
    else (x: Int) => nTimes_v2(f, n-1)(f(x))
  }

  val plusOne = (x: Int) => x + 1
  val tenThousand = nTimes(plusOne, 10000, 0)
  val tenThousand_v2 = nTimes_v2(plusOne,10000) // risks stack overflow

  // currying
  val supperAdder: Int => Int => Int = (x: Int) => (y: Int) => x + y
  val add3: Int => Int = supperAdder(3)
  val invokeSupperAdder = supperAdder(3)(100)

  // curried methods (methods with multiple arg list
  def curriedFormatter(fmt: String)(x: Double): String = fmt.format(x)

  val standardFormat: (Double => String) = curriedFormatter("%4.2f") // (x: Double) => "%4.2f".format(x)
  val preciseFormat: (Double => String) = curriedFormatter("%10.8f") // (x: Double) => ""%10.8f".format(x)

  def main(args: Array[String]): Unit = {
    println(tenThousand)
    //println(tenThousand_v2(1))
    println(standardFormat(Math.PI))
    println(preciseFormat(Math.PI))
  }
}
