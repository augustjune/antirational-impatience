package generic

import common.Permutation

abstract class GeneticPop(permutations: List[Permutation]) {

  val size: Int = permutations.length

  def best: Permutation = permutations.minBy(_.fitnessValue)

  def best(n: Int): List[Permutation] = permutations.sortBy(_.fitnessValue).take(n)

  def select(sizeProp: Double): GeneticPop

  def crossOver(sizeProp: Double): GeneticPop
}
