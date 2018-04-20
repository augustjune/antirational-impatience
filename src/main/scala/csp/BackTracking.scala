package csp

object BackTracking {
  var counter = 0
}

class BackTracking[T] extends Solver[T] {

  def apply(problem: Problem[T]): Option[Solution[T]] = {
    val initialDomains = problem.initialDomains
    val domains: Array[Domain[T]] = initialDomains.toArray
    var solution: Solution[T] = Nil

    var varIndex = 0
    while (varIndex < initialDomains.length) {
      BackTracking.counter += 1
      domains(varIndex) = domains(varIndex).filter(value => problem.isCompatible(value :: solution))

      if (domains(varIndex).isEmpty) {
        if (varIndex == 0) return None
        solution = solution.tail
        domains(varIndex) = initialDomains(varIndex)
        varIndex = varIndex - 1
        domains(varIndex) = domains(varIndex).tail
      } else {
        solution = domains(varIndex).head :: solution
        varIndex = varIndex + 1
      }
    }

    Some(solution.reverse)
  }

}
