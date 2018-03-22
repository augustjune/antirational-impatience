package genetic

import genetic.benchmarking.IterativeBenchmark
import genetic.common.Permutation
import genetic.engines.{GeneticEngine, LoggedGeneticEngine}
import genetic.logging.Notepad
import genetic.operations.Mixing.ClassicCrossover
import genetic.operations.Mutation.{ComplexMutation, SingleChromosomeMutation}
import genetic.operations.Selection.{Roulette, SimpleTournament}

object Utils {
  def main(args: Array[String]): Unit = {

    val testEngines: List[LoggedGeneticEngine] = List(
      LoggedGeneticEngine(Roulette(), ClassicCrossover(0.5), SingleChromosomeMutation(0.7),
        Notepad("C:\\Users\\Jura\\Desktop\\GA logs\\2.1.txt")),
      LoggedGeneticEngine(SimpleTournament(5), ClassicCrossover(0.5), SingleChromosomeMutation(0.7),
        Notepad("C:\\Users\\Jura\\Desktop\\GA logs\\2.2.txt")),
      LoggedGeneticEngine(SimpleTournament(10), ClassicCrossover(0.3), ComplexMutation(0.3, 0.9),
        Notepad("C:\\Users\\Jura\\Desktop\\GA logs\\2.3.txt")))

    val bench = new IterativeBenchmark {
      def sources: List[String] = List(
        "P:\\Projects\\Studia\\VI_Semestr\\Sztuczna Inteligencja i inzyneria wiedzy\\Lab\\src\\test\\resources\\test_matrix_12.txt",
        "P:\\Projects\\Studia\\VI_Semestr\\Sztuczna Inteligencja i inzyneria wiedzy\\Lab\\src\\test\\resources\\test_matrix_14.txt",
        "P:\\Projects\\Studia\\VI_Semestr\\Sztuczna Inteligencja i inzyneria wiedzy\\Lab\\src\\test\\resources\\test_matrix_16.txt",
        "P:\\Projects\\Studia\\VI_Semestr\\Sztuczna Inteligencja i inzyneria wiedzy\\Lab\\src\\test\\resources\\test_matrix_18.txt",
        "P:\\Projects\\Studia\\VI_Semestr\\Sztuczna Inteligencja i inzyneria wiedzy\\Lab\\src\\test\\resources\\test_matrix_20.txt",
      )
     
      def popSizes: List[Int] = List(250, 500, 1000, 2000)

      def engines: List[GeneticEngine] = testEngines

      def iterationParameters: List[Int] = List(100, 150)
    }

    val duration = bench.run()
    if (duration.toSeconds > 200)
      println(s"Time took: ${duration.toMinutes} min")
    else
      println(s"Time took: ${duration.toSeconds} sec")

    testEngines.map(_.log).foreach(_ print())
}

  def printCandidate(candidate: Permutation): Unit = {
    println(s"$candidate = ${candidate.fitnessValue}")
  }

}
