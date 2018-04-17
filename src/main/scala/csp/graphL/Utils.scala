package csp.graphL

import scala.concurrent.duration.{Duration, NANOSECONDS}

object Utils {

  val randomSearch = new RandomSearch

  def main(args: Array[String]): Unit = {
    measure(5, List(20, 18, 16, 15, 14, 13, 12, 11, 10))
  }

  def measure(n: Int, ss: List[Int]): Unit = {
    ss.foreach { i=>
      val (d1, r1) = measureTime(randomSearch.findAnswers(n, i).head)
      println(s"Kratka $n x $n dla $i kolorow: ${d1.toMillis} milisekund")
      printRes(r1)

    }
  }

  def measureTime[R](f: => R): (Duration, R) = {
    val start = System.nanoTime()
    val res = f
    val end = System.nanoTime()
    (Duration(end - start, NANOSECONDS), res)
  }

  def printRes(res: Vector[Vector[Int]]): Unit = {
    println(res.map(_.mkString(" - ")).mkString("\n"))
  }
}
