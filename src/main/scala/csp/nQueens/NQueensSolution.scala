package csp.nQueens

object NQueensSolution {
  def placeQueens(queens: Int): List[Int] = {
    val rowIndexes = 0 until queens

    val domains = rowIndexes.map(rowIndexes.toSet).toVector


    Nil
  }

  def backTrack(queens: Int): Solution[Int] = {
    def initialDomain = this.initialDomain(queens)

    val domains: Array[Domain[Int]] = (0 until queens).map(_ => initialDomain).toArray
    var solution: Solution[Int] = Nil

    var row = 0
    while (row < queens) {
      domains(row) = domains(row).filter(value => isCompatible(value :: solution))

      if (domains(row).isEmpty) {
        solution = solution.tail
        domains(row) = initialDomain
        row = row - 1
        domains(row) = domains(row).tail
      } else {
        solution = domains(row).head :: solution
        row = row + 1
      }
    }

    solution
  }

  def forwardCheck(queens: Int): List[Int] = {
    def initialDomain = this.initialDomain(queens)

    val domains: Array[Stream[Int]] = (0 until queens).map(_ => initialDomain).toArray
    var solution: Solution[Int] = Nil

    var row = 0
    while (row < queens) {
      if (domains(row).isEmpty) {

      } else {
        solution = domains(row).head :: solution
        row = row + 1
        domains(row) = domains(row).filterNot(x => isCompatible(x :: solution))
      }
    }

    Nil
  }

  def initialDomain(queens: Int): Stream[Int] = (0 until queens).toStream

  def isCompatible(queenIndexes: List[Int]): Boolean = queenIndexes match {
    case Nil => true
    case h :: t =>
      t.zipWithIndex.forall(t => h != t._1 && math.abs(h - t._1) - 1 != t._2) &&
        isCompatible(t)
  }

}
