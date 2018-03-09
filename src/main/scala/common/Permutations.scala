package common

import common.Matrices.{FlowMatrix, RangeMatrix}

import scala.util.Random

class Permutations(flowMatrix: FlowMatrix, rangeMatrix: RangeMatrix) {
  require(flowMatrix.size == rangeMatrix.size)

  def create(locations: Seq[Int]): Permutation = new Permutation(flowMatrix, rangeMatrix, locations)

  def random: Permutation = create(Random.shuffle(1 to flowMatrix.size))
}
