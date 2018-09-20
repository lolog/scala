package adj.scala.baseGrammar

object BlockStatement {
  def main(args: Array[String]): Unit = {
    // 默认情况下, scala不需要语句终结符, 默认将每一行作为一条语句

    // 一行需要有多条语句
    // 如果一行需要有多条语句的话, 必须使用语句终结符";"
    val condition:Boolean = true
    var flag:String = "Hello World"
    var message = if(condition) {flag += "..."; flag} else "other"
    println("block, multi statement, message= " + message)

    // 块表达式
    // 指{}中的值, 其中可以包含多条语句, 最后一个语句如果不是一个赋值语句, 则这个语句就是块表达式的返回值, 否则无返回值
  }
}
