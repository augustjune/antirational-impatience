package game_solving_algorithms

import org.scalatest.FreeSpec

class StrategoBoardSpec extends FreeSpec {
  "A StrategoBoard" - {
    "should be possible to create by" - {
      "number, which indicates size of board" in {
        val size = 4
        val board = StrategoBoard(size)
        assert(size === board.size)
      }

      "string, which indicates current state of the board" in {
        val state = "_ _ _" +
                  "\n_ _ _" +
                  "\n_ _ _"
        val board = StrategoBoard(state)
        assert(state === board.toString)
      }
    }

    "should have grid filled after filling" in {
      val board = StrategoBoard(4)
      val pos = (0, 0)
      board.fill(pos)
      assert(board.isFilled(pos))
    }

    "while filling" - {
      "should give 0 points if grid is already filled" in {
        val pos = (0, 0)
        val board = StrategoBoard(4)
        board.fill(pos)
        val reward = board.fill(pos)
        assert(reward === 0)
      }

      "should give n points for filling row" in {
        val n = 4
        val board = StrategoBoard(n)
        board.fill(0, 0)
        board.fill(0, 1)
        board.fill(0, 2)
        assert(n === board.fill(0, 3))
      }

      "should give n points for filling column" in {
        val n = 3
        val board = StrategoBoard(n)
        board.fill(0, 0)
        board.fill(1, 0)
        assert(n === board.fill(2, 0))
      }

      "should give points for filling diagonal" in {
        val board = StrategoBoard("_ _ X _ _ _ _" +
                                "\n_ X _ _ _ _ _" +
                                "\n_ _ _ _ _ _ _" +
                                "\nX _ _ _ _ _ _" +
                                "\n_ X _ _ _ _ _" +
                                "\n_ _ X _ _ _ _" +
                                "\n_ _ _ _ _ _ _")
        assert(3 === board.fill(2, 0))
        assert(4 === board.fill(6, 3))
      }

      "shouldn't give points for diagonals of size 1" in {
        val board = StrategoBoard(5)
        assert(0 === board.fill(0, 0))
        assert(0 === board.fill(0, 4))
        assert(0 === board.fill(4, 0))
        assert(0 === board.fill(4, 4))
      }

      "should be able to combine points" in {
        val board = StrategoBoard("_ _ X _ _ X _ _" +
                                "\n_ _ _ X _ X _ _" +
                                "\n_ _ _ _ X X _ _" +
                                "\nX X X X X _ X X" +
                                "\n_ _ _ _ _ X X _" +
                                "\n_ _ _ _ _ X _ X" +
                                "\n_ _ _ _ _ X _ _" +
                                "\n_ _ _ _ _ X _ _")
        assert(6 + 8 + 8 === board.fill(3, 5))
      }
    }
  }
}
