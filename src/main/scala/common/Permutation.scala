package common

import common.Matrices.{FlowMatrix, RangeMatrix}

import scala.util.Random

class Permutation(flowMatrix: FlowMatrix, rangeMatrix: RangeMatrix, private val locations: Seq[Int]) {
  require(flowMatrix.size == rangeMatrix.size)
  require(flowMatrix.size == locations.size)

  lazy val fitnessValue: Int = {
    val locationMap: Map[Int, Int] = locations.zipWithIndex.toMap

    def relationPrices(currentLocation: Int, currentNum: Int): Int =
      locationMap.foldLeft(0) { case (left, (loc, num)) => left + rangeMatrix(currentLocation - 1, loc - 1) * flowMatrix(currentNum, num) }

    locationMap.foldLeft(0) { case (left, (loc, num)) => left + relationPrices(loc, num) }
  }

  def switchPair(n1: Int, n2: Int): Permutation = {
    val loc1 = locations(n1)
    create(locations.updated(n1, locations(n2)).updated(n2, loc1))
  }

  def greedyGuess: Permutation = {
    def pairSwitchedPerm: Stream[Permutation] =
      for {
        i <- locations.indices.toStream
        j <- i + 1 until locations.length
      } yield switchPair(i, j)

    pairSwitchedPerm.find(_.isBetterThen(this)) match {
      case Some(betterPerm) => betterPerm
      case None => this
    }
  }

  def crossOver(other: Permutation, num: Int): Permutation = {
    def repair(locations: Seq[Int]): Seq[Int] = {
      def replaceDuplicates(pool: List[Int], loc: List[Int]): List[Int] = loc match {
        case h :: t =>
          if (t.contains(h)) pool.head :: replaceDuplicates(pool.tail, t)
          else h :: replaceDuplicates(pool, t)
        case Nil => Nil
      }

      val missing = 1 to locations.length filterNot locations.contains
      replaceDuplicates(missing.toList, locations.toList)
    }

    create(repair(locations.take(num) ++ other.locations.drop(num)))
  }

  def randomCrossOver(other: Permutation): Permutation = crossOver(other, Random.nextInt(locations.length))

  def isBetterThen(other: Permutation): Boolean = fitnessValue < other.fitnessValue

  private def create(locations: Seq[Int]) = new Permutation(flowMatrix, rangeMatrix, locations)

  override def toString: String = locations mkString("[", ", ", "]")
}
