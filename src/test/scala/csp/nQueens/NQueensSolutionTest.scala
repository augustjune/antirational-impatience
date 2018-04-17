package csp.nQueens

import org.scalatest.WordSpec

class NQueensSolutionTest extends WordSpec {
  "A List of indexes" should {
    "be solution" when {
      "is empty" in {
        assert(NQueensSolution.isCompatible(List()))
      }
      "is correct" in {
        assert(NQueensSolution.isCompatible(List(2, 0, 3, 1)))
      }
    }
    "not two indexes in one column" when {
      "has same indexes" in {
        assert(!NQueensSolution.isCompatible(List(0, 0)))
      }

      "has two indexes incorrect by diagonal" in {
        assert(!NQueensSolution.isCompatible(List(0, 1)))
        assert(!NQueensSolution.isCompatible(List(1, 0)))
      }
    }
  }

}
