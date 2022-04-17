package patternMatching
import practice.*
object AllThePatterns {

  object MySingleton

  // 1 - constants
  val someValue: Any = "Scala"
  val constants: String = someValue match {
    case 42 => "a number"
    case "Scala" => "THE Scala"
    case true => "the truth"
    case MySingleton => "a singleton"
  }

  // 2 - match anything
  val matchAhythingVar = someValue match {
    case something => s"I've matched anything, it's $something" // matching a var
  }
  val matchAnything = someValue match {
    case _ => "I can match anything at all"
  }

  // 3 - tuples
  val aTuple = (1,4)
  val tupleMatch = aTuple match {
    case (1, something) => s"A tuple with 1 and $something"
    case (something, 2) => s"A tuple with $something and 2"
  }

  // PM structures can be NESTED

  val nestedTuple= (1, (2, 3))
  val matchNestedTuple = nestedTuple match {
    case (_, (2, v)) => "A nested tuple ..."
  }

  // 4 - nested case classes
  val aList: LList[Int] = Cons(1, Cons(2, Empty()))
  val matchList = aList match {
    case Empty() => "an empty list"
    case Cons(head, Cons(_, tail)) => s"a non-empty list starting with $head"
  }

  val anOption: Option[Int] = Option(2)
  val matchOption = anOption match {
    case None => "an empty option"
    case Some(value) => s"non-empty, got $value"
  }

  // 5 - list patterns
  val aStandartList = List(1,2,3,4,5)
  val matchStandardList = aStandartList match {
    case List(1, _, _, _) => "a list with 1 and exactly 3 more elements"
    case List(1, _*) => "List starting with 1"
    case List(1, 2, _) :+ 42 => "A list ending in 42 "
    case 1 :: tail => "Also a list starting with 1 (with infix usage)" + "deconstructed list"
  }

  // 6 - type specifiers
  val unknown: Any = 2
  val matchTyped = unknown match {
    case anInt: Int => s"I matched an int, I can add 2 to it: ${anInt + 2}"
    case aString: String => s"I matched a String" + aString
    case _: Double => s"I matched a Double"
  }

  // 7 - name binding
  val bindingNames = aList match {
    case Cons(head, rest @ Cons(_, tail)) => s"Can use $rest"
  }

  // 8 - chained patterns
  val multiMatch = aList match {
    case Empty() | Cons(0, _) => "an empty list to me"
  }

  // 9 - if guards
  val secondElementSpetial = aList match {
    case Cons(_, Cons(special, _)) if special > 5 => "second element is big enough"
  }

  /**
   * Example
   */

  val aSimpleInt = 45
  val isEven_bad = aSimpleInt match {
    case n if n % 2 == 0 => true
    case _ => false
  }

  // heavy anti-pattern !!
  val isEven_bad_v2 = if (aSimpleInt % 2 == 0) true else false

  // better way
  val isEvenBetter = aSimpleInt % 2 == 0


  /**
   * Excercise (trick)
   */

  val numbers: List[Int] = List(1,2,3,4)
  val numbersMatch = numbers match {
    case listOfString: List[String] => "list of strings"
    case listOfInts: List[Int] => "a list of Numbers"
  } // prints "list of strings"

  /*
    PM runs at runtime
      - uses reflection
      - generic types are erased at runtime
        List[String] => List
        List[Int] => List
        Function1[Int

  */

  def main(args: Array[String]): Unit = {

  }
}
