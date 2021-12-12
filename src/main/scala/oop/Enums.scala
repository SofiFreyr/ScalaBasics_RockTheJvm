package oop

object Enums {

  enum Permissions {
    case READ, WRITE, EXECUTE, NONE

    //add fieds/methods
    def openDocument(): Unit =
      if(this == READ) println("opening document")
      else println("not allowed")
  }

  val somePermissions: Permissions = Permissions.READ

  enum PermissionsWithBits(bits: Int) {
    case READ extends PermissionsWithBits(4) //100
    case WRITE extends PermissionsWithBits(2) // 010
    case EXECUTE extends PermissionsWithBits(1) // 001
    case NONE extends PermissionsWithBits(0) // 000
  }

  object PermissionsWithBits {
    def fromBits(bits: Int): PermissionsWithBits =
      PermissionsWithBits.NONE
  }

  // standard API
  val somePermissionsOrdinal = somePermissions.ordinal //index of the enum value
  val allPermissions = PermissionsWithBits.values //array of all possible values
  val readPermission: Permissions = Permissions.valueOf("READ") // Permissions.READ


  def main(args: Array[String]): Unit = {
    println(somePermissions)
    somePermissions.openDocument()
    println(somePermissionsOrdinal)
  }
}
