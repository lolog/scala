package adj.scala.baseGrammar

import scala.math._
/**
 * Scala函数的调用
 */
object FunctionCall {
  def main(args: Array[String]): Unit = {
    // 函数的调用方式
    // 如果调用函数时, 不需要传递参数, 则scala允许调用函数时省略括号
    val res:Double = sqrt(2)
    val lower:String = "HelloWorld".toLowerCase
    
    // apply函数
    // 在scala的object中, 可以声明apply函数。
    // 使用"类名()"的形式, 其实就是"类名.apply()"的一种缩写。
    // 通常使用这种方式来构造类的对象, 而不是使用"new 类名()"的形式
    var str:Char = "Hello World"(6) // 等价于 "Hello World".apply(6), 因为StringOps定义了def apply(n:Int):Char方法
    var arr:Array[Int] = Array(1, 2, 3, 4) // 等价于, Array object的apply()函数来创建Array类的实例, 也就是一个数组
  }
}