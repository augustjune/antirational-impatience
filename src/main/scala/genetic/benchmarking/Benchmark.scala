package genetic.benchmarking

import genetic.common.Permutations
import genetic.common.matrices.MatrixSource
import genetic.engines.GeneticEngine
import genetic.operations.Population

import scala.concurrent.duration.Duration

trait Benchmark {
  def sources: List[String]

  def popSizes: List[Int]

  def engines: List[GeneticEngine]

  def run(): Duration

  protected def initialPopulations: List[Population] = for {
    source <- sources
    popSize <- popSizes
  } yield permutationsOf(source).candidates.take(popSize).toList

  private def permutationsOf(path: String): Permutations = {
    val source = new MatrixSource(path)
    val (range, flow) = source.toMatrices
    new Permutations(range, flow)
  }
}

trait TimeBenchmark extends Benchmark {
  def timeParameters: List[Duration]

  def run(): Duration = {
    val start = System.nanoTime()
    for {
      engine <- engines
      initPop <- initialPopulations
      time <- timeParameters
    } yield engine.iterate(initPop, time)
    Duration(System.nanoTime() - start, java.util.concurrent.TimeUnit.NANOSECONDS)
  }
}

trait IterativeBenchmark extends Benchmark {

  def iterationParameters: List[Int]

  def run(): Duration = {
    val start = System.nanoTime()
    for {
      engine <- engines
      initPop <- initialPopulations
      iterationNumber <- iterationParameters
    } yield engine.iterate(initPop, iterationNumber)
    Duration(System.nanoTime() - start, java.util.concurrent.TimeUnit.NANOSECONDS)
  }
}
