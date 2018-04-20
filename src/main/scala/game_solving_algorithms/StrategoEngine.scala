package game_solving_algorithms

import game_solving_algorithms.StrategoBoard.Position

trait StrategoEngine {
  def tiles: Array[Array[Boolean]]

  def size: Int = tiles.length

  def calculateReward(pos: Position): Int =
    if (tiles(pos._1)(pos._2)) 0
    else calculatePoints(pos._1, pos._2)


  private def calculatePoints(row: Int, col: Int): Int =
    pointsForRow(row) + pointsForCol(col) +
      pointsForUpDiag(row, col) + pointsForDownDiag(row, col)

  private def pointsForRow(row: Int): Int = {
    val completesRow = tiles(row).count(!_) == 1
    pointsForStraight(completesRow)
  }

  private def pointsForCol(col: Int): Int = {
    val completesColumn = (0 until size).map(tiles(_)(col)).count(!_) == 1
    pointsForStraight(completesColumn)
  }

  private def pointsForUpDiag(row: Int, col: Int): Int = {
    val diagonalLength = size - math.abs(size - 1 - row - col)
    val completesUpDiagonal = {
      def gridsToFillUpDiagonal = {
        val sum = row + col
        if (sum < size) (0 to sum).map(c => tiles(sum - c)(c)).count(!_)
        else (size - 1 until sum - size by -1).map(r => tiles(r)(sum - r)).count(!_)
      }

      gridsToFillUpDiagonal == 1
    }

    pointsIf(diagonalLength != 1 && completesUpDiagonal, diagonalLength)
  }

  private def pointsForDownDiag(row: Int, col: Int): Int = {
    val diagonalLength = size - Math.abs(row - col)
    val completesDownDiagonal = {
      def gridsToFillDownDiagonal = {
        val diff = math.abs(row - col)
        if (col > row) (0 until size - diff).map(r => tiles(r)(r + diff)).count(!_)
        else (0 until size - diff).map(c => tiles(c + diff)(c)).count(!_)
      }

      gridsToFillDownDiagonal == 1
    }
    pointsIf(diagonalLength != 1 && completesDownDiagonal, diagonalLength)
  }

  private def pointsForStraight(pred: Boolean): Int = pointsIf(pred, size)

  private def pointsIf(pred: Boolean, reward: Int): Int =
    if (pred) reward
    else 0


}
