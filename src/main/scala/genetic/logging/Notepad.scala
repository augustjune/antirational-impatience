package genetic.logging

import java.io.{File, PrintWriter}

import scala.collection.mutable

object Notepad {
  def apply(file: File): Notepad = new Notepad(file)
  def apply(path: String): Notepad = new Notepad(new File(path))
}

class Notepad(file: File) extends Log {
  private var lines: mutable.MutableList[String] = mutable.MutableList.empty

  def addHeader(line: String): Unit = lines += formatHeader(line)

  def addValues(values: Any*): Unit = lines += (values mkString ",")

  def addComment(line: String): Unit = lines += formatComment(line)

  protected def formatHeader(line: String): String = s"\n\t\t*** | $line | ***".toUpperCase

  protected def formatComment(line: String): String = "//" + line

  def print(): Unit = {
    val writer = new PrintWriter(file)
    lines.foreach(writer.println)
    writer.close()
  }
}
