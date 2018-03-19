package genetic.operations

import genetic.common.Permutation
import genetic.random.CustomRandom

object Selection {

  trait Selection extends GeneticOperation

  case class Roulette() extends Selection {
    def apply(population: Population): Population = {
      val largestFitness = population.max.fitnessValue
      val range: Double = population.map(largestFitness - _.fitnessValue).sum

      val rouletteSectors = population.map((x) => (largestFitness - x.fitnessValue) / range)

      def selectOne: Permutation = population(CustomRandom.shot(rouletteSectors))

      def selectUntil(newPopSize: Int, acc: List[Permutation]): List[Permutation] =
        if (acc.length < newPopSize)
          selectUntil(newPopSize, selectOne :: acc)
        else acc

      selectUntil(population.length, Nil)
    }

    override def toString(): String = "Roulette method of selection"
  }

  case class SimpleTournament(size: Int) extends Selection {
    def apply(population: Population): Population = {
      def takeCandidate(tour: Population): Permutation = tour min

      def selectOne: Permutation =
        takeCandidate(CustomRandom.shuffle(population).take(size))

      def selectUntil(newPopSize: Int, acc: List[Permutation]): List[Permutation] =
        if (acc.length < newPopSize)
          selectUntil(newPopSize, selectOne :: acc)
        else acc

      selectUntil(population.length, Nil)
    }

    override def toString(): String = s"SimpleTournament selection with tournament size of $size"
  }

}
