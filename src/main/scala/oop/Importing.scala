package oop

import practice.Predicate

object Importing {

  // packages = "folders"

  //fully qualified name
  val aList: practice.LList[Int] = ???

  // import
  import practice.LList
  val anotherList: LList[Int] = ???

  //importing under an alias
  import java.util.{List => JList}
  import java.util.Map as JMap
  val aJavaList: JList[Int] = ???
  val aJavaMap: JMap[Int,Int] = ???

  //importing everything
  import practice.*
  val aPredicate: Predicate[Int] = ???

  // import multiple symbols
  import PhysicsConstants.{SPEED_OF_LIGHT,EARTH_GRAVITY}
  val c = SPEED_OF_LIGHT

  // import everything but something
  object PlayingPhysics {
    import PhysicsConstants.{PLANK as _, *}
    // val plank = PLANK  <--- doesn't work
    val light = SPEED_OF_LIGHT
  }


  // exports
  class PhysicsCalc {
    import PhysicsConstants.*

    def computePhotoneEnergy(wavelength: Double): Double =
      PLANK / wavelength
  }

  object ScienceApp {
    val physicsCalc = new PhysicsCalc

    export physicsCalc.computePhotoneEnergy

    def computeEquivalentMass(wavelength: Double): Double =
      computePhotoneEnergy(wavelength) / (SPEED_OF_LIGHT * SPEED_OF_LIGHT)
  }

  def main(args: Array[String]): Unit = {

  }
}


object PhysicsConstants {
  // constants
  val SPEED_OF_LIGHT = 299792458
  val PLANK = 6.62e-34
  val EARTH_GRAVITY = 9.8
}