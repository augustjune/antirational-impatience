package genetic.common

import genetic.common.matrices._
import genetic.random.CustomRandom


class Permutations(flowMatrix: FlowMatrix, rangeMatrix: RangeMatrix) {
  require(flowMatrix.size == rangeMatrix.size)

  def create(locations: Seq[Int]): Permutation = new Permutation(flowMatrix, rangeMatrix, locations)

  def random: Permutation = create(CustomRandom.shuffle(1 to flowMatrix.size))

  def candidates: Stream[Permutation] = random #:: candidates
}
