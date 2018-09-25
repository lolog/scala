package adj.scala.advance

import scala.collection.mutable.ArrayBuffer

object ExecShell {
  def main(args: Array[String]): Unit = {
    /**
      * 执行外部命令
      * 比如说,如果说,我们的Scala程序,希望去执行Scala坐在进程之外的,比如说,本地操作系统的一个命令
      * 也许执行的本地操作系统的命令,会启动一个新的进程,也许也不会。
      * 但是,如果想要是现在这样的效果和功能,Scala能不能办到呢？
      *
      * 这个是可以的。
      * Scala实际上是提供了这样的支持,也就是说,咋们的Scala程序,运行在一个独立的进程中,但是可以随心所欲地
      * 执行外部操作系统的其他命令,升值启动其他进程。
      */

    // 方式1
    import scala.collection.JavaConversions.bufferAsJavaList
    val cmd = ArrayBuffer("javac", "java/Convert.java")
    val builder = new ProcessBuilder(cmd)
    val process = builder.start()
    val res = process.waitFor()
    println("res = " + res + "\n")

    // ++++++++++++++ windows +++++++++++++
    import sys.process._
    // 方式2
    Process(s"cmd.exe dir").!!
    // 方式3
    "cmd.exe java java.Convert".!

    // ++++++++++++++ linux +++++++++++++++++
    "ls -l".!
  }
}
