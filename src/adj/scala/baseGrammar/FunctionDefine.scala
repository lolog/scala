package adj.scala.baseGrammar

/**
  * <pre>
  * scala中, d定义函数时, 需要指定函数的函数名、参数、函数体。
  * scala要求必须给出所有参数的类型, 但是不一定给出函数返回值的类型。
  * 只要右侧的函数体中不包含递归语句,scala就可以根据右侧的表达式推断出返回类型。
  *
  * def 函数名 ([参数列表:参数类型]) [: 返回值类型 =] {
  *   function body
  *   [expr]
  * }
  * </pre>
  */
object FunctionDefine {
  /**
    * main方法。
    * 就如同java中,如果要运行一个程序,必须编写一个包含main方法类一样;
    * 在scala中,如果要运行一个应用程序,那么必须有一个main方法,作为入口。
    * scala中的main方法定义为def main(args: Array[tring]), 而且必须在scala的object中。
    *
    * 除了自己实现main方法外, 还可以继承App Trait, 然后将需要在main方法中运行的代码, 直接作为
    * object的构造器代码,而且用args可以接受传入的参数。
    */
  def main(args: Array[String]): Unit = {
    // 有返回值类型的函数定义
    val sum: Int = addInt(10, 11)
    println("addInt(10, 11) = " + sum)

    println("\n====================\n")

    // 没有返回值类型的函数定义
    val sub = subInt(10, 11)
    println("subInt(10, 11) = " + sub)

    println("\n====================\n")

    // 没有返回值的函数定义
    val sub0 = subDouble(10, 11)
    println("subDouble(10, 11) = " + sub0)

    println("\n====================\n")

    // 无参函数2种调用方式
    doAction
    doAction()
    // 只能这种方式调用
    doAction_xxx

    println("\n====================\n")

    // 当行函数体的定义
    val res: Int = sumInt(8, 9)
    println("sumInt(8, 9) = " + res)

    println("\n====================\n")

    // 递归函数
    // 斐波那数列
    val fabRes: Int = fab(4);
    println("fab(4) = " + fabRes)

    println("\n====================\n")

    // 默认参数
    var inc: Int = incr(10)
    println("incr(10) = " + inc)

    // 带名参数
    inc = incr(a = 10, b = 2)
    println("incr(a=10, b=2) = " + inc)

    inc = incr(10, b = 2)
    println("incr(10, b=2) = " + inc)

    println("\n====================\n")

    // 变长参数
    val resAdd = add(1, 2, 3, 4)
    println("add(1, 2, 3, 4) = " + resAdd)

    // 使用序列调用变长参数
    // 如果想将一个已有的序列直接调用变长参数,是不对的,比如, val s = sum(1 to 5)。
    // 此时需要使用scala特殊的语法:将序列转换为变长参数, 让scala解释器能够识别。
    // 这种语法非常有用, 在spark源码中大量使用到。
    val rangeRes = add(1 to 5: _*)
    println("add(1 to 5:_*) = " + rangeRes)

    // 可变参数, 递归调用
    val rangeTRes = addT(1 to 5: _*)
    println("addT(1 to 5:_*) = " + rangeTRes)

    // 过程的定义, 过程调用是没有返回值的
    val pres0 = process();
    val pres1 = process("hello World")
    println("process, res0 = " + pres0 + ", res1 = " + pres1)
  }

  /**
    * 带参数和返回值类型<br>
    * 函数体中有多行代码, 则可以使用代码块的方式包裹多行代码, 代码中最后一行的返回值是整个函数的返回值。<br>
    * 与Java不同, 不是使用return返回值的。
    */
  def addInt(a: Int, b: Int): Int = {
    val sum: Int = a + b
    // 返回值, 可以是一个不是赋值语句的表达式
    sum + 0
  }

  /**
    * 带参数, 没有返回值类型
    */
  def subInt(a: Int, b: Int) = {
    val sub: Int = a - b
    // 返回值, 可以是一个变量
    sub
  }

  /**
    * <pre>
    * 带参数, 没有返回值类型
    * 如果参数没有"=", 那么函数时没有返回值的, 即如下格式是没有返回值的
    *
    * def 函数名 ([参数列表:参数类型])  {
    *   function body
    *   [expr]
    * }
    * </pre>
    */
  def subDouble(a: Double, b: Double) {
    val sub: Double = a - b
    // 返回值, 可以是一个变量
    sub
  }

  /**
    * 无参函数定义
    */
  def doAction(): Unit = {
    println("do Action")
  }
  // 等价于
  def doAction_xxx: Unit = {
    println("do Action XXX")
  }

  /**
    * 单行的函数
    */
  def sumInt(a: Int, b: Int): Int = a + b

  /**
    * 递归函数<br>
    * scala要求必须指定函数的返回值类型
    */
  def fab(n: Int): Int = if (n <= 1) 1 else fab(n - 1) + fab(n - 2)

  /**
    * 默认参数<br>
    * 调用的时候, 如果给出的参数个数不够, 则会从左往右依次应用参数。<br>
    *
    * 带名参数<br>
    * 在调用函数时, 也可以不按照函数定义的参数顺序传递参数, 而是使用待命参数的方式传递。<br>
    * 还可以混合使用未命名参数和带名参数, 但是未命名参数必须排在带名参数前面
    */
  def incr(a: Int, b: Int = 1): Int = a + b

  /**
    * 变长参数<br>
    * scala中, 有时我们需要将函数定义为参数个数可变的情形, 此时可以使用变长参数定义函数
    */
  def add(args: Int*): Int = {
    var res: Int = 0
    for (arg <- args) res += arg
    res
  }

  /**
    * 变长参数递归调用<br>
    * scala中, 有时我们需要将函数定义为参数个数可变的情形, 此时可以使用变长参数定义函数
    */
  def addT(args: Int*): Int = {
    if (args.isEmpty) 0
    else args.head + addT(args.tail: _*)
  }

  /**
    * 在scala中, 定义函数体直接包裹在了花括号里面, 而没有使用=链接, 则函数的返回值类型为Unit,或者声明函数的返回值类型为Unit。<br>
    * 这样的函数称之为过程。过程常用语不需要返回值的函数。
    */
  def process(): Unit = {
    "hello World"
  }

  def process(str: String) {
    str
  }
}