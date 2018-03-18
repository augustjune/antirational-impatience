package scheduling

import common.Permutations
import common.matrices.MatrixSource
import engines.LoggedGeneticEngine
import logging.{Log, Notepad}
import operations.Mixing.ClassicCrossover
import operations.Mutation.{ComplexMutation, SingleChromosomeMutation}
import operations.Population
import operations.Selection.{Roulette, SimpleTournament}

import scala.concurrent.duration._

object Schedule {
  val sources = List("P:\\Projects\\Studia\\VI_Semestr\\Sztuczna Inteligencja i inzyneria wiedzy\\Lab\\src\\test\\resources\\test_matrix_16.txt",
    "P:\\Projects\\Studia\\VI_Semestr\\Sztuczna Inteligencja i inzyneria wiedzy\\Lab\\src\\test\\resources\\test_matrix_20.txt")
  val popSizes = List(500) //, 1000, 2500)

  val initialPopulations: List[Population] = for {
    source <- sources
    popSize <- popSizes
  } yield initRandom(source).candidates.take(popSize).toList

  val engines: List[LoggedGeneticEngine] = List(
    LoggedGeneticEngine(Roulette(), ClassicCrossover(0.5), SingleChromosomeMutation(0.7), Notepad("C:\\Users\\Jura\\Desktop\\GA logs\\1.txt")),
    LoggedGeneticEngine(SimpleTournament(5), ClassicCrossover(0.5), SingleChromosomeMutation(0.7), Notepad("C:\\Users\\Jura\\Desktop\\GA logs\\2.txt")),
    LoggedGeneticEngine(SimpleTournament(10), ClassicCrossover(0.3), ComplexMutation(0.3, 0.9), Notepad("C:\\Users\\Jura\\Desktop\\GA logs\\3.txt"))
  )

  val timeParameters: List[Duration] = List(2 seconds)//, 3 seconds, 5 seconds)
  val iterationParameters: List[Int] = List(50, 100)

  def start: List[Log] = {
    for {
      engine <- engines
      initPop <- initialPopulations
      time <- timeParameters
    } yield {
      engine.iterate(initPop, time); engine.log
    }
  }

  def initRandom(path: String): Permutations = {
    val source = new MatrixSource(path)
    val (range, flow) = source.toMatrices
    new Permutations(range, flow)
  }

}
