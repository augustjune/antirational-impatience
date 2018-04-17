package csp.nQueens

class ForwardChecking[T] {
  def apply(domains: List[Domain[T]]): Solution[T] = {

    def helper(domains: List[List[T]]): List[List[T]] = domains match {
      case Nil => Nil
      case curr :: rest =>
        if (curr.isEmpty) Nil
        else for {
          x <- curr
          next <- helper(rest)
        } yield x :: next
    }

    helper(domains.map(_.toList)).find(_.length == domains.length).get
  }

}
