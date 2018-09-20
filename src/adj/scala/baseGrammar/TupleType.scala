package adj.scala.baseGrammar

object TupleType {
  def main(args: Array[String]): Unit = {
    //======================== map的元素类型-Tuple ===================//
    // 简单的Tuple
    val t = ("leo", 30, 20, "felix")

    // 访问tuple
    println("tuple = " + t._1 + ", " + t._2)

    // zip操作
    val names = Array("leo", "jack", "mike")
    val ages  = Array(10, 20 ,30)
    val nameAges = names.zip(ages) // 类型为Array[(String, Int)]
    println("Tuple.zip  " + nameAges.mkString(" , "))
    for((name, age) <- nameAges) print(name + ":" + age + " ")

    // tup定义
    val tp0:Array[(String, Int)] = Array("felix"->1, "jo" -> 20)
    val tp1:Array[(String, Int)] = Array(("felix", 1), "jo" -> 20)
    val tp2:Array[(String, Int)] = Array(("felix", 1), ("jo", 20))

    println(tp0)
    println(tp0)
    println(tp0)
  }
}
