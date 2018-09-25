package adj.scala.advance

object ScalaAnnotation {
  def main(args: Array[String]): Unit = {
    /**
      * 什么是注解？
      * 注解其实说白了,就是好在我们的代码中,加入一些特殊的标记。
      * 然后,我们的Scala编译器,就可以在编译的时候,在碰到注解的时候,做一些特殊操作。
      * 一个经典的例子就是@BeanProperty注解,我们之前讲解过的,给某个field添加这个
      * 注解之后,Scala编译器就会给field编译出新的getter和setter方法。
      *
      * 在什么地方用到注解呢？
      * 在Scala中,可以给类、方法、field、variable、parameter添加注解。而且,Scala是支持给某个对象添加多个注解的。
      * 这里有一些特例：如果要给类的构造函数添加注解,那么需要在构造函数前添加注解,并且加上一堆圆括号。
      * 还可以给表达式添加注解,此时需要在表达式后面加上冒号以及注解。
      */
    val scores = Map("Leo" -> 10, "Jack" -> 20)
    (scores.get("Leo"): @unchecked) match {
      case Some(score: Int) => println("score=" + score)
    }
    // 除此之外,还可以给类型参数和变量的类型定义添加注解。

    /**
      * 自定义注解
      * 必须继承Annotation Trait
      */
    class IntMin(var min: Int = 10) extends annotation.Annotation

    class ANPerson @unchecked() (val name: String, @IntMin val age: Int)
    @IntMin class IAPerson


    // 如果注解的参数是value的话那么也可以不用指定注解的参数名
    class AnMin(var value: Int) extends annotation.Annotation
    // 等价于
    class AnyMin extends annotation.Annotation

    /**
      * 常用注解
      * 其实,里面很多都是针对java中的概念提供的
      * @volatile var name = "leo"                轻量级的java多线程并发安全控制
      * jvm: java虚拟机中,可以有多个线程,每个线程都有自己的工作区,还有一块所有线程共享的工作区.
      * 每次一个线程拿到一个哥哥的变量,都需要从共享区拷贝一个副本到自己的工作区中使用,和修改。
      * 然后修改完后,再在一个合适的时机,将副本的值写回到共享区中。
      *
      * 这里就会出现一个多线程并发访问安全的问题。
      * 多个线程如果同时拷贝了变量副本,都做了不同的修改
      * 然后依次将副本修改的值,写回到共享区中,会依次覆盖掉之前的一些副本值
      * 就会出现变量的值,是不符合期望的
      * 咋们的系统,出现了错误和bug
      *
      * volatile关键字修饰的变量
      * 它可以保证,一个线程在从共享区获取一个变量的副本时,都会强制刷新一下这个变量的值
      * 保证自己获取到的变量的副本值是最新的
      * 所有这样子做呢,是一种轻量级的多线程并发访问控制方法
      *
      * 但是也不是100%保险的,还是有可能会出现错误的风险。
      *
      * @transient var name = "leo"               瞬态字段,不会序列化这个字段
      * 序列化默认会将一个对象中所有的字段的值,都序列化到磁盘文件中区,然后反序列化的时候,
      * 还可以获取这些字段的值。加了transient的字段是瞬态的,序列化和反序列化的时候不会序列化这个字段。
      *
      * @native                                   标注用o实现的本地方法
      * @SerialVersionUID(value)                  标记类的序列化版本号
      * 序列化版本号,这个是什么意思呢？
      * 如果我们将一个雷的对象序列化到磁盘文件上了,结果过一段时间之后,这个类在代码中改变了,
      * 此时如果你将磁盘文件的对象反序列化回来,就会报错,因为你的序列化对象的结构与代码中的类结果已经不一样了。
      * 针对类改变了,就会重新生成一个序列化版本号。
      * 反序列化的时候,就会发现序列化类型的版本号和代码中的类的版本号不一样。
      *
      * @throws(classOf[Exception]) def test() {} 给方法标记要抛出的checked异常
      * @varargs def test(args: String*){}        标记方法接收的时变长参数
      * @BeanProperty                             标记生成JavaBean风格的setter和getter方法
      * @BooleanBeanProperty                      标记生成is风格的getter方法,用于Boolean类型的field
      * @deprecated(message="")                   让编译器提示警告
      * @unchecked                                让编译器提示类型转换的警告
      */
  }
}
