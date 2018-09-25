package adj.scala.baseGrammar

object SpecialOparate {
  def main(args: Array[String]): Unit = {
    // Scala中的=>符号可以看做是创建函数实例的语法
    // A => T / A,B => T 表示一个函数的输入类型为A或者A、B, 返回值为T
    // val f: Int => String = myInt => "The value of myInt is: " + myInt.toString()
    val f: Int => String = myInt => "The value of myInt is: " + myInt.toString()
    println("=>")
    println("    res=" + f(12))
  }
}
