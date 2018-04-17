package csp.graphL

import scala.collection.mutable

object Graph {
  def apply(tabNodes: Vector[Vector[Color]]): Graph = {
    def constructNode(row: Int, col: Int): Node = {
      val c = tabNodes(row)(col)
      val neighbors: mutable.MutableList[Color] = mutable.MutableList.empty
      if (row > 0) neighbors += tabNodes(row - 1)(col)
      if (row < tabNodes.size - 2) neighbors += tabNodes(row + 1)(col)
      if (col > 0) neighbors += tabNodes(row)(col - 1)
      if (col < tabNodes.size - 2) neighbors += tabNodes(row)(col + 1)
      Node(c, neighbors.toList)
    }

    new Graph(
      {for {
          row <- tabNodes.indices
          col <- tabNodes.indices
        } yield constructNode(row, col)
      } toSet
    )
  }
}

class Graph(nodes: Set[Node]) {
  def isLegal: Boolean = nodes.forall(_.isLegal)

  def size: Int = nodes.size
}
