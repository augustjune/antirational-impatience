package csp.problems

import csp.{Domain, Problem, Solution}

class LatinMatrix(size: Int) extends Problem[Int]{
  def variableCount: Int = size

  def initialDomains: List[Domain[Int]] = List.fill(size)((1 to size).toStream)

  def isCompatible(solution: Solution[Int]): Boolean = ???

  def isSolution(solution: Solution[Int]): Boolean = ???

  def removeConflicts(domains: List[Domain[Int]], el: Int): List[Domain[Int]] = ???
}
