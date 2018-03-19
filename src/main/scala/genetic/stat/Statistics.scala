package genetic.stat

import genetic.common.Permutation

import scala.collection.mutable

object Statistics {
  val fitnesses: mutable.Map[Permutation, Int] = mutable.Map()

  var lastSize = 0

  def fitnessOf(x: Permutation): Int = fitnesses.get(x) match {
    case Some(value) => value
    case None =>
      fitnesses += x -> x.fitnessValue
      x.fitnessValue
  }

  def newFitnessCount: Int = {
    val res = fitnesses.size - lastSize
    lastSize = fitnesses.size
    res
  }

  private val records: mutable.Queue[String] = mutable.Queue.empty

  def addRecord(record: String): Unit = records += record

  def printRecords(): Unit = records.foreach(println)
}
