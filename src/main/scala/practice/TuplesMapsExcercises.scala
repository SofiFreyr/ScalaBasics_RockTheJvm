package practice

import scala.annotation.tailrec
import scala.collection.immutable

object TuplesMapsExcercises {

  /**
   * Social network = Map[String, Set[String] ]
   * Friend relationships are Mutual
   *
   * - add a person
   * - remove a person from the network
   * - add friend relationship (network, a, b)
   * - unfriend
   *
   *
   * - number of friends of a person
   * - who has the most friends
   * - how many people have NO friends
   * + if there is a social connection between two people (direct of not)
   *
   * Daniel <-> Mary <-> Jane <-> Tom >> Daniel, Tom = true
   */

  val defaultNetwork = Map("Jane" -> Set("Pumpkin", "Mary"), "Mary" -> Set(), "Pumpkin" -> Set("Jane"), "Henry" -> Set())

  def addPerson(network: Map[String, Set[String]], newPerson: String): Map[String, Set[String]] = {
    network + (newPerson -> Set.empty)
  }

  def removePerson(network: Map[String, Set[String]], personToRemove: String): Map[String, Set[String]] = {
    (network - personToRemove).map(person => person._1 -> (person._2 - personToRemove))
  }

  def addFriendConnection(network: Map[String, Set[String]], firstPerson: String, secondPerson: String): Map[String, Set[String]] = {
    network.map {
      case name if name._1 == firstPerson => name._1 -> (name._2 + secondPerson)
      case name if name._1 == secondPerson => name._1 -> (name._2 + firstPerson)
      case x => x
    }
  }

  def getNumberOfFriends(network: Map[String, Set[String]], person: String): Int = {
    network(person).size
  }

  def whoHasMostFriends(network: Map[String, Set[String]]): String = {
    network.maxBy(person => person._2.size)._1
  }

  def howManyPeopleHaveNoFriends(network: Map[String, Set[String]]): Int = {
    network.count(_._2.isEmpty)
  }

  def checkIfConnectionExists(network: Map[String, Set[String]], firstPerson: String, secondPerson: String): Boolean = {
    if(network(firstPerson).contains(secondPerson)) true
    else {
      @tailrec
      def search(discoveredPeople: Set[String], consideredPeople: Set[String]): Boolean = {
        if (discoveredPeople.isEmpty) false
        else {
          val person = discoveredPeople.head
          val personsFriends = network(person)
          if (personsFriends.contains(secondPerson)) true
          else search(discoveredPeople - person ++ personsFriends -- consideredPeople, consideredPeople + person)
        }
      }

      search(network(firstPerson), Set())
    }
  }


  def main(args: Array[String]): Unit = {
    println(addFriendConnection(defaultNetwork, "Jane", "Mary"))
    println(addPerson(defaultNetwork, "Harry"))
    println(removePerson(defaultNetwork, "Jane"))
    println("Add friend" + addFriendConnection(defaultNetwork, "Jane", "Mary"))
    println(getNumberOfFriends(defaultNetwork,"Mary"))
    println(whoHasMostFriends(defaultNetwork))
    println(howManyPeopleHaveNoFriends(defaultNetwork))
    println(checkIfConnectionExists(defaultNetwork, "Jane", "Pumpkin"))
  }
}
