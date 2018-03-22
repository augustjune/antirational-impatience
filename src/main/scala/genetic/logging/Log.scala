package genetic.logging

trait Log {
  def addHeader(line: String)
  def addComment(line: String)
  def addValues(values: Any*)

  def print()
}
