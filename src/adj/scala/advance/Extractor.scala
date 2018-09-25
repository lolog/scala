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
        Option[(String, Int)](words(0) -> words(1).toInt)
      }
    }
    println("unapply")
    val exp = new EXPerson("Leo", 18)
    val EXPerson(name, age) = exp
    println("    name=" + name + ", age=" + age)

    val EXPerson(n, a) = "Leo 20"
    println("    name=" + n + ", age=" + a)

    /**
      * 只有一个参数的提取器
      * 现在我们来想一下,如果你的类只有一个字段,字符串里面只有一个字段,解析出来的一个字段,是没有办法
      * 放在Tuple中的,因为Scala中的Tuple规定了,必须要2个以及2个以上的值。
      * 那么,在提取器unapply方法中,只能将一个字段值封装在Some对象中返回。
      */
    class EIPerson(val name: String)
    object EIPerson {
      def unapply(str: String): Option[String] = Some(str)
    }
    val EIPerson(ename) = "Leo"
    println("\nOnly one parameter unapply, name=" + ename)
  }
}
