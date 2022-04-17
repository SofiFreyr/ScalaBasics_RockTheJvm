package funP

import scala.util.Random

object LinearCollections {

  // Seq = well-defined ordering + indexing
  def testSeq(): Unit = {
    val aSequence = Seq(1, 2, 3, 4)
    // main API: indeax an element
    val thirdElement = aSequence.apply(2) // element 3
    // map/flatMap/filter/for

    val anIncrementedSequence = aSequence.map(_ + 1) // [2,3,4,5]
    val aFlatMappedSequence = aSequence.flatMap(x => Seq(x, x + 1)) // [1,2,2,3,3,4,4,5,5,6]
    val filteredSequence = aSequence.filter(_ % 2 == 0) // [2,4]

    // other methods
    val reversed = aSequence.reverse
    val concatenation = aSequence ++ Seq(5,6,7)
    val sortedSequence = aSequence.sorted // [1,2,3,4]
    val valSum = aSequence.foldLeft(0)(_ + _) // 10
    val valSum_v2 = aSequence.sum // 10
    val stringRep = aSequence.mkString("[",",","]") // String = "[1,2,3,4]"


    println(aSequence)
    println(concatenation)
    println(sortedSequence)
  }

  // lists
  def testLists(): Unit = {
    val aList = List(1,2,3)
    // main API - the same as in Seq
    val firstElement = aList.head
    val restOfList = aList.tail

    // appending and prepending
    val aBiggerLis = 0 +: aList :+ 4 // [0,1,2,3,4]
    val prepending = 0 :: aList // !case class ::!

    // utility methods
    val scalax5 = List.fill(5)("Scala")
  }

  // ranges
  def testRanges(): Unit = {
    val aRange: Seq[Int] = 1 to Int.MaxValue
    val aNonInclusiveRange = 1 until 10 // 10 not included
    // same Seq API
    (1 to 10).foreach(_ => println("Scala"))
  }

  // arrays
  def testArrays(): Unit = {
    val anArray = Array(1,2,3,4,5,6) // int[] in JVM
    // most Seq APIs
    // arrays are NOT Seqs
    val aSequence = anArray.toIndexedSeq
    //arrays are mutable
    anArray.update(1,999) // 1,999,3,4,5,6 !! no new array is allocated
  }

  // vectors are fast sequences for a large amount of data
  def testVectors(): Unit = {
    val aVector = Vector(1,2,3,4,5,6, 7 to 1000)
    // the same Seq API
  }

  def smallBenchmark(): Unit = {
    val maxRuns = 1_000
    val maxCapacity = 1_000_000

    def getWriteTime(collection: Seq[Int]): Double = {
      val random = new Random()
      val times = for {
        i <- 1 to maxRuns
      } yield {
        val index = random.nextInt(maxCapacity)
        val element = random.nextInt(maxCapacity)
        val currentTime = System.nanoTime()
        val updatedCollection = collection.updated(index, element)

        System.nanoTime() - currentTime
      }

      // compute average
      times.sum * 1.0 / maxRuns
    }

    val numbersList = (1 to maxCapacity).toList
    val numbersVector = (1 to maxCapacity).toVector

    println("List: " + getWriteTime(numbersList))
    println("Vector: " + getWriteTime(numbersVector))
  }

  // sets
  def testSets(): Unit = {
    val aSet = Set(1,2,3,4,5,4) // 1,2,3,4,5 -> no duplicates but also no ordering guaranteed
    // equals + hashCode = hashSet
    // main API: test if element is in the set
    val contains3 = aSet.contains(3) // true
    val contains3_v2 = aSet(3) // same: true

    // adding/removing
    val aBiggerSet = aSet + 4 // [1,2,3,4,5]
    val aSmallerSet = aSet - 4 // [1,2,3,5]

    //concatendation = unioning
    val anotherSet = Set(4,5,6,7,8)
    val muchBiggerSet = aSet ++ anotherSet // [1,2,3,4,5,6,7,8]
    val muchBiggerSet_v2 = aSet.union(anotherSet) // same
    val muchBiggerSet_v3 = aSet | anotherSet // same
    // difference
    val aDiffSet = aSet.diff(anotherSet)
    val aDiffSet_v2 = aSet -- anotherSet // same
    // intersection
    val anIntersection = aSet.intersect(anotherSet) // [4,5]
    val anIntersection_v2 = aSet & anotherSet

  }

  def main(args: Array[String]): Unit = {
    testSeq()

    smallBenchmark()
  }
}
