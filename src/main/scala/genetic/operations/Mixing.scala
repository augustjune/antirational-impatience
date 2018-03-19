package genetic.operations

import genetic.common.Permutation
import genetic.random.CustomRandom

object Mixing {

  trait Mixing extends GeneticOperation

  case class ClassicCrossover(parentChance: Double) extends Mixing {
    def apply(population: Population): List[Permutation] = {
      def iter(pool: List[Permutation], acc: List[Permutation]): List[Permutation] = pool match {
        case Nil | List(_) => acc
        case p1 :: p2 :: tail =>
          if (CustomRandom.shot(parentChance)) iter(tail, p2 :: p1 :: acc)
          else iter(tail, p1.randomCrossOver(p2) :: p2.randomCrossOver(p1) :: acc)
      }

      iter(population, Nil)
    }

    override def toString(): String = s"ClassicCrossover with parent stay chance $parentChance"
  }

}
