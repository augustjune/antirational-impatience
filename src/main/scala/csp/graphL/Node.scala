package csp.graphL

case class Node(color: Color, neighbors: List[Color]) {

  def isLegal: Boolean = selfCheck && neighborsCheck

  def selfCheck: Boolean = neighbors.forall(n => Math.abs(n - color) > 1)

  def neighborsCheck: Boolean = {
    def checkSingle(candidate: Color, others: List[Color]): Boolean = others match {
      case Nil => true
      case h :: t => Math.abs(candidate- h) > 0 && checkSingle(candidate, t)
    }
    def checkAll(neighborList: List[Color]): Boolean = neighborList match {
      case Nil => true
      case h :: t => checkSingle(h, t) && checkAll(t)
    }

    checkAll(neighbors)
  }
}
