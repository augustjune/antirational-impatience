package csp

object ForwardChecking {
  var counter = 0
}

class ForwardChecking[T] extends Solver[T] {
  def apply(problem: Problem[T]): Option[Solution[T]] = {
    def helper(domains: List[Domain[T]], acc: List[T]): Option[Solution[T]] ={
      ForwardChecking.counter += 1
      if (domains.isEmpty) Some(acc)
      else domains.head.map(el => helper(problem.removeConflicts(domains, el).tail, el :: acc)).find(_.isDefined).flatten
    }

    helper(problem.initialDomains, Nil).map(_.reverse)
  }
}
