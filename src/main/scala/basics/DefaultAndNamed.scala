package basics

import scala.annotation.tailrec

object DefaultAndNamed {

  @tailrec
  def sumUntilTailrec(x: Int, accumulator: Int = 0): Int = {
    if (x <= 0) accumulator
    else sumUntilTailrec(x - 1, accumulator + x)
  }

  val sumUntil100 = sumUntilTailrec(100) // additional arg passed automatically

  // when you use a function most of the time with the same value = default arguments
  def savePicture(dirPath: String, name: String, format: String = "jpg", width: Int = 1920, height: Int = 1080): Unit = {
    println(s"Saving picture in format $format in path $dirPath with $width/$height")
  }

  def main(args: Array[String]): Unit = {
    savePicture("/users/sofie/photos", "myphoto")
    savePicture("/users/sofie/photos", "myphoto", width = 1080)
  }
}
