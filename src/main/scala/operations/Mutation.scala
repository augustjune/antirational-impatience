package operations

import common.Permutation
import random.CustomRandom

object Mutation {

  trait Mutation extends GeneticOperation

  case class SingleChromosomeMutation(chance: Double) extends Mutation {
    def apply(population: Population): Population = population.map(
      if (CustomRandom.shot(chance)) _.mutate else identity)

    override def toString(): String = s"SingleChromosomeMutation of chance $chance"
  }


  case class ComplexMutation(chance: Double, complexity: Double) extends Mutation {
    def apply(population: Population): Population = population.map(
      if (CustomRandom.shot(chance)) mutateSingleChromosome(complexity)
      else identity
    )

    private def mutateSingleChromosome(complexity: Double)(permutation: Permutation): Permutation = {
      val mutated = permutation.mutate
      if (CustomRandom.shot(complexity)) mutateSingleChromosome(complexity)(mutated)
      else mutated
    }

    override def toString(): String = s"ComplexMutation with single permutation mutation chance $chance and mutation complexity $complexity"
  }

}
