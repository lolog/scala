package adj.scala.baseGrammar

object RangeType {
  def main(args: Array[String]): Unit = {
    // scala中的Range可以看成是List的特例。
    // Range包含的元素类型是Int

    //======= Range的创建 =========//
    // 方式1
    val r00 = Range(1, 10, 1)   // r0 = [1, 10), step=1
    println(r00)
    // 等价于
    val r01 = 1 until 10 by 1
    val r02 = 1.until(10, 1)
    val r03 = 1.until(10) by 1

    // 方式2
    val r10 = 1 to 10 by 1   // r1 = [1, 10], step=1
    println(r10)
    // 等价于
    val r11 = 1.to(10, 1)
    val r12 = 1.to(10) by 1
  }
}
