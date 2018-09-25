package adj.scala.advance

object CollectionOparate {
  def main(args: Array[String]): Unit = {
    /**
      * 集合元素的操作
      * col :+ ele                将元素添加到集合尾部             Seq
      * ele +: col                将元素添加到集合头部             Seq
      * col + ele                 在集合尾部添加元素               Set、Map
      * col + (ele1,ele2)         将其他集合添加到集合的尾部        Set、Map
      * col - ele                 将元素从集合找那个删除            Set、Map、ArrayBuffer
      * col - (ele1,ele2)         将子集合从集合中删除              Set、Map、ArrayBuffer
      * col ++ col2               将其他集合添加到集合尾部          Iterator
      * col2 ++: col              将其他集合添加到集合头部          Iterator
      * ele :: list               将元素添加到list的头部           List
      * list2 ::: list            将其他list添加到list的头部       List
      * lis1 :: list2             将其他list添加到list尾部         List
      * set1 | set2               取2个set并集                    Set
      * set1 & set2               取2个set交集                    Set
      * set1 &~ set2              取2个set的diff                  Set
      * col += ele                给集合添加一个元素                可变集合
      * col += (ele1,ele2)        给集合添加一个集合                可变集合
      * col ++ col2               给集合添加一个集合                可变集合
      * col -= ele                从集合中删除一个元素              可变集合
      * col -= (ele1,ele2)        从集合中删除一个集合              可变集合
      * col -= col2               从集合中删除一个子集合            可变集合
      * ele +=: col               向集合头部添加一个元素            ArrayBuffer
      * col2 ++=: col             此案够集合头部添加一个几个         ArrayBuffer
      */

    /**
      * 集合常用的方法
      * head、last、tail
      * length、isEmpty
      * sum、max、min
      * count、exists、filter、filterNot
      * taskWhile、dropWhile
      * take、drop、splitAt
      * taskRight、dropRight
      * sclie
      * contains、startsWith、endsWith
      * indexOf
      * intersect、diff
      */

    // map操作:一对一映射
    val scoreMap = Map[String, Int]("Leo" -> 90, "Jack" -> 80)
    val names = List("Leo", "Jack")
    val scores: List[Int] = names.map(scoreMap(_))

    // flatMap：一对多映射
    val scoreHMap = Map[String, List[Int]]("Leo" -> List(80, 90, 100), "Jack" -> List(70, 60, 50))
    val hscores: List[List[Int]] = names.map(scoreHMap(_))
    val hascores: List[Int] = names.flatMap(scoreHMap(_))

    // collect操作,结合偏函数使用
    val v: IndexedSeq[Int] = "abc".collect {
      case 'a' => 1
      case 'b' => 2
      case 'c' => 3
    }

    // foreach遍历
    names.foreach(println _)
  }
}
