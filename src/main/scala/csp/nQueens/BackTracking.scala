package csp.nQueens

class BackTracking[T] extends Solver[T] {

  def apply(initialDomains: List[Domain[T]], isCompatible: Solution[T] => Boolean): Option[Solution[T]] = {
    val domains: Array[Domain[T]] = initialDomains.toArray
    var solution: Solution[T] = Nil

    var varIndex = 0
    while (varIndex < initialDomains.length) {
      domains(varIndex) = domains(varIndex).filter(value => isCompatible(value :: solution))

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
