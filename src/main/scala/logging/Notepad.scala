package logging

import java.io.{File, PrintWriter}

import scala.collection.mutable

object Notepad {
  def apply(file: File): Notepad = new Notepad(file)
  def apply(path: String): Notepad = new Notepad(new File(path))
}

class Notepad(file: File) extends Log {
  private final val POINT_COUNTER_START = 1
  private var lines: mutable.MutableList[String] = mutable.MutableList.empty
  private var pointCounter = POINT_COUNTER_START

  private def erasePointCounter(): Unit = pointCounter = POINT_COUNTER_START

  def addHeader(line: String): Unit = {
    lines += header(line)
    erasePointCounter()
  }

  protected def header(line: String): String = s"\t\t*** | $line | ***".toUpperCase

  def addLine(line: String): Unit = {
    lines += line
    erasePointCounter()
  }

  def addPoint(line: String): Unit = lines += point(line)

  protected def point(line: String): String = {
    val s = s"$pointCounter. $line"
    pointCounter = pointCounter + 1
    s
  }

  def print(): Unit = {
    val writer = new PrintWriter(file)
    lines.foreach(writer.println)
    writer.close()
  }
}
