package adj.scala.advance

object ControlBreak {
  def main(args: Array[String]): Unit = {
    /**
      * Scala控制跳出的方法
      * 1.基于boolean类型的控制变量
      * 2.在嵌套函数中使用return
      * 3.使用Breaks的break方法
      */
    // 使用booolean控制变量
    var flag = true
    var res = 0
    for(i <- 0 until 10 if flag) {
      res += i
      if(i == 4) flag = false
    }
    println("condition boolean")
    println("     res=" + res)

    // 在嵌套函数中使用return
    def add_outer = {
      var result = 0

      // 函数如果使用return, return可以有返回值,  但是函数必须指定返回类型
      def add_inner: Unit = {
        for(i <- 0 until 10) {
          if (i == 5) return
          result += i
        }
      }

      add_inner
      println("     res=" + result)
    }
    println("outer func call inner func")
    add_outer

    // 使用Breaks对象的break方法
    // 集合breakable一起使用,break跳出breakable代码块,即跳出breakable的代码块
    println("breakable")
    import scala.util.control.Breaks._
    var bres = 0
    breakable{
      for(i <- 0 until 10) {
        if(i == 5) break
        bres += i
      }
    }
    println("     res=" + bres)
  }
}
