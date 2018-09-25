package adj.scala.advance

object SampleClass {
  def main(args: Array[String]): Unit = {
    /**
      * Scala中的样例类
      * 类似于Java中的JavaBean,即包含了一堆field、以及setter和getter方法。
      */
    // Scala样例类自动提供了apply和unapply方法
    case class SAStudent(val name: String, val age: Int)
    val sas = new SAStudent("Leo", 20)

    sas match {
      case SAStudent(name, age) => println("name="+name + ", age=" + age)
    }
  }
}
