package funP

import scala.util.Random

object Options {

  // options = "collections" with at most one value
  val anOption: Option[Int] = Option(42)
  val anEmptyOption: Option[Int] = Option.empty

  val aPresentValue: Option[Int] = Some(4)
  val anEmptyOption_v2: Option[Int] = None

  // "standard API"
  val isEmpty = anOption.isEmpty
  val innerValue = anOption.getOrElse(90)
  val anotherOption = Option(412312)
  val aChainedOption = anEmptyOption.orElse(anotherOption)


  // map, flatmap, filter, for
  val anIncrementedOption = anOption.map(_ + 1) //Some(43)
  val aFilteredOption = anIncrementedOption.filter(_ % 2 == 0) // None
  val aFlatMappedOption = anOption.flatMap(value => Option(value*10)) // Some(430)


  // work with unsafe APIs
  def unsafeMethod(): String = null
  def fallbackMethod(): String = "some valid result"

  // defensive style
  val stringLength = if (unsafeMethod() == null) -1 else unsafeMethod().length


  // option-style: no need for null check
  val stringLengthOption = Option(unsafeMethod()).map(_.length)

  // use-case for orElse
  val someResult = Option(unsafeMethod()).orElse(Option(fallbackMethod()))

  // DESIGN
  def betterUnsafeMethod(): Option[String] = None
  def betterFallbackMethod(): Option[String] = Some("A valid results")
  def betterChain = betterUnsafeMethod().orElse(betterFallbackMethod())

  // example: Map.get
  val phonebook = Map(
    "Daniel" -> 1234
  )
  val marysPhoneNumber = phonebook.get("Mary")
  // no need to crash, check for nulls or if mary is present

  /**
   * Excercise
   *
   * Get the host and port from the map
   *  try to open a connection
   *  print "Conn succesful"
   *  or "Conn failed"
   */

  val config: Map[String,String] = Map(
    // comes from elsewhere
    "host" -> "178.192.0.1",
    "port" -> "8081"
  )

  class Connection {
    def connect(): String = "Connection succesful"
  }

  object Connection {
    val random = new Random()

    def apply(host: String, port: String): Option[Connection] =
      if (random.nextBoolean()) Some(new Connection)
        else None
  }


  // my try
  val connectionOpened: Option[Connection] = Connection(config("host"), config("port"))
  def connect() = connectionOpened.map(_.connect()).getOrElse("Connection failed")


  // how to do it correctly
  val host = config.get("host")
  val port = config.get("port")

  val connection = host.flatMap(h => port.flatMap( p => Connection(h,p))).map(_.connect()).getOrElse("Conn failed")

  // for-comprehension
  val connStatus_v3 = for {
    h <- config.get("host")
    p <- config.get("port")
    conn <- Connection(h, p)
  } yield Option(conn.connect()).getOrElse("Conn failed")

  def main(args: Array[String]): Unit = {
    println(stringLength)

    println(connect())

  }
}
