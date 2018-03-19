package genetic

import genetic.common.Permutation
import genetic.operations.Mixing.Mixing
import genetic.operations.Mutation.Mutation
import genetic.operations.Selection.Selection

package object operations {
  type Population = List[Permutation]

  trait GeneticOperation extends (Population => Population)

  object Same extends Selection with Mixing with Mutation {
    def apply(pop: Population): Population = pop
  }

}
