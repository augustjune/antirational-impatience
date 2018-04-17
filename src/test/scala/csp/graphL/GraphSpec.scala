package csp.graphL

import org.scalatest.WordSpec

class GraphSpec extends WordSpec {

  "A graph is legal" in {
    val g = Graph(Vector(
      Vector(0, 2, 4, 6, 8),
      Vector(4, 6, 8, 0, 2),
      Vector(8, 0, 2, 4, 6),
      Vector(2, 4, 6, 8, 0),
      Vector(0, 8, 1, 3, 5),
    ))

    assert(g.isLegal)
  }

}
