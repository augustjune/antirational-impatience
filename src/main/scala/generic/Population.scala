package generic

import common.Permutation
import random.CustomRandom

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
      takeCandidate(CustomRandom.shuffle(permutations).take(tourSize).toList, CustomRandom.nextDouble, p)

    def takeOne(permutations: Seq[Permutation]): (Permutation, Seq[Permutation]) = {
      val perm = selectOne(permutations)
      (perm, permutations.filterNot(_ == perm))
    }

    val newPopSize = size * sizeProp

    def takeUntil(permutations: Seq[Permutation], acc: List[Permutation]): List[Permutation] =
      if (acc.length < newPopSize) {
        val (taken, rest) = takeOne(permutations)
        takeUntil(rest, taken :: acc)
      }
      else acc

    new Population(takeUntil(permutations, Nil))
  }

  def randCrossover(parentChance: Double, times: Double): Population = {
    val childCount = (times - parentChance).toInt
    val extraChildChance = (times - parentChance) - childCount
    def crossWithFirst(perms: List[Permutation]): List[Permutation] = {
      val (dad, moms) = (perms.head, perms.tail.take(childCount + (if(CustomRandom.shot(extraChildChance)) 1 else 0)))
      moms.map(dad.randomCrossOver)
    }

    def createChildren(perms: List[Permutation], acc: List[Permutation]): List[Permutation] =
      if (perms.length > childCount) createChildren(perms.tail, acc ++ crossWithFirst(perms))
      else acc

    val children = createChildren(CustomRandom.shuffle(permutations ++ permutations.take(childCount + 1)), Nil)
    val parents = permutations.filter(_ => CustomRandom.shot(parentChance))

    new Population(parents ++ children)
  }
}
