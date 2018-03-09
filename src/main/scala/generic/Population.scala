package generic

import common.Permutation

import scala.util.Random

class Population(permutations: List[Permutation]) {

  val size: Int = permutations.length

  def best: Permutation = permutations.minBy(_.fitnessValue)

  def best(n: Int): List[Permutation] = permutations.sortBy(_.fitnessValue).take(n)

  def selectByTournament(tourSize: Int, p: Double)(sizeProp: Double): Population = {
    def takeCandidate(tour: List[Permutation], shot: Double, chance: Double): Permutation = tour match {
      case h :: t :: rest => if (shot <= chance) h else takeCandidate(t :: rest, shot - chance, chance * (1 - p))
      case h :: _ => h
      case _ => throw new RuntimeException("Tour is empty")
    }

    def selectOne(permutations: Seq[Permutation]): Permutation =
      takeCandidate(Random.shuffle(permutations).take(tourSize).toList, Random.nextDouble(), p)

    def takeOne(permutations: Seq[Permutation]): (Permutation, Seq[Permutation]) = {
      val perm = selectOne(permutations)
      (perm, permutations.filterNot(_ == perm))
    }

    val newPopSize = size * sizeProp

    def takeUntil(permutations: Seq[Permutation], acc: List[Permutation]): List[Permutation] = {
      if (acc.length < newPopSize) {
        val (taken, rest) = takeOne(permutations)
        takeUntil(rest, taken :: acc)
      }
      else acc
    }

    new Population(takeUntil(permutations, Nil))
  }

  def randCrossover(parentChance: Double, childChance: Double, childCount: Int): Population = {
    // new population size = this population size * (parentChance + childChance * childCount)
    def crossWithFirst(perms: List[Permutation]): List[Permutation] = {
      val (dad, moms) = (perms.head, perms.tail.take(childCount))
      moms.filter(_ => Random.nextDouble() < childChance).map(dad.randomCrossOver)
    }

    def createChildren(perms: List[Permutation], acc: List[Permutation]): List[Permutation] =
      if (perms.length > childCount) createChildren(perms.tail, acc ++ crossWithFirst(perms))
      else acc

    val children = createChildren(Random.shuffle(permutations ++ permutations.take(childCount)), Nil)
    val parents = permutations.filter(_ => Random.nextDouble() < parentChance)
    new Population(parents ++ children)
  }
}
