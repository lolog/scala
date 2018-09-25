package adj.scala.advance

import scala.collection.mutable

object Extractor {
  def main(args: Array[String]): Unit = {
    /**
      * 提取器
      *
      * apply方法
      * 伴生类和伴生对象,伴生对象里面,可以定义一个apply方法,直接调用类(参数),就相当于早调用apply方法,然后创建伴生类对象返回。
      *
      * unapply方法
      * 和apply相反
      *
      * 提取器就是一个包含了unapply方法的对象,跟apply方法正好相反
      * apply方法,是接收一堆参数,然后构造出一个对象
      * unapply方法,是接收一个字符串,然后解析出对象的属性
      */
    class EXPerson (val name: String, val age: Int)
    object EXPerson {
      def unapply(arg: EXPerson): Option[(String, Int)] = {
        if(arg == null) null
        else Some[(String, Int)](arg.name -> arg.age)
      }
      def unapply(str: String): Option[(String, Int)] = {
        val words: Array[String] = str.split(" ")
        Some[(String, Int)](words(0) -> words(1).toInt)
      }
    }
    println("unapply")
    val exp = new EXPerson("Leo", 18)
    val EXPerson(name, age) = exp
    println("    name=" + name + ", age=" + age)

    val EXPerson(n, a) = "Leo 20"
    println("    name=" + n + ", age=" + a)
  }
}
