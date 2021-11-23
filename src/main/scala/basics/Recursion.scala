package basics

import scala.annotation.tailrec

object Recursion {

  // Repetition = recursion in Scala

  def sumUntil(n: Int): Int = {
    if (n <= 0) 0
    else n + sumUntil(n - 1)
  }

  @tailrec
  def sumUntilTail(n: Int, sum: Int = 0): Int = {
    if (n <= 0) sum
    else sumUntilTail(n - 1, sum + n)
  }

  /**
   * Excercises
   * 1. Concatenate a string n times
   * 2. Fibonacci function, tail recursive
   * 3. Is isPrime function tail recursive (yes)
   *
   *
   * 1,1,2,3,5,8,13,21,34
   */
  @tailrec
  def concatenateString(s: String, n: Int, acc: String = ""): String = {
    if (n <= 0) acc
    else concatenateString(s, n - 1, acc + s)
  }

  def fibonacci(n: Int): Int = {
    if (n <= 2) return 1

    @tailrec
    def fibonacciHelper(k: Int, prev1: Int, prev2: Int): Int = {
      if (k >= n) prev1
      else fibonacciHelper(k + 1, prev1 + prev2, prev1)
    }

    fibonacciHelper(2, 1, 1)
  }


  def main(args: Array[String]): Unit = {
    println(sumUntil(10))
    println(sumUntilTail(10))
    println(concatenateString("YeeHaw ", 7))
    println(fibonacci(1))
  }
}
