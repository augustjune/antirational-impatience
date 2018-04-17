package csp.latinMatrix

import genetic.common.matrices.SquareMatrix

class LatinMatrix(values: Vector[Vector[Int]]) {
  require(values.forall(_.length == values.length))

  private val size = values.length

  def isLegal: Boolean = {
    values.forall(_.distinct.size == size)

  }
}
