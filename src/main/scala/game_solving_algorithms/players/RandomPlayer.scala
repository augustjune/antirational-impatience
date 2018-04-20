package game_solving_algorithms.players

import game_solving_algorithms.{StrategoPlayer, StrategoBoard}

import scala.util.Random

class RandomPlayer(name: String, game: StrategoBoard) extends StrategoPlayer(name, game) {

  def move(): (Int, Int) = {
    val possibleMoves = game.freeMoves
    possibleMoves(Random.nextInt(possibleMoves.size))
  }
}
