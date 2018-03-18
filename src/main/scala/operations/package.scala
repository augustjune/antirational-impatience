import common.Permutation
import operations.Mixing.Mixing
import operations.Mutation.Mutation
import operations.Selection.Selection

package object operations {
  type Population = List[Permutation]

  trait GeneticOperation extends (Population => Population)

  object Same extends Selection with Mixing with Mutation {
    def apply(pop: Population): Population = pop
  }

}
