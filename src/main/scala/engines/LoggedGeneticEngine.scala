package engines

import logging.Log
import operations.Mixing.Mixing
import operations.Mutation.Mutation
import operations.{Population, Same}
import operations.Selection.Selection

import scala.concurrent.duration.Duration

object LoggedGeneticEngine {
  def apply(selection: Selection = Same, mixing: Mixing = Same, mutation: Mutation = Same, log: Log): LoggedGeneticEngine =
    new LoggedGeneticEngine(selection, mixing, mutation, log)
}

class LoggedGeneticEngine(selection: Selection, mixing: Mixing, mutation: Mutation, val log: Log)
  extends GeneticEngine(selection, mixing, mutation) {

  log.addHeader(s"Starting logging for parameters: $selection, $mixing, $mutation")

  override def iterate(initial: Population, duration: Duration): Population = {
    log.addLine(s"Starting iterating initial population of size ${initial.length} for $duration")
    super.iterate(initial, duration)
  }

  override def iterate(initial: Population, times: Int): Population = {
    log.addLine(s"\nStarting iterating initial population of size ${initial.length} for $times times")
    super.iterate(initial, times)
  }

  override protected def cycle(population: Population): Population = {
    val nextPop = super.cycle(population)
    val best = nextPop.min
    log.addPoint(s"Best permutation of current pop: Q($best) = ${best.fitnessValue} | Current pop size: ${nextPop.length} | Duplicating permutations count ${nextPop.length - nextPop.distinct.length}")
    nextPop
  }
}
