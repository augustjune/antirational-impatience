package csp.graphL

import scala.util.Random

class RandomSearch {
  def findAnswers(n: Int, s: Int): Stream[Vector[Vector[Int]]] = {
    randomMatrices(n, s).filter(Graph(_).isLegal)
  }

  private def createRandomMatrix(n: Int, s: Int): Vector[Vector[Int]] = {
    {
      for (i <- 0 until n) yield Seq.fill(n)(Random.nextInt(s)).toVector
    }.toVector
  }

  private def randomMatrices(n: Int, s: Int): Stream[Vector[Vector[Int]]] = {
    createRandomMatrix(n, s) #:: randomMatrices(n, s)
  }

}
