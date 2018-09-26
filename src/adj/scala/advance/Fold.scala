package adj.scala.advance

object Fold {
  def main(args: Array[String]): Unit = {
    // reduce
    val list = Array(1, 2, 3, 4)
    val reduceLeft = list.reduceLeft(_ + _)
    val reduceRight = list.reduceRight(_ + _)
    println("reduce")
    println("    reduceLeft=" + reduceLeft)
    println("    reduceRight=" + reduceRight)

    // fold操作
    val foldLeft = list.foldLeft(10)(_ + _)
    val foldRight = list.foldRight(10)(_ + _)
    println("\nfold")
    println("    foldLeft=" + foldLeft)
    println("    foldRight=" + foldRight)
  }
}
