package patternMatching

object ImperativeProgramming {

  val meaningOfLife: Int = 42
  // meaningOfLife = 43 <- failure

  var aVariable = 99
  aVariable = 100 // var variables can be reassigned
  //aVariable = "Scala"  <- failure because type incompatibility

  aVariable += 10 // aVariable = aVariable + 10
  //aVariable++ <- failure

  // loops too
  def testLoop(): Unit = {
    var i = 0
    while (i < 10) {
      println(s"Counter at ${i}")
      i += 1
    }
  }

  // using imperative scala = bad :D

  val anExpression = aVariable += 10

  def main(args: Array[String]): Unit = {
    testLoop()
    println(anExpression)
  }
}
