package csp

import csp.problems.NQueens

import scala.concurrent.duration.{Duration, NANOSECONDS}
import scala.util.Random

object Util {
  def main(args: Array[String]): Unit = {
    val problem = new NQueens(5)
    val solver = new BackTracking[Int]

    printSolution(solver(problem))
    println(BackTracking.counter)
  }

  def printSolution(solution: Option[Solution[Int]]): Unit = solution match {
    case None => println("No possible solution for this N argument")
    case Some(value) => printQueens(value)
  }

  def printTime(duration: Duration): Unit = {
    if (duration.toNanos < 2000) println(s"Nanoseconds took: ${duration.toNanos}")
    else if(duration.toMillis < 2000) println(s"Milliseconds took: ${duration.toMillis}")
    else if (duration.toSeconds < 1000) println(s"Seconds took: ${duration.toSeconds}")
    else println(s"Minutes took: ${duration.toMinutes}")
  }

  def printQueens(positions: List[Int]): Unit = {
    val emptyRow = "_" * positions.length
    positions.map(pos => emptyRow.updated(pos, "*").mkString(" ")).foreach(println)
    println("===========================================")
  }

  def randomQueens(queens: Int): Stream[List[Int]] = Random.shuffle((0 until queens).toList) #:: randomQueens(queens)

  def measure[R](fun: => R): (Duration, R) = {
    val start = System.nanoTime()
    val res = fun
    (Duration(System.nanoTime() - start, NANOSECONDS), res)
  }
}
