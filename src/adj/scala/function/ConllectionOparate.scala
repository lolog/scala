package adj.scala.function

import scala.collection.mutable

/**
  * Scala的集合体系结构
  * List
  * LinkedList
  * Set
  * 集合的函数式编程
  * 函数式编程综合案例,统计多个文本内的单词总数
  */
object ConllectionOparate {
  def main(args: Array[String]): Unit = {
    /**
      * scala中的集合体系主要包括:Iterable、Seq、Set、Map。其中,Iterator是所有集合Trait的根。这个结构与Java的集合体系非常相似。
      *
      * Scala中的集合时分为可变和不可变两类集合的,其中可变集合就是说：集合的元素可以动态修改, 而不可变集合的元素在初始化之后就无法修改了。
      * 分别对应scala.collection.mutable和scala.collection.immutable两个包。
      *
      * Seq下包含了Range、ArrayBuffer、List等子Trait。其中,Range就代表了一个序列,通常可以使用"1 to 10"这种语法产生一个Range。
      * ArrayBuffer就类似于Java这种的ArrayList。
      */

    /**
      * List
      * List代表一个不可变的列表。
      * List的创建, val list = List(1, 2, 3, 4)
      * List有head和tail, head代表List的第一个元素,tail代表第一个元素之后的所有元素, list.head, list.tail。
      * List有特殊的::操作符,可以用于将head和tail合并成一个List, 0::list。
      * 如果一个List只有一个元素,那么他的head就是这个元素,它的tail就是Nil
      */
    // 案例, 用递归函数来给List中每个元素都加上指定前缀, 并打印
    println("List")
    def decorator(list: List[Int], prefix: String): Unit = {
      if(list != Nil) {
        print(prefix + list.head + " ")
        decorator(list.tail, prefix)
      }
    }
    val list = List(1, 2, 3, 4)
    decorator(list, "+")

    // 组装成新的list
    val list_new = 0::list
    println()
    for(ele <- list_new) print(ele + " ")

    /**
      * LinkedList
      * LinkedList代表一个可变的列表,使用elem可以引用其头部,使用next可以引用除头之外的尾部。
      */
    println("\n\nLinkedList")
    val linkedList = scala.collection.mutable.LinkedList(1, 2, 3, 4)
    println(linkedList.elem)
    println(linkedList.next)

    // 案例：使用while循环将列表中的每个元素都乘以2
    var currentList = linkedList
    while(currentList != Nil) {
      currentList.elem *= 2
      currentList = currentList.next
    }
    for(ele <- linkedList) print(ele + " ")

    // 案例：使用while循环将列表中每隔一个元素就乘以2
    println
    val llist = scala.collection.mutable.LinkedList(1, 2, 3, 4, 5, 6, 7, 8, 9)
    var clist = llist

    llist.elem *= 2
    while(clist != Nil && clist.next != Nil) {
      clist = clist.next.next
      clist.elem *= 2
    }
    for(ele <- llist) print(ele + " ")

    /**
      * Set
      * Set代表一个没有重复元素的集合, 而且Set时不保证插入顺序的, 也就是说Set是乱序的。
      */
    val s = new scala.collection.mutable.HashSet[Int]()
    s += 1
    s += (2, 3)
    println("\nSet")
    println(s)
    // LinkedHashSet会用一个链表维护插入顺序
    var lhs = scala.collection.mutable.LinkedHashSet[Int]()
    lhs += 1
    lhs += (2, 3)
    println(lhs)

    // SortedSet会自动根据key来进行排序
    val ss = scala.collection.mutable.SortedSet("orange", "apple", "banana")
    println(ss)

    /**
      * 集合的函数式编程
      * 集合的函数式编程非常重要。
      * 必须完全掌握和理解scala的告诫函数时什么意思,scala的集合类的map、flatMap、reduce、reduceLeft、foreach等这些函数,
      * 就是告诫函数,因为可以接收其他函数作为参数。
      * 高阶函数的使用,也是scala与java最大的一点不同。因为java里面没有函数式编程,也肯定没有高阶函数,也肯定无法接收将函数传入一个方法,
      * 或让方法返回函数。
      */
    // map案例实战:为List中每个元素都添加一个前缀
    List("Leo", "Jen", "Peter", "Jack").map("+" + _).foreach(ele => print(ele + " "))
    // flatMap案例实战:将List中的多行句子拆分成单词
    println
    List("Hello World", "You Me").flatMap(_.split(" ")).foreach(ele => print("+" + ele + " "))
    // foreach案例实战：打印List中的每个单词
    println
    List("I", "have", "a", "beautiful", "house").foreach(ele => print(ele + " "))
    // zip案例实战：对学生姓名和学生成绩进行关联
    println
    List("Leo", "Jen", "Peter", "Jack").zip(List(100, 90, 75, 83))
      .foreach(ele => print(ele._1 + ":" + ele._2 + " "))

    /**
      * 统计文本内单词数量
      *
      */
    println("\n单词统计")
    // 使用scala的io将文本内的数据读取出来
    val line_01 = scala.io.Source.fromFile("text_01.txt").mkString
    val line_02 = scala.io.Source.fromFile("text_02.txt").mkString
    // 使用List的伴生对象,将多个文件的内容创建为一个List
    val lines = List(line_01,line_02)
    // spark沿用了这种风格,即使用链式调用函数式编程,spark吱声的api中提供了map、flatMap、reduce、
    // foreach, 以及更高级的reduceByKey、groupByKey等高阶函数。如果要使用scala进行开发,必须掌握这种复杂的链式调用。
    val count = lines.flatMap(_.split(" ")).map((_, 1)).map(_._2).reduceLeft(_ + _)
    println("word count=" + count)
  }
}
