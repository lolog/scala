package adj.scala.advance

object PartialFunc {
  def main(args: Array[String]): Unit = {
    /**
      * 偏函数是一种高级的函数形式
      * 简单来说,偏函数是什么,其实就是没有定义好明确输入的函数,函数体是一连串的case语句。
      *
      * 偏函数是PartialFunction[A,B]类的实例。
      * 这个类有2个方法：
      * 1.apply()方法,直接调用可以通过函数体内的case进行匹配,返回结果。
      * 2.isDefinedAt()方法,可以返回一个输入是否跟任何一个case语句匹配。
      */
    def getStudentGrade: PartialFunction[String, Int] = {
      case "Leo" => 90
      case "Jack" => 85
      case "Marry" => 80
    }
    println("PartialFunction")
    val leo = getStudentGrade("Leo")
    println("Leo => " + leo)

    // getStudentGrade()之前必须检查,否则会因为没有找到,抛出异常
    val tom = getStudentGrade.isDefinedAt("Tom")
    println("tom => " + tom)
  }
}
