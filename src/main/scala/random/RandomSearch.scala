package random

import common.{Permutation, Permutations}

class RandomSearch(permutations: Permutations) {

  def candidates: Stream[Permutation] = permutations.random #:: candidates

  def bestOf(iterations: Int): Permutation =
    candidates.take(iterations).minBy(_.fitnessValue)
}
