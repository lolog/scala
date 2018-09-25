package adj.scala.advance

import scala.collection.mutable.ArrayBuffer

object ArrayImmplicit {
  def main(args: Array[String]): Unit = {
    /**
      * 多维数组、Java数组与Scala数组的隐式转换
      */
    // 多维数组
    val mut2Arr = Array.ofDim[Int](2, 2)          // 二维数组
    val mut3Arr = Array.ofDim[Int](2, 2, 2)  // 三维数组
    mut2Arr(0)(0) = 1
    mut3Arr(0)(0)(0) = 1

    // 构造不规则数组
    val mutArr1 = new Array[Array[Int]](3)
    mutArr1(0) = new Array[Int](1)
    mutArr1(1) = new Array[Int](2)
    mutArr1(2) = new Array[Int](3)
    mutArr1(0)(0) = 1

    /**
      * Java数组与Scala数组的隐式转换
      * Scala中,直接调用Java的Api, 比如调用一个Java类的方法,势必可能传入Java类型的List;
      * Scala中构造出来的List,其实是很ArrayBuffer
      */
    import scala.collection.JavaConversions.bufferAsJavaList // 隐式转换,将scala的buffer转换为java的List
    import scala.collection.mutable.ArrayBuffer

    val command = ArrayBuffer("javac", "java/Convert.java")
    val processBuilder = new ProcessBuilder(command)
    val process = processBuilder.start()
    val res = process.waitFor()
    println("scala array - java array")
    println("    res="+res)

    import scala.collection.mutable.Buffer
    import scala.collection.JavaConversions.asScalaBuffer // 隐式将java的List转换我Scala的Buffer
    val cmd: Buffer[String] = processBuilder.command()
    println("java list - scala buffer")
    println("    " + cmd)
  }
}