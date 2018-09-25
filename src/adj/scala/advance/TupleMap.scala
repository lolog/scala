package adj.scala.advance

import java.util

object TupleMap {
  def main(args: Array[String]): Unit = {
    /**
      * Tuple拉链操作
      * Tuple拉链操作指的就是zip操作。
      * zip操作,是Array的方法,用于将两个Array,合并为一个Array。
      * 比如Array(v1)和Array(v2),使用zip操作合并之后的格式为Array((v1, v2))。
      * 合并之后的Array元素类型Tuple
      */
    println("Tuple zip")
    val students = Array("Leo", "Jack", "Jen")
    val ages     = Array(18, 19, 19)
    val infos = students.zip(ages)
    // 访问
    println("    [" + infos(0) + ", " + infos(1) + ", " + infos(2) + "]")
    for((student, age) <- infos) print("    (" + student + "," + age + ")")
    // 转换为map
    println("\nTuple to Map")
    println("    " + infos.toMap)

    /**
      * Java Map与Scala Map的隐式转换
      */
    val javaStudents = new util.HashMap[String, Int]()
    javaStudents.put("Alice", 80)
    javaStudents.put("Bob", 90)
    javaStudents.put("Cindy", 8)

    println("java map")
    println("    " + javaStudents)

    // scala map
    val scalaPersons = scala.collection.Map[String, Int] ("Alice"->10, "Jack" -> 20)

    // java map - scala map
    println("java map convert to scala map")
    import scala.collection.JavaConversions.mapAsScalaMap
    val scalaStudents: scala.collection.mutable.Map[String, Int] = javaStudents
    // 访问
    println("    " + scalaStudents)
    println("    Cindy->" + scalaStudents("Cindy"))

    // scala map - java map
    println("scala map convert to java map")
    import scala.collection.JavaConversions.mapAsJavaMap
    val javaPersons: java.util.Map[String, Int] = scalaPersons
    // 访问
    println("    " + javaPersons)
    println("    Jack->" + javaPersons("Jack"))

  }
}
