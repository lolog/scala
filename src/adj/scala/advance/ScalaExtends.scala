package adj.scala.advance

/**
  * 重写field的提前定义、Scala继承层级、对象相等性
  */
object ScalaExtends {
  def main(args: Array[String]): Unit = {
    /**
      * 重写field的提前定义
      * 默认情况下,如果父类中的构造函数代码,用到了会被子类重写的field,那么会出现令人意想不到的一幕。
      * 1.子类的构造函数调用父类的构造函数。
      * 2.父类的构造器函数初始化field(结果正确)。
      * 3.父类的构造函数使用field执行其他构造代码,但是此时该field的getter方法被重写,将返回默认值(Int -> 0)。
      * 4.子类的构造器函数再执行,重写field。
      * 5.但是此时子类从父类继承的代码,已经出现错误了。
      */
    class FStudent {
      val classNumber: Int = 10
      val classScores: Array[Int] = new Array[Int](classNumber)
    }
    class FPStudent extends FStudent {
      override val classNumber: Int = 3
    }
    val fs = new FPStudent
    println("field override")
    println("   array size = " + fs.classScores.length)
    // 分析：期望,FPStudent可以从FStudent继承来一个长度为3的classScores数组。
    // 结果：FPStudent对象,只有一个长度为0的classScores数组。
    // 解决方案：此时只能使用Scala对象继承的一个高级特性：提前定义,即在父类构造函数执行之前,先执行子类的构造函数中的某些代码。
    class FPStudentOk extends {
      override val classNumber: Int = 3
    } with FStudent
    val fpo = new FPStudentOk
    println("   ok, array size=" + fpo.classScores.length)

    /**
      * +++++++++++++  Scala的继承层级   ++++++++++++++
      * 这里我们大概知道一下Scala的继承层级,我们写的所有Scala Trait和class,都是默认继承自一些Scala根的类,有一些基础的方法。
      *
      * Scala中,最顶端的两个Trait是Nothing和Null, Null Trait唯一的对象就是null, 其次是继承Nothing Trait的Any类。
      * 接着Anyval Trait和AnyRef类,都是继承Any类。
      * Any是一个比较重要的类,其中定义了isInstanceOf和asInstanceOf等方法,以及equals、hashCode等基本方法。Any类,有点像Java中的Object类。
      * AnyRef类,增加了一些多线程的方法,比如wait、notify/notifyAll、synchronized等,也是属于Java Object类的一部分。
      *
      * +++++++++++++  对象的相等性   ++++++++++++++
      * 这里,我们要知道,在Scala中,你如何判断两个引用变量,是否指向一个对象实例？
      * AnyRef的eq方法用于检查2个变量是否指向同一个对象实例。
      * AnyRef的equals方法默认调用eq方法实现, 也就是说,默认情况下,判断2个变量想等,要求必须指向同一对象实例。
      * 通常情况下,自己可以重写equals方法,根据类的fields来判定是否相等。
      * 此外,定义equals方法时,也最好使用对象同样的fields重写hashCode方法。
      *
      * 如果只是想要简单地通过是否指向同一个对象实例判定变量是否相等,那么直接使用==操作即可。默认判断null,然后调用equals方法。
      */
    class NeProduct(val name: String, val price: Double) {
      final override def equals(other: Any) = {
        val that = other.asInstanceOf[NeProduct]
        if(that == null) false
        else name == that.name && price == that.price
      }
      final override def hashCode = 13 * name.hashCode + 17 * price.hashCode
    }
    val p1 = new NeProduct("p", 1.0)
    val p2 = new NeProduct("p", 1.0)
    println("\nScala equals & hashCode")
    println("equal = " + (p1 == p2))
  }
}
