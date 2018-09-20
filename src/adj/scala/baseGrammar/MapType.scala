package adj.scala.baseGrammar

import scala.collection.mutable

// Map类型
object MapType {
  def main(args: Array[String]): Unit = {
    //============== 创建Map ======================//

    // 创建一个不可变的Map
    // 类型为: scala.collection.imutable.Map
    val infos  = Map("Leo"->21, "Jen"->22, "Jack"->23)
    // 不能执行修改操作：infos("Leo") =  20
    println("imutable.Map " + infos)

    // 创建一个可变的Map
    val peoples = scala.collection.mutable.Map("Leo" -> 21, "Jen"->22, "Jack"->23)
    peoples("Leo") = 20
    peoples("Lucy") = 21
    println("mutable.Map "+ peoples)

    // 使用另一种方式创建Map
    val managers = Map(("Leo", 21), ("Jen", 22), ("Jack", 23))
    println("Map((key,value)) = " + managers)

    // 创建一个空的HashMap
    val hm = new mutable.HashMap[String, Int]
    // 或者: new mutable.HashMap[String, Int]()

    //================== 访问Map的元素 ========================//
    val admins  = scala.collection.mutable.Map("Leo"->21, "Jen"->22, "Jack"->23)
    // 获取指定的key对于的value, 如果key不存在, 会报错
    val age = admins("Jack")

    // 使用contains函数检查key是否存在
    val keyIsExisted = admins.contains("Leo")

    // getOrElse函数
    val default = admins.getOrElse("name", null);
    println("getOrElse :: " + default)

    // getOrElseUpdate
    val other = admins.getOrElseUpdate("Join", 20);
    println("getOrElseUpdate :: Join = " + other +"; map = " + admins)

    //================== 修改Map的元素 ========================//
    // 更新Map元素
    admins("Join") = 30

    // 添加多个元素
    admins += ("Mike" -> 20, "Tom" -> 10)

    // 移除元素
    admins -= "Mike"

    // 不可变map元素操作
    val notChangeMap = Map("Mike" -> 20, "Tom" -> 10)
    // 不可变map和新的map相加之后, 赋值给新变量
    val resMap = notChangeMap + ("Jack" -> 10)
    // 移除不可变map的元素, 并且赋值给新变量
    val resmap_r = resMap - "Jack"
    println("resmap_r = " + resmap_r)

    //================== 遍历Map的元素 ========================//
    println("遍历map键值对")
    for((name, age) <- notChangeMap)
      print(name + "=" + age + " ")

    // 遍历map的key
    println("\n遍历map的键")
    for(key <- notChangeMap.keys) {
      print(key + " ")
    }

    println("\n遍历map的值")
    for(value <- notChangeMap.values) {
      print(value + " ")
    }

    // 通过yield翻转key/value, 生成新的map
    val hashmap = for((key, value) <- notChangeMap) yield (value, key)
    println("\n通过yield翻转key/value, 生成新的map")
    println(hashmap)

    //================== SortedMap和LinkedHashMap ========================//
    // SortedMap可以自动对Map的key进行排序
    var sort_map = scala.collection.immutable.SortedMap("felix" -> 20, "alice" -> 21)
    println("SortedMap =" + sort_map)

    // LinkedHashMap可以记住插入的entry顺序
    var linkedMap = new scala.collection.mutable.LinkedHashMap[String, Int]
    linkedMap("felix") = 20
    linkedMap("alice") = 21
    linkedMap("jango") = 22
    println("LinkedHashMap = " + linkedMap)
  }
}
