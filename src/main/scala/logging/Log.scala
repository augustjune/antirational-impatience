package logging

trait Log {
  def addLine(line: String)
  def addPoint(line: String)
  def addHeader(line: String)

  def print()
}
