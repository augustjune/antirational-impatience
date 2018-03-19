package genetic.common.matrices

import scala.io.Source.{fromFile, fromURL}

class MatrixSource(source: String) {
  private val LINES_SEPARATION_COUNT = 1
  private val VALUES_SEPARATOR = " "

  def toMatrices: (FlowMatrix, RangeMatrix) = {
    val lines = getLines.toVector
    val matrixSize = lines.head.trim.toInt
    val flowMatrixBoundaries = flowMatrixLines(matrixSize)
    val rangeMatrixBoundaries = rangeMatrixLines(matrixSize)

    (toMatrix(lines.slice(flowMatrixBoundaries._1, flowMatrixBoundaries._2)),
      toMatrix(lines.slice(rangeMatrixBoundaries._1, rangeMatrixBoundaries._2)))
  }

  private def getLines: Iterator[String] =
    if (source.startsWith("http"))
      fromURL(source).getLines
    else fromFile(source).getLines


  private def toMatrix(lines: Vector[String]): SquareMatrix[Int] =
    new SquareMatrix[Int](lines.map(_.trim.split(VALUES_SEPARATOR).filter(_ != "").map(_.toInt).toVector))

  private def flowMatrixLines(matrixSize: Int): (Int, Int) =
    matrixLines(1 + LINES_SEPARATION_COUNT, matrixSize)

  private def rangeMatrixLines(matrixSize: Int): (Int, Int) =
    matrixLines(1 + matrixSize + 2 * LINES_SEPARATION_COUNT, matrixSize)

  private def matrixLines(start: Int, matrixSize: Int): (Int, Int) = (start, start + matrixSize)
}
