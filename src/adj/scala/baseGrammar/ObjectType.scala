package adj.scala.baseGrammar

object ObjectType {
  def main(args: Array[String]): Unit = {
    /**
      * Option[T]
      * Option[T]主要是用来避免NullPointerException异常的(Option本身是一个容器)
      * Option[T]有2个实现
      * 1. Some[T]
      * 3. None
      */
    val myMap: Map[String, String] = Map("key1" -> "value")
    val value1: Option[String] = myMap.get("key1")
    val value2: Option[String] = myMap.get("key2")

    println("Option[T]")
    println("    val=" + value1 + ", isEmpty" + value1.isEmpty) // Some("value1")
    println("    val=" + value2 + ", isEmpty" + value2.isEmpty) // None

    val option1: Option[Int] = Some(123)
    val option2: Option[Int] = None
    val val1 = option1.getOrElse(0) // val1 = 123
    val val2 = option2.getOrElse(0) // val2 = 0

    println("\nOption[T] getOrElse")
    println("    val=" + val1)
    println("    val=" + val2)

    /**
      * Nothing
      * Nothing是所有类型的子类,Nothing没有对象,但是可以用来定义类型。
      */
    // if语句是表达式,有返回值,必然有返回值类型;
    // 如果arg < 0, 抛出异常, 返回值的类型为Nothing, Nothing也是Int的子类,
    // 所以, if表达式的返回类型为Int, get方法的返回值类型也为Int。
    def get(arg: Int):Int = {
      if(arg < 0)
        throw new Exception()
      else
        arg
    }

    /**
      * Any
      * Any是abstract类, 它是Scala类继承结构中最底层的。
      * 所有运行环境中的Scala类都是直接或间接继承自Any这个类, 它就是其它语言（.Net，Java等）中的Object。
      */

    /**
      * Null和Nil
      *
      * Null是所有AnyRef的子类, 在scala的类型系统中, AnyRef是Any的子类, 同时Any子类的还有AnyVal。对应java值类型的所有类型都是AnyVal的子类。
      * 所以Null可以赋值给所有的引用类型(AnyRef), 不能赋值给值类型, 这个java的语义是相同的。null是Null的唯一对象
      *
      * Nil是一个空的List, 定义为List[Nothing], 根据List的定义List[+A], 所有Nil是所有List[T]的子类。
      */
  }
}
