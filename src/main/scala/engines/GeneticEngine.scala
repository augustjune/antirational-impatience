package engines

import operations.Mixing._
import operations.Mutation._
import operations.{Population, Same}
import operations.Selection._

import scala.concurrent.duration.Duration

object GeneticEngine {
  def apply(selection: Selection = Same, mixing: Mixing = Same, mutation: Mutation = Same): GeneticEngine =
    new GeneticEngine(selection, mixing, mutation)
}

class GeneticEngine(selection: Selection, mixing: Mixing, mutation: Mutation) {

  def iterate(initial: Population, times: Int): Population = {
    def iter(n: Int, population: Population): Population =
      if (n > times) population
      else iter(n + 1, cycle(population))

    iter(0, initial)
  }

  def iterate(initial: Population, duration: Duration): Population = {
    val end = System.nanoTime() + duration.toNanos

    def iter(population: Population): Population =
      if (System.nanoTime() > end) population
      else iter(cycle(population))

    iter(initial)
  }

  protected def cycle(population: Population): Population = mutation(mixing(selection(population)))
}
