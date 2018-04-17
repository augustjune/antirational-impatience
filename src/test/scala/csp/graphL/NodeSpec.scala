package csp.graphL

import org.scalatest.WordSpec

class NodeSpec extends WordSpec {
  "A Node" when {

    "is single" should {
      "be legal" in {
        val singleNode = Node(0, Nil)
        assert(singleNode.isLegal)
      }
    }

    "has one neighbor" should {
      "be legal if difference with neighbor color is more then 1" in {
        val node2 = Node(2, List(0))
        assert(node2.isLegal)
      }

      "be illegal if difference with neighbor color is less then 2" in {
        val node2 = Node(1, List(0))
        assert(!node2.isLegal)
      }
    }

    "has two neighbor" should {
      "be legal if difference between neighbors is more then 0" in {
        val node3 = Node(4, List(0, 1))
        assert(node3.isLegal)
      }

      "be illegal if difference between neighbors is less then 1" in {
        val node3 = Node(4, List(0, 0))
        assert(!node3.isLegal)
      }
    }
  }

}
