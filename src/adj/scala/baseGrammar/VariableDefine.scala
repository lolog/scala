package adj.scala.baseGrammar

import scala.io.Source.fromFile

// Unit	    表示无值,和其他语言中void等同。用作不返回任何结果的方法的结果类型。Unit只有一个实例值,写成()。
// Null	    null或空引用
// Nothing	Nothing类型在Scala的类层级的最低端;它是任何其他类型的子类型。
// Any	    Any是所有其他类的超类
// AnyRef  	AnyRef类是Scala里所有引用类(reference class)的基类
object VariableDefine {
  def main(args: Array[String]): Unit = {
    // val -> 声明常量, 常量时无法修改值的, 异常信息为reassignment to val
    val no: Int = 10

    // var -> 声明变量, 可以改变其引用值
    // 但是, 在scala中, 通常建议使用val
    // 如spark的大型复杂系统中, 需要大量的网络传输数据, 如果使用var, 可能会担心值被错误更改
    var num: Int = 10

    // 指定类型
    // 无论声明val常量, 还是var变量, 都可以手动指定其类型;
    // 如果不指定类型的话, scala会自动根据值进行腿短
    val name: String = null
    var info: Any = "felix"

    // 声明多个变量、常量
    // 可以将多个声明昂在一起声明
    var message, vendor: String = "ZY0808"
    val num1, num2 = 10
    println(message)

    // 数据类型
    // Byte    	8位有符号补码整数。  -2^8  ~ (2^8  - 1)
    // Short	  16位有符号补码整数。 -2^16 ~ (2^16 - 1)
    // Int	    32位有符号补码整数。 -2^32 ~ (2^32 - 1)
    // Long	    64位有符号补码整数。 -2^64 ~ (2^64 - 1)
    // Float	  32 位, IEEE 754标准的单精度浮点数
    // Double	  64 位, IEEE 754标准的单精度浮点数
    // Char	    16位无符号Unicode字符,区间值为 U+0000 到 U+FFFF
    // String	     字符序列
    // Boolean	true或false
    // Scala没有基本数据类型与包装类型的概念, 统一都是类。Scala会负责基本数据类型和引用类型的转换操作
    val const: String = 1.toString
    
    // 类型的加强版类型
    // scala使用很多加强类给数据类型增加了很多功能和函数
    // 如: String类通过StringOps类增强了大量的函数, 如下:
    val resStr: String = "hello".intersect("world")
    println(resStr)
    
    // Scala还提供了RichInt、RichDouble、RichChar等类型。
    val range: Range.Inclusive = 1.to(2) // 等价于 1 to 10
    for(index <- range) {
      if(range.end == index)
        println(index)
      else
        print(index + ", ")
    }
    val sum = 1.+(1) // 等价于: 1 + 1
    println("1.+(1) = " + sum)

    /**
      * lazy值<br。
      * 在scala中, 提供了lazy值得特性, 也就是说, 如果将一个变量声明为lazy, 则只有在第一次使用该变量时,<br>
      * 变量对于的表达式才会发生计算。这种特性对于耗时的计算操作特别有用, 比如打开文件进行IO, 进行网络IO等。
      */
    lazy val lines = fromFile("./lazy.txt").mkString
    // 即使文件不存在, 也不会报错, 只有第一次使用该变量时会报错, 证明表达式计算的lazy特性
    // println(lines)
  }
}