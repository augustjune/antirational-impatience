package game_solving_algorithms

abstract class StrategoPlayer(name: String, board: StrategoBoard) {
  private var points = 0

  def score: Int = points

  def addPoints(add: Int): Unit = points += add

  def performStep(): Unit

  override def toString: String = name
}
