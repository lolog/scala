package adj.scala.advance

import java.io._

import scala.io.Source

/**
  * 文件操作
  */
object FileOparate {
  def main(args: Array[String]): Unit = {
    /**
      * 遍历文件中的每一行
      *
      * 必须导入scala.io.Source类: import scala.io.Source
      */
    // 方法1：使用Source.getLines返回迭代器
    var source = Source.fromFile("text_01.txt", "UTF-8")
    val fIter = source.getLines
    println("Source.getLines - Iterator ")
    for(line <- fIter) println("    " + line)
    source.close // 关闭IO流

    // 说明一点：一个BufferedSource对象的getLines方法,只能调用一次,一次调用完之后,
    // 就已经把文件里的内容读取完了。如果反复调用Source.getLines,是获取不到内容的,
    // 必须重新创建一个BufferedSource对象。

    // 方法2：将Source.getLines返回的迭代器,转换成数组
    source = Source.fromFile("text_01.txt", "UTF-8")
    val lines = source.getLines.toArray
    println("\nSource.getLines - Iterator to Array")
    println("    line count = " + lines.size)
    source.close

    // 方法3：调用Source.mkString,返回文本中所有的内容
    source = Source.fromFile("text_01.txt", "UTF-8")
    println("\nSource.mkString")
    println(source.mkString)
    source.close

    // 遍历一个文件中的每一个字符
    source = Source.fromFile("text_01.txt", "UTF-8")
    println("\ntraverse file charactor")
    for(c <- source) print(c)
    source.close

    // 从URL以及字符串读取字符
    val html = Source.fromURL("http://www.baidu.com", "UTF-8")
    html.close
    val str = Source.fromString("Hello World")
    str.close

    // 递归遍历子目录
    println("\n\ntraverse directory")
    def subdirs(dir: File): Iterator[File] = {
      val childDirs = dir.listFiles().filter(_.isDirectory)
      childDirs.toIterator ++ childDirs.toIterator.flatMap(subdirs _)
    }
    val iter = subdirs(new File("src/"))
    for(d <- iter) println("    " + d)

    /**
      * 结合Java IO流,读取任意文件
      * 这里说明一点,大家千万不要认为,Spark就是会Scala就可以了;也千万不要认为,Scala就是
      * 跟Java一点关系都没有,升值与完全可以替代Java。上述说法,都是很荒谬的,都是门外汉才会这么认为的。
      *
      * 如果你真的深入Spark的源代码,真的对Scala掌握得很深入,你就会知道这一点。
      * Spark的源码实际上是由Scala和Java共同编写而成的,Java的多线程
      * Scala本身编程语言的功能就不是很强大和完善,比如说：Scala甚至不能很方便地写文件,必须依赖于Java的IO流才可以。
      *
      * 所以说,Scala其实主要就是针对某些特定领域的一些复杂系统,比较实用的一种编程语言而已。
      * 完全无法替代Java的,Scala和Java是相辅相成的。
      *
      * 可以这么跟大家说
      * Scala还有一种作用,可以用Scala编写Spark作业,
      * 但是问题是：为什么我们要用Java开发hive udf、mapreduce、hbase client、zookeeper client,
      * 用Java开发Storm的作业。然后,作为一个大数据攻城狮,偏偏用Spark的时候,一定要用Scala开发呢？
      * 用Spark开发作业,用Java,个人认为,是最合适的、最通用的、最可移植的、最方便维护的。
      *
      * Scala
      * 1.有些公司的技术leader,要求用scala开发spark作业,个人持反对观念。
      * 2.最重要的一点,深入掌握Scala所有的初中高级语法,才能透彻和深入理解与阅读Spark源码。
      * 3.也有很少公司,可能只会用scala开发复杂的大型分布式系统。
      */
    // 案例：结合Java IO流,实现文件拷贝
    val fis = new FileInputStream(new File("text_01.txt"))
    val fos = new FileOutputStream(new File("out/text_01.copy.txt"))
    val buf = new Array[Byte](1024)
    fis.read(buf)
    fos.write(buf, 0, 1024)
    fis.close
    fos.close

    // 结合Java IO流,写文件
    val pw = new PrintWriter("out/out.txt")
    pw.println("Hello World")
    pw.close

    /**
      * 序列化与反序列化
      */
    // 如果要序列化,那么必须让类有一个@SerialVersion,定义一个版本号,
    // 要让类继承Serializable Trait
    @SerialVersionUID(50L) class UPerson(val name: String) extends Serializable
    val leo = new UPerson("Leo")
    println("\nSerializable")
    val oos = new ObjectOutputStream(new FileOutputStream("out/serializable.txt"))
    oos.writeObject(leo)
    oos.close

    val ois = new ObjectInputStream(new FileInputStream("out/serializable.txt"))
    val rLeo = ois.readObject().asInstanceOf[UPerson]
    println("    name = " + rLeo.name)
    ois.close
  }
}
