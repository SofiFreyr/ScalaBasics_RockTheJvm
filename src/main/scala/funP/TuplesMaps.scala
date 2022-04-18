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
  ).withDefaultValue(-1)

  // core API
  val phonebookHasDaniel = phonebook.contains("Daniel")
  val marysPhoneNumber = phonebook.apply("Mary") // crash with an exception if the key is not present

  // add a pair
  val newPair = "Mary" -> 678
  val newPhonebook = phonebook + newPair

  // remove a key
  val phonebookWithoutDaniel = phonebook - "Daniel" // new map without key Daniel

  // list -> map
  val linearPhonebook = List(
    "Jim" -> 555,
    "Daniel" -> 789,
    "Jane" -> 123
  )

  val phonebook_v2 = linearPhonebook.toMap // only available for List(el1, el2) === only for two elements in tuple

  // map -> linear collection
  val linearPhonebook_v2 = phonebook.toList // toSeq, toVector, toArray, toSet

  // map, flatMap, filter
  /*
      Map("Jim" -> 123, "jIM" -> 999) => Map("JIM" -> ????) -- not a good idea
  */
  val aProcesedPhonebook = phonebook.map(pair => (pair._1.toUpperCase(),pair._2))

  // filtering keys
  val noJs = phonebook.view.filterKeys(!_.startsWith("J")).toMap

  // mapping values
  val prefixNumbers = phonebook.view.mapValues(number => s"0255-$number").toMap

  // other collections can create maps
  val names = List("Bob", "James", "Mary", "Daniel", "Jim")
  val nameGroupings = names.groupBy(name => name.charAt(0)) // Map[Char, List[String]]

  def main(args: Array[String]): Unit = {
    println(phonebook)
    println(phonebookHasDaniel)
    println(marysPhoneNumber)
    println(nameGroupings)
  }
}
