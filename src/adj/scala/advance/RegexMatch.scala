package adj.scala.advance

object RegexMatch {
  def main(args: Array[String]): Unit = {
    /**
      * 正则表达式
      *
      * 什么是正则表达式？
      * 一种语法：用一个表达式,来匹配一系列的字符串。
      */
    // 定义一个正则表达式,使用String类的r方法
    // 此时,返回的类型是scala.util.matching.Regex
    val p1 = "[a-zA-Z]".r

    // 获取一个字符串中,匹配正则表达式的部分,使用findAllIn
    print("findAllIn\n    ")
    for(str <- p1.findAllIn("Hello World")) print(str)

    // 同理,使用findFirstIn,可以获取第一个匹配正则表达式的部分
    println("\n\nfindFirstIn")
    val ps1 = p1.findFirstIn("Hello World")
    println("    res = " + ps1)

    // 使用replaceAllIn,可以将匹配正则的部分,替换掉
    println("\nreplaceAllIn")
    val ps2 = p1.replaceAllIn("Hello World!!!", "y")
    println("    res = " + ps2)

    // 使用
    println("\nreplaceFirstIn")
    val ps3 = p1.replaceFirstIn("Hello Word", "K")
    println("    res = " + ps3)
  }
}
