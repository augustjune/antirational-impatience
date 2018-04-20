package game_solving_algorithms

abstract class StrategoPlayer(val name: String, board: StrategoBoard) {
  private var points = 0

  def score: Int = points

  def addPoints(add: Int): Unit = points += add

  def move(): (Int, Int)

  override def toString: String = name
}
