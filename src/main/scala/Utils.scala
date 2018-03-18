import common.Permutation
import scheduling.Schedule

object Utils {
  def main(args: Array[String]): Unit = {
    val schedule = Schedule
    val notes = schedule.start
    notes.foreach(_ print() )
  }

  def printCandidate(candidate: Permutation): Unit = {
    println(s"$candidate = ${candidate.fitnessValue}")
  }

}
