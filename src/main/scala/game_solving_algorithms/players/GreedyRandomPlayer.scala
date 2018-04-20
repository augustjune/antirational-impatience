package game_solving_algorithms.players

import game_solving_algorithms.{StrategoBoard, StrategoPlayer}

class GreedyRandomPlayer(name: String, game: StrategoBoard) extends RandomPlayer(name, game) {

  override def move(): (Int, Int) = {
    val move = game.freeMoves.maxBy(game.calculateReward)
    if (game.calculateReward(move) > 0) move
    else super.move()
  }
}
