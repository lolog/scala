package adj.scala.baseGrammar

// Array
// List、Set、Map、Option、元组
// Iterator
object ArrayType {
  def main(args: Array[String]): Unit = {
    //============= Array =================//
    // scala中, rray和Java类似,由于scala与java都是运行在jvm中,双方可以互相调用,
    // 因此scala数组的地产实际上是java数组。

    // 数组初始化后, 长度就固定下来了, 而且元素全部根据其类型初始化, 如Int默认值为0
    val a = new Array[Int](10)

    // 也可以直接使用Array()创建数组, 元素类型自动推断
    val b = Array(1, 2, 3, 4, 5) // 类型为Int
    var c = Array("felix", 1) // 类型为Any

    // 访问
    println("c(0) = " + c(0))

    //============= ArrayBuffer =================//
    // scala中, 如果需要类似于Java中的ArrayList这种长度可变的集合类。
    // 如果不想每次都使用全限定名, 则可以预先导入ArrayBuffer类
    import scala.collection.mutable.ArrayBuffer

    // 使用ArrayBuffer()方式可以创建一个空的ArrayBuffer
    var d = ArrayBuffer[Int]()

    // 使用+=操作符,可以添加一个元素,或者多个元素
    // spark源码大量使用了这种语法
    d += 1
    d += (2, 3, 4)

    // 使用++=操作符,可以添加其他集合中的所有元素
    d ++= Array(5, 6, 7, 8)

    // 使用trimEnd()函数,可以从尾部截断指定个数的元素
    d.trimEnd(5)
    println("d = " + d)

    // 插入元素
    d.insert(1, 10, 11, 12, 13) // index = 1, 插入多个元素
    // 移除元素
    d.remove(1, 2) // index = 1, 2

    // Array和ArrayBuffer可以互相进行转换
    val e = d.toArray[Int]
    val f = e.toBuffer[Int]

    println("f = " + f)

    //==================== Array和ArrayBuffer的遍历 ========================//
    // 使用for和until遍历Array和ArrayBuffer
    for (i <- 0 until d.length)
      print(d(i) + " ")

    println()
    // 从尾部遍历Array和ArrayBuffer
    for (i <- (0 until d.length).reverse)
      print(d(i) + " ")

    println()
    // 使用"增强for循环"遍历Array和ArrayBuffer
    for (i <- d) {
      print(i + " ")
    }

    // ===================== 其他属性 ========================//
    val arr = Array(1, 2, 5, 4, 3)
    // 求和
    val sum  = arr.sum[Int]

    // 求数组的最大值
    val max = arr.max[Int]

    // 对数组进行排序
    scala.util.Sorting.quickSort(arr)
    println()
    print("quickSort(arr) = ")
    for (i <- arr)
      print(i + " ")

    // 获取数组中所有元素内容
    println()
    println("arr.mkString = " + arr.mkString)
    println("arr.mkString(\",\") = " + arr.mkString(","))
    println("arr.mkString(\"<\", \",\", \">\") = " + arr.mkString("<" , ",", ">"))

    // toString
    println("arr.toString = " + arr.toString)


    //============== Array操作与Array转换 =================//
    // 使用yield和函数式变成转换数组

    // 对Array进行转换, 获取的还是Array
    val a00 = Array(1, 2, 3, 4, 5)
    val a01 = for(ele <- a00) yield ele * ele
    println("for(ele <- a00) yield ele * ele, = " + a01.mkString(","))

    // 对ArrayBuffer进行转换, 获取的还是ArrayBuffer
    val b00 = ArrayBuffer[Int]()
    b00 += (1, 2, 3, 4, 5)
    val b01 = for(ele <- b00) yield ele * ele
    println("for(ele <- b00) yield ele * ele, = " + b01.mkString(","))

    //给定if守卫, 仅转换需要的元素
    val a02 = for(ele <- a00 if ele % 2 == 0) yield ele * ele
    println("for(ele <- a00 if ele % 2 == 0) yield ele * ele, = " + a02.mkString(","))

    // 使用函数式变成转换数组(通常使用第一种方式)
    val a03 = a00.filter(_ % 2 == 0).map(2 * _) // _代表a00数组的每一个元素
    val a04 = a00.filter{_ % 2 == 0} map {2 * _}
    println("a00.filter(_ % 2 == 0).map(2 * _) = " + a03.mkString(","))
    println("a00.filter{_ % 2 == 0} map {2 * _} = " + a04.mkString(","))
  }
}
