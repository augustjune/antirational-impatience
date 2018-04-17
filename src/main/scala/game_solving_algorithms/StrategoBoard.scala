package game_solving_algorithms

import game_solving_algorithms.StrategoBoard._

object StrategoBoard {
  private val GRID_SEPARATION: String = " "
  private val EMPTY_GRID = "_"
  private val FILLED_GRID = "X"

  type Position = (Int, Int)

  def apply(size: Int): StrategoBoard =
    new StrategoBoard(new Array[Any](size).map(_ => new Array[Boolean](size)))

  def apply(input: String): StrategoBoard =
    new StrategoBoard(input.split("\n").map(_.split(GRID_SEPARATION).map(_ != EMPTY_GRID)))
}

class StrategoBoard private(grids: Array[Array[Boolean]]) {
  assert(grids.forall(_.length == grids.length))

  val size: Int = grids.length

  def isFilled(position: Position): Boolean = grids(position._1)(position._2)

  def isEmpty(position: Position): Boolean = !isFilled(position)

  def fill(position: Position): Int = {
    val reward = calculateReward(position)
    grids(position._1)(position._2) = true
    reward
  }

  def calculateReward(pos: Position): Int =
    if (isFilled(pos)) 0
    else calculatePoints(pos._1, pos._2)


  private def calculatePoints(row: Int, col: Int): Int =
    pointsForRow(row) + pointsForCol(col) +
      pointsForUpDiag(row, col) + pointsForDownDiag(row, col)

  private def pointsForRow(row: Int): Int = {
    val completesRow = grids(row).count(!_) == 1
    pointsForStraight(completesRow)
  }

  private def pointsForCol(col: Int): Int = {
    val completesColumn = (0 until size).map(grids(_)(col)).count(!_) == 1
    pointsForStraight(completesColumn)
  }

  private def pointsForUpDiag(row: Int, col: Int): Int = {
    val diagonalLength = size - math.abs(size - 1 - row - col)
    val completesUpDiagonal = {
      def gridsToFillUpDiagonal = {
        val sum = row + col
        if (sum < size) (0 to sum).map(c => grids(sum - c)(c)).count(!_)
        else (size - 1 until sum - size by -1).map(r => grids(r)(sum - r)).count(!_)
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
        if (col > row) (0 until size - diff).map(r => grids(r)(r + diff)).count(!_)
        else (0 until size - diff).map(c => grids(c + diff)(c)).count(!_)
      }

      gridsToFillDownDiagonal == 1
    }
    pointsIf(diagonalLength != 1 && completesDownDiagonal, diagonalLength)
  }

  private def pointsForStraight(pred: Boolean): Int = pointsIf(pred, size)

  private def pointsIf(pred: Boolean, reward: Int): Int =
    if (pred) reward
    else 0

  def freeMoves: IndexedSeq[(Int, Int)] = for {
    row <- 0 until size
    col <- 0 until size
    if isEmpty(row, col)
  } yield (row, col)

  def isEnded: Boolean = grids.exists(row => row.exists(!_))

  override def toString: String = grids.map(_.map(if (_) FILLED_GRID else EMPTY_GRID).mkString(GRID_SEPARATION)).mkString("\n")
}
