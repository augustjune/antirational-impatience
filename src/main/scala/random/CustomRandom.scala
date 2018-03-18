package random

import scala.collection.generic.CanBuildFrom
import scala.util.Random

object CustomRandom {
  val rand = new Random

  def nextInt: Int = rand.nextInt

  def nextInt(bound: Int): Int = rand.nextInt(bound)

  def nextDouble: Double = rand.nextDouble()

  def shot(chance: Double): Boolean = rand.nextDouble() < chance

  def shot(chances: List[Double]): Int = {
    def iter(chances: List[Double], i: Int, shot: Double): Int = chances match {
      case Nil => throw new RuntimeException
      case h :: t => if (shot <= h) i else iter(t, i + 1, shot - h)
    }
    iter(chances.sorted.reverse, 0, Random.nextDouble())
  }

  def shuffle[T, CC[X] <: TraversableOnce[X]](xs: CC[T])(implicit bf: CanBuildFrom[CC[T], T, CC[T]]): CC[T] =
    rand.shuffle(xs)
}
