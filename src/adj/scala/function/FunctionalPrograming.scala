package adj.scala.function

import java.awt.event.{ActionEvent, ActionListener}

import javax.swing.{JButton, JFrame}

object FunctionalPrograming {
  def main(args: Array[String]): Unit = {
    /**
      * 函数直接赋值给变量
      * scala中的函数是一等公民,可以独立定义,独立存在,而且可以直接将函数作为值复制给变量。
      */
    def work(threadName: String) = println("[thread name] = " + threadName)

    val workFunc = work _
    workFunc("main")

    /**
      * 匿名函数
      * scala中,函数也可以不需要命名,此时函数称为匿名函数。
      * 可以直接定义函数之后,将函数赋值给某个变量;也可以将直接定义的匿名函数传入其他函数之中。
      * scala定义匿名函数的语法规则： (参数名: 参数类型) => 函数体。
      * 这种匿名函数的语法必须深刻理解,在spark的源码中有大量的语法。
      */
    val runFunc = (name: String) => println("[not name function] args = " + name)
    runFunc("thread")

    /**
      * 高阶函数
      * scala中,由于函数是一等公民,因此可以直接将某个函数传入其他函数作为参数,
      * 这个功能极其强大,也是java这种面向对象的变成语言所部具备的。
      * 接收其他函数作为参数的函数,也称为高阶函数(higher-order function)
      */
    println("\n高阶函数")
    def start(func: (String) => Unit, name: String) = func(name)
    start(work, "higher-order function")

    val arr = Array(1, 2, 3, 4).map((ele: Int) => ele * ele)
    for(ele <- arr) {
      print(ele + " ")
    }

    // 将函数作为另一个功能是：函数作为返回值
    def working(message: String) = (name: String) => println("\n{name="+ name + ", message=" + message + "}")
    val workingFunc = working("working")
    workingFunc("thread")

    /**
      * 高阶函数的类型推断
      * 高阶函数可以自动推断参数类型,而不需要写明类型;
      * 而且对于只有一个参数的函数,还可以省去其小括号;
      * 如果仅有的一个参数在右侧的函数体内只使用一次,则还可以将接受参数省略,并且将参数用_来替代。
      */
    def starting(func: String => Unit, name: String) = func(name)
    starting(name => println("[starting] name=" + name), "main thread")
    // 或者
    //starting((name: String) => println("[starting] name=" + name), "main thread")

    def triple(func: Int => Int) = func(3)
    val res = triple(4 * _) // _代表func(3)的参数3, 即为函数 func: (arg:Int)=> Int = 4 + arg
    println("res = " + res)

    /**
      * scala的常用高阶函数
      */
    // map: 对传入的每个元素都进行映射,返回一个处理后的元素
    var aRes = Array(1, 2, 3, 4).map(2 * _) // 返回Array(2, 4, 6, 8)
    // foreach: 对传入的每个元素处理,但是没有返回值
    (1 to 5).map(2 * _ + " ").foreach(print _)

    // filter: 对传入的每个元素都解析条件判断,如果对元素返回true,则保留该元素,否则过来该元素
    println("filter")
    for(ele <- (1 to 20).filter(_ % 2 == 0)) print(ele + " ")

    // reduceLeft:从左侧元素开始,进行reduce操作,即先对元素1和元素2进行处理,然后将结果与元素3处理。
    // 再将结果与元素4处理,以此类推,即为reduce
    val reduceRes = (1 to 9).reduceLeft(_ + _)
    println("\nreduceLeft = " + reduceRes)

    // sortedWith: 对元素进行两两相比, 进行排序
    val sortedArr = Array(3, 2, 5, 4, 10 ,1).sortWith(_ < _)
    println("sortWith")
    for(ele <- sortedArr) print(ele + " ")

    /**
      * 闭包
      * 闭包最简洁的解释：函数在变量不处于作用域时,还能够对变量解析访问,即为闭包。
      */
    println("\n\n闭包")
    def connect(conn_type: String) = (sub_type: String) => {
      // scala会将conn_type声明为val变量,即不可以更改
      // conn_type += ":"
      println(conn_type + ":" + sub_type)
    }
    val sqlConnect = connect("SQL")
    val ftpConnect = connect("FTP")
    sqlConnect("MySQL")
    sqlConnect("Oracle")
    // 两次调用connect函数,传入不同的conn_type,并且创建不同的函数返回。
    // 然而,conn_type只是一个局部变量,却在connect执行之后,还可以继续存在创建的函数之中,
    // 调用sqlConnect("MySQL")时,值为"SQL"的conn_type被保留在了函数体内部,可以反复的事业这种变量。
    // 这种变量超出了其作用域,还可以使用,即为闭包。

    // scala通过为每个函数创建来实现闭包,实际上对于connect函数创建的函数,conn_type是作为函数对象的变量存在的,因此每个函数才可以拥有不同的conn_type。

    // scala编译器会确保上述闭包机制


    /**
      * SAM转换
      * 在Java中,不支持直接将函数传入一个方法作为参数,通常来说,唯一的办法就是定义一个实现某个接口的类的实例对象。
      * 该对象只有一个方法,而这些接口都只有单个的抽象方法,也就是single abstract method, 在Java中被称为SAM类型。
      *
      * 由于scala是可以调用Java的代码的,因此当我们调用Java的某个方法时,可能就不得不创建SAM传递给方法,非常麻烦,
      * 但是scala又是直接传递函数的。此时就可以使用scala提供的,在调用java方法时,使用的功能,SAM转换,即将SAM转换为
      * scala函数
      *
      * 要使用SAM转换,需要使用scala提供的特性,隐式转换
      */
    var count = 0
    val frame = new JFrame("SAM")
    val button = new JButton("Increment")
    // java方式
    // button.addActionListener(new ActionListener {
    //    @override public void actionPerformed(event: ActionEvent) {
    //       count += 1
    //       println(count)
    //    }
    // })
    // 隐式转换
    implicit def makeAction(action: ActionEvent => Unit) = new ActionListener {
        override def actionPerformed(event: ActionEvent) = action(event)
    }
    // 使用匿名函数
    button.addActionListener((event: ActionEvent) => {count += 1; println(count)})
    frame.setContentPane(button)
    frame.pack()
    frame.setVisible(true)

    /**
      * Currying函数,即科里函数
      * Currying函数:将原来接收2个参数的一个函数,转换为2个函数,第一个函数接收原先的第一个参数,
      * 然后返回接收原先第二个参数的第二个函数。
      * 在函数调用的过程中, 就变为了2个函数连续调用的形式。
      */
    println("\nCurrying函数")
    def sum(a: Int, b: Int) = a + b
    val sumRes = sum(1, 1)
    // 等价于
    def sum2(a: Int) = (b: Int) => a + b
    val sum2Res = sum2(1)(1)
    // 等价于
    def sum3(a: Int)(b: Int) = a + b
    val sum3Res = sum3(1)(1)
    println("sum(1, 1) = " + sumRes)
    println("sum2(1)(1) = " + sum2Res)
    println("sum3(1)(1) = " + sum3Res)

    /**
      * return
      * scala中, 不需要使用return来返回函数值的,函数最后一行非赋值语句的值就是函数的返回值。
      * 在scala中,return用于在匿名函数中返回值给包含匿名函数的带名函数,并作为带名函数的返回值。
      * 使用return的匿名函数,是必须给出返回类型的,否则无法通过编译。
      */
    def greeting(name: String) = {
      def sayHello(name: String): String = return "Hello. " + name
      sayHello(name)
    }
    val greetingStr = greeting("felix")
    println("\nreturn = " + greetingStr)
  }
}