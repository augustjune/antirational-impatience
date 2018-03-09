import common._
import generic.Population
import random.RandomSearch

object Utils {
  def main(args: Array[String]): Unit = {
    val source = new MatrixSource("C:\\Users\\Jura\\Desktop\\matrix.txt")
    val (range, flow) = source.toMatrices
    val permutations = new Permutations(range, flow)
    val random  = new RandomSearch(permutations)

    val initialPop = new Population(random.candidates.take(1000).toList)
    printCandidate(initialPop.best)
    val newPop = initialPop.selectByTournament(4, 0.8)(0.5).randCrossover(0, 0.5, 4)
    printCandidate(newPop.best)
  }

  def printCandidate(candidate: Permutation): Unit = {
    println(s"$candidate = ${candidate.fitnessValue}")
  }
}
