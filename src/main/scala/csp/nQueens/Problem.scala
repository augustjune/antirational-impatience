package csp.nQueens

trait Problem[T] {

  def variableCount: Int

  def initialDomains: List[Domain[T]]

  def isCompatible(solution: Solution[T]): Boolean

  def isSolution(solution: Solution[T]): Boolean
}
