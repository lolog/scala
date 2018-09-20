package adj.scala.baseGrammar

import java.io.File
import  scala.util.control.Breaks._

object ConditionControl {
  def main(args: Array[String]): Unit = {
    // if-else
    ifElse(args)

    // while
    whileCycle

    // do while
    doWhile

    // for
    forCycle
  }

  /**
    * if - else if - else
    */
  def ifElse(args: Array[String]): Unit = {
    //+++++++++++++ if表达式的定义  +++++++++++++//
    var name: String = "scala"
    // scala中, 如果if/else表达式最后一条语句不是一个赋值语句, 那么是有返回值的, 即最后一个语句的值。
    name = if (!args.isEmpty) args(0) else "scala"
    println("name = " + name)

    // 等价于

    // 反之, 将没有值返回
    if (!args.isEmpty) name = args(0)
    println("name = " + name)

    // if - else if - else
    val age = 10
    if (age < 0) {
      println("age < 0")
    } else if (age < 10) {
      println("0 =< age < 10")
    } else {
      println("age >= 10")
    }

    //+++++++++++++ if表达式的类型推断  +++++++++++++//
    // 由于if/else表达式是有值的, 而if/else字句的值类型可能不同, 此时if表达式的值是什么类型呢?
    // 答：scala会自动进行推断的, 取2个类型的公共父类型
    var condition: Boolean = true
    val flag = if (condition) 1 else 0 // flag为Int类型
    val author = if (condition) "author" else 0 // Any是String和Int的公共父类
    val info = if (condition) "infomation" // 如果if后面没有跟else,则默认else的值时Util类型, 用()表示, 类似于java中的void或者null。
    // 因此, info类型为Any
    println("val info = if (condition) \"infomation\", info type = " + typeOf(info))

    //+++++++++++++ 将if语句放在多行中  +++++++++++++//
    // 默认情况下, REPL只能解释if表达式的一条语句, 如果if表达式通常包含多行语句, 可以使用{}的方式。
    // 或者在命令行中,使用:paste和ctrl+D的方式
    var str:Any = if(condition) {
      name = "felix"
      name
    }
    print("if multi item, str = " + str)

    println()
  }

  // 类型判断函数
  def typeOf[T: Manifest](t: T): Manifest[T] = manifest[T]

  def whileCycle() {
    // scala的while循环和java相同
    var flag: Boolean = true
    while (flag) {
      flag = false
      println("while")
    }
    println()
  }

  def doWhile(): Unit = {
    var flag: Boolean = false
    do {
      println("do while")
    } while (flag);
    println()
  }

  def forCycle(): Unit = {
    // scala没有for循环, 只能使用while替代for循环, 或者使用简易版的for语句
    // 简易版的for语句, for (i <- 1 to 10)
    // 或者使用until, 表达不到上限, for(i <- 1 until 10)
    // 也可以对字符串进行遍历, 类似于Java的增强型for循环, for(chr <- "Hello World")

    // 循环跳出语句
    // scala没有提供类似于java的break语句。
    // 但是可以使用boolean类型变量, return或者breaks的break函数来替代使用。
    // import scala.until.control.Breaks._

    println("index:Int <- 1 to 2")
    for (index: Int <- 1 to 2)
      println("    index = " + index)

    // 等价于
    println("index <- 1 to 2")
    for (index <- 1 to 2)
      println("    index = " + index)

    // 等价于
    println("index <- 1.to(2)")
    for (index <- 1.to(2))
      println("    index = " + index)

    // for each
    println("for each")
    var files: Array[File] = new File(".").listFiles();
    for (file: File <- files)
      println("    " + file.getAbsolutePath)

    breakable {
      for (chr <- "Hello World") {
        if (chr == 'W') break;
        print(chr + " ")
      }
    }

    // 多重for循环, 99乘法表
    println()
    for(i <-1 to 9; j <- 1 to 9) {
      if(j == 9) {
        println(i + "*" + j + "=" + (i * j) + "  ")
      }
      else {
        print(i +"*" + j + "=" + (i * j) + "  ")
      }
    }

    // if守卫, 取偶数
    for(i <- 1 to 10 if i % 2 == 0) print(i + " ")

    // for推导式, 构造集合
    println()
    val y = for(i <- 1 to 10) yield i
    println(y)

    println()
  }
}
