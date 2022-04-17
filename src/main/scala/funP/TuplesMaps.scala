package funP

object TuplesMaps {
  // tuples = finite ordered "lists" / group of values under the same value
  val aTuple = (1, "sad") // Tuple2[Int,String]
  val firstField = aTuple._1
  val aCopiedTuple = aTuple.copy(_1 = 54) // _1 is changed to 54

  // tuples of 2 elements
  val aTuple_v2 = 1 -> "sad" // IDENTICAL to (2, "sad")

  // maps: keys -> values
  val aMap = Map()

  val phonebook: Map[String, Int] = Map(
    "Jim" -> 555,
    "Daniel" -> 789,
    "Jane" -> 123
  )

  def main(args: Array[String]): Unit = {

  }
}
