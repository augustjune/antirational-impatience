import common.Permutations
import common.matrices.MatrixSource
import engines.GeneticEngine
import operations.Population

import scala.concurrent.duration.Duration

trait Benchmark {
  def sources: List[String]

  def popSizes: List[Int]

  def engines: List[GeneticEngine]

  def timeParameters: List[Duration]

  def iterationParameters: List[Int]

  def start(): Unit = {
    for {
      engine <- engines
      initPop <- initialPopulations
      time <- timeParameters
    } yield engine.iterate(initPop, time)
  }

  private val initialPopulations: List[Population] = for {
    source <- sources
    popSize <- popSizes
  } yield permutationsOf(source).candidates.take(popSize).toList

  private def permutationsOf(path: String): Permutations = {
    val source = new MatrixSource(path)
    val (range, flow) = source.toMatrices
    new Permutations(range, flow)
  }

}
