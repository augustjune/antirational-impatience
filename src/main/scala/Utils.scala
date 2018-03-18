import common.Permutation
import engines.{GeneticEngine, LoggedGeneticEngine}
import logging.Notepad
import operations.Mixing.ClassicCrossover
import operations.Mutation.{ComplexMutation, SingleChromosomeMutation}
import operations.Selection.{Roulette, SimpleTournament}

import scala.concurrent.duration.{Duration, _}

object Utils {
  def main(args: Array[String]): Unit = {
    val testEngines: List[LoggedGeneticEngine] = List(
      LoggedGeneticEngine(Roulette(), ClassicCrossover(0.5), SingleChromosomeMutation(0.7), Notepad("C:\\Users\\Jura\\Desktop\\GA logs\\1.txt")),
      LoggedGeneticEngine(SimpleTournament(5), ClassicCrossover(0.5), SingleChromosomeMutation(0.7), Notepad("C:\\Users\\Jura\\Desktop\\GA logs\\2.txt")),
      LoggedGeneticEngine(SimpleTournament(10), ClassicCrossover(0.3), ComplexMutation(0.3, 0.9), Notepad("C:\\Users\\Jura\\Desktop\\GA logs\\3.txt")))


    val bench = new Benchmark {
      def sources: List[String] = List(
        "P:\\Projects\\Studia\\VI_Semestr\\Sztuczna Inteligencja i inzyneria wiedzy\\Lab\\src\\test\\resources\\test_matrix_16.txt",
        "P:\\Projects\\Studia\\VI_Semestr\\Sztuczna Inteligencja i inzyneria wiedzy\\Lab\\src\\test\\resources\\test_matrix_20.txt")

      def popSizes: List[Int] = List(500)

      def engines: List[GeneticEngine] = testEngines

      def timeParameters: List[Duration] = List(2 seconds)

      def iterationParameters: List[Int] = Nil
    }


    bench.start()
    testEngines.map(_.log).foreach(_ print())
  }

  def printCandidate(candidate: Permutation): Unit = {
    println(s"$candidate = ${candidate.fitnessValue}")
  }

}
