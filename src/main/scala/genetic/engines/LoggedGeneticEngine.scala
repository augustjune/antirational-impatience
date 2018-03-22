package genetic.engines

import genetic.logging.Log
import genetic.operations.Mixing.Mixing
import genetic.operations.Mutation.Mutation
import genetic.operations.Selection.Selection
import genetic.operations.{Population, Same}

import scala.concurrent.duration.Duration

object LoggedGeneticEngine {
  def apply(selection: Selection = Same, mixing: Mixing = Same, mutation: Mutation = Same, log: Log): LoggedGeneticEngine =
    new LoggedGeneticEngine(selection, mixing, mutation, log)
}

class LoggedGeneticEngine(selection: Selection, mixing: Mixing, mutation: Mutation, val log: Log)
  extends GeneticEngine(selection, mixing, mutation) {

  log.addHeader(s"Parameters -  Selection: $selection, Mixing: $mixing, Mutation: $mutation")
  log.addComment(s"Format: PopSize,BestFitnessVal,DuplicatesInPop")

  override def iterate(initial: Population, duration: Duration): Population = {
    log.addComment(s"Starting iterating for $duration with init_pop of ${initial.size} and matrix of ${initial.head.size}")
    super.iterate(initial, duration)
  }

  override def iterate(initial: Population, times: Int): Population = {
    log.addComment(s"Starting iterating for $times times with init_pop of ${initial.size}")
    super.iterate(initial, times)
  }

  override protected def cycle(population: Population): Population = {
    val nextPop = super.cycle(population)
    val nextPopSize = nextPop.length
    log.addValues(nextPopSize, nextPop.min.fitnessValue, nextPopSize - nextPop.distinct.length)
    nextPop
  }
}
