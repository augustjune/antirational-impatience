package game_solving_algorithms

class StrategoGame(board: StrategoBoard, player1: StrategoPlayer, player2: StrategoPlayer) {
  var result: String = _

  def play(): Unit =
    while (!board.isEnded) {
      player1.move()
      player2.move()
    }
}
