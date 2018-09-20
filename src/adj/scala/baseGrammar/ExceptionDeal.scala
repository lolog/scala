package adj.scala.baseGrammar

import java.io.IOException
import java.io.FileNotFoundException
import java.io.FileReader
import java.io.BufferedReader
import com.sun.javafx.webkit.prism.TextUtilities

/**
 * <pre>
 * Scala异常捕捉
 * try {
 * }catch {
 *   case ex: Exception1 => {
 *   }
 *   case ex: Exception2 => {
 *   }
 * }
 * </pre>
 */
object ExceptionDeal {
  def main(args: Array[String]): Unit = {
    try {
      throw new RuntimeException("Exception");
    } catch {
      case _: FileNotFoundException => {
        println("Missing file exception")
      }
      case _: IOException => {
        println("IO Exception")
      }
      case ex: Exception => {
        ex.printStackTrace
      }
    }
    finally {
      println("exception end")
    }
  }
}