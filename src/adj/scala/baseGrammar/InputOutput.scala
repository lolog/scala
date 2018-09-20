package adj.scala.baseGrammar

import scala.io._

object InputOutput {
  def main(args: Array[String]): Unit = {
    // scala从命令行读取一行
    val line:String = StdIn.readLine

    // scala输出值到控制台
    print(line)    // 没有换行符
    println()      // 单独输出一个换行符
    println(line)  // 有换行符
    printf("%s\n", line) // 指定格式输出
  }
}