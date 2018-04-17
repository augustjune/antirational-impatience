package game_solving_algorithms.players

import game_solving_algorithms.{StrategoPlayer, StrategoBoard}

import scala.util.Random

class RandomPlayer(name: String, game: StrategoBoard) extends StrategoPlayer(name, game) {

  def performStep(): Unit = {
    val possibleMoves = game.freeMoves
    addPoints(game.fill(possibleMoves(Random.nextInt(possibleMoves.size))))
  }
}
