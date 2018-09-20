package adj.scala.obj

import scala.collection.mutable.ArrayBuffer

/**
  * Object
  * object相当于class的单个实例,通常在里面放一些静态field或者method。
  * 第一次调用object的方法时,就会执行object的constructor, 也就是object内部不在method中的代码, 但是object不能定义接收参数的constructor。
  * 注意：object的constructor只会在其第一次被调用时执行一次,以后再次调用就不会再次执行constructor。
  * object通常用于作为单例模式的实现, 或者放class的静态成员, 比如工具方法。
  */
object TObject {
  private val VERSION = "1.0.0"

  // object TObject第一次被访问的时候, 被调用
  println("\ninitial associated object VERSION = " + VERSION)

  def getInfo: String = VERSION

  def print(tclazz: TObject) = {
    println("associated object call class mark=" + tclazz.mark)
  }
}

/**
  * 伴生对象
  * 如果有一个class, 还有一个与class同名的Object, 那么就称这个object时class的伴生对象,class时object的伴生类。
  * 伴生对象和伴生类必须放在一个.scala文件之中。
  * 伴生对象和伴生类, 最大的特点就在于：相互可以访问private field。
  */
class TObject(var name: String = "associated class", var info: String = "Info") {
  private val mark: String = "associated class"

  def print = println("TObject class version= " + TObject.VERSION)
}

/**
  * object继承抽象类
  * object的功能其实和class类似,除了不能定义接收参数的构造器之外,
  * object也可以继承抽象类,并且覆盖抽象类中的方法。
  */
abstract class SQLOparate(val types: String) {
  def select(sql: String): Unit = println(types + " : " + sql)
}

object MySQLOparate extends SQLOparate("MySQL") {
  override def select(sql: String): Unit = super.select(sql)
}

/**
  * apply方法
  * object中非常重要的一个特殊方法,就是apply。
  * 通常在伴生对象中实现apply方法, 并在其中实现构造伴生类的对象的功能。
  * 而创建伴生类的对象时,通常不会使用new Class的方式,而是使用Class()的方式,隐式地调用伴生对象的apply方法, 这样会让对象创建更加简洁。
  *
  * 比如：Array类的伴生对象的apply方法就实现了接收可变数量的参数,并创建一个Array对象的功能。
  * val arr = Array(1, 2, 3, 4, 5)
  */
class TPerson(var name: String) {
  def print = println("{name=" + name + "}")
}

object ArrayPerson {
  def apply(names: String*): ArrayBuffer[TPerson] = {
    var persons = new ArrayBuffer[TPerson]()
    for (name <- names) {
      persons += new TPerson(name)
    }
    persons
  }
}

/**
  * scala没有直接提供类似于Java的enum这样的枚举特性,如果要实现枚举,则需要用object继承Enumeration类,并且调用Value方法来初始化枚举值
  */
object Season extends Enumeration {
  val SPRING, SUMMER, AUTUMN, WINTER = Value
}

/**
  * 还可以通过Value传入枚举值的id和name,通过id和toString可以获取,还可以通过id和name来查找枚举值
  */
object OSeason extends Enumeration {
  val SPRING = Value(0, "spring")
  val SUMMER = Value(1, "summer")
  val AUTUMN = Value(2, "autumn")
  val WINNTER = Value(3, "winter")
}