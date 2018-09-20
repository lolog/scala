package adj.scala.obj

import scala.beans.BeanProperty
import scala.collection.mutable.ArrayBuffer

/**
  * geter和setter方法。
  * scala定义不带private的var field, 此时scala生成的面相Jvm类时, 会定义为private的字段, 并且提供public的getter和setter方法。
  * 而如果使用private修饰field, 则生成的getter和setter也是private的。
  * 如果定义val field, 则只会生成getter方法。
  * 如果不希望生成getter和setter方法, 则将field声明为private[this]
  *
  * 注意：getter和setter方法分别为：name和name=
  * 变量必须被初始化
  */
class SimpleClazz {
  // 不带private修饰的var field, field为private, 为其提供public的getter和setter方法。
  var age = 10

  // private修饰的var field,生成的getter和setter也是private权限
  private var name = "felix"

  // val field, 只会生成getter方法
  val info = "information"

  // field没有getter和setter方法,
  private[this] val mark = "mark"
  private[this] var description = "description"

  // 方法定义
  def print: Unit = println("name = " + name)

  /**
    * 如果只是希望拥有简单的getter和setter方法,那么按照scala提供的语法规则,根据需求为field选择合适的修饰符就好; var、val、private、private[this]。
    * 但是如果希望能够自己对getter和setter进行控制, 则可以自定义getter与setter方法。
    * 自定义setter方法的时候一定要注意scala的语法限制,签名、=、参数间不能有空格。
    */

  private var my_sid = 1101;

  def sid: Int = my_sid

  def sid_=(value: Int): Unit = {
    my_sid = value;
  }

  /**
    * 如果你不希望field有setter方法, 在可以为val, 但是此时就在也不能更改field的值了。
    * 但是, 如果希望能够仅仅暴露出一个getter方法, 并且还能通过某些方法更改field的值, 那么需要综合使用private以及自定义getter方法。
    * 此时, 由于field是private的, 所以setter和getter都是private, 对外界没有暴露l 自己实现修改field值的方法, 自己可以覆盖getter方法
    */
  private var my_message = "message"

  def message = my_message

  /**
    * private[this]的使用
    * 如果将field使用private来修饰, 那么代表这个field是类私有的。
    * 在类的方法中, 可以直接访问类的其他对象的private field。
    * 在这种情况下, 如果不希望field被其他对象访问到, 那么可以使用private[this], 意味着对象私有的field, 只有本对象内可以访问到。
    *
    * 区别：
    * private : 类私有, 类中可以访问
    * private[this] : 对象私有, 只能在当前实例内才能访问
    */
  def call(clazz: SimpleClazz): Unit = {
    // 不能调用private[this] field
    println(clazz.my_message)
  }

  /**
    * Java风格的getter和setter方法
    * scala的getter和setter方法的命名与java时不同的, 时field和field_=的方式。
    * 如果要让scala自动生成java风格的getter和setter方法,只要给field添加@BeanProperty注解即可。
    * 此时会生成4个方法: field:Type、field_=(value:Type):Unit、getField():Type、setField(value:Type):Unit
    */
  @BeanProperty var alias: String = "alias"
}

class JField(@BeanProperty var alias: String)

/**
  * 辅助constructor
  */
class SConstructor {
  // scala中, 可以给类定义多个辅助constructor, 类似于java的构造函数重载
  // 辅助constructor之间可以互相调用, 而且第一行必须调用主constructor
  private var name = ""
  private var age = 0

  // 辅助constructor
  def this(name: String) {
    this()
    this.name = name
  }

  def this(name: String, age: Int) {
    this()
    this.name = name
    this.age = age
  }

  def print = println("{name=" + name + ", age=" + age + "}")
}

/**
  * 主constructor
  * scala中, 主constructor是与类名放在一起的, 与java不同。
  * 而且类中, 没有定义在任何方法或者代码块之中的代码,就是主constructor的代码, 这点感觉没有java那么清晰。
  *
  * 如果主constructor传入的参数什么修饰都没有, 比如field:Type, 如果内部的地方使用到了,
  * 则会声明为private[this] field, 否则没有该field, 就只能被constructor代码使用而已
  */
class MConstructor(var name: String, var age: Int = 10) {
  // scala中, 可以给类定义多个辅助constructor, 类似于java的构造函数重载
  // 辅助constructor之间可以互相调用, 而且第一行必须调用主constructor

  // 辅助constructor
  // 注意：如果辅助构造器覆盖了主构造器,那么就不能定义其他的辅助类了
  //def this(name: String, mark: Int) {
  //  this(name, 0)
  //}

  def this(name: String, mark: String) {
    this(name, 0)
  }

  def print = println("{name=" + name + ", age=" + age + "}")
}

/**
  * 内部类
  * scala中,同样可以在类中定义内部类,但是与java不同的是,每个外部类的对象的内部类, 都是不同的类
  */
class OutClazz {
  class InnerClazz(var name: String) {
    // 创建对象时调用
    println("name = " + name)
  }

  var innerArrays = new ArrayBuffer[InnerClazz]()
  def getInner(name: String) = new InnerClazz(name)
}