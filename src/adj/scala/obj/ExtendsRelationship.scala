package adj.scala.obj


object ExtendsRelationship {
  def main(args: Array[String]): Unit = {
    val childrenClazz = new ChildrenClazz("children", 0, false)

    var parentClazzTmp: ParentClazz = null
    var childrenClazzTmp: ChildrenClazz = null

    /**
      * isInstanceOf和asInstanceOf
      * 如果我们创建了子类的对象,但是又将其赋予了父类类型的变量。则在后续的程序中,我们又需要将父类类型的变量转换为子类类型的变量,应该如何做呢?
      * 首先,需要使用isInstanceOd判断呢对象是否是指定类或者其子类的对象,如果是的话,则可以使用asInstanceOf将对象转换为指定类型。
      * 注意：如果对象时null,则isInstanceOf一定返回false,asInstance一定返回null。
      * 注意：如果没有用isInstanceOf先判断对象是否为指定类的实例,就直接用asInstanceOf转换,则可能会抛出异常。
      */
    if (childrenClazz != null) {
      if (childrenClazz.isInstanceOf[ChildrenClazz]) {
        // 虽然转换为ParentClazz类型,但是其还是ChildrenClazz的实例
        parentClazzTmp = childrenClazz.asInstanceOf[ParentClazz]
        childrenClazzTmp = childrenClazz.asInstanceOf[ChildrenClazz]
        // 调用
        parentClazzTmp.print
        childrenClazzTmp.print
      }
      if (childrenClazz.isInstanceOf[ParentClazz]) {
        parentClazzTmp = childrenClazz.asInstanceOf[ParentClazz]
        childrenClazzTmp = childrenClazz.asInstanceOf[ChildrenClazz]
        // 调用
        parentClazzTmp.print
        childrenClazzTmp.print
      }
    }

    /**
      * getClass和classOf
      * isInstance只能判断出对象是否是指定类以及其子类的对象,而不能精确判断出对象就是指定类的对象。
      * 如果要求精确地判断对象就是指定类的对象,那么久只能使用getClass和classOf了。
      * 对象.getClass可以精确获取对象的类,classOf[类]可以精确获取类,然后使用==操作符即可判断。
      */
    val children = new ChildrenClazz("children", 0, false)
    println("classOf and getClass, " + (children.getClass == classOf[ChildrenClazz]))

    /**
      * 使用模式匹配进行类型的判断
      * 但是在实际开发中,比如spark的源码中,大量使用模式匹配的方式进行类型的判断,这种方式和更加简洁明了,而且代码的可维护性和可扩展性也非常高。
      * 使用模式匹配,功能上来说与isInstanceOf一样,也是判断主要是该类以及该类的子类的对象即可,不是精确判断的。
      */
    val mchildren: ParentClazz = new ChildrenClazz("childrem", 0, false)
    println("模式匹配")
    mchildren match {
      case per: ParentClazz => per.print
      case _ => println("unknown type")
    }

    // protected
    println("protected")
    children.info

    children.innerClazz("foo", 10)

    // 抽象类
    val p = new Student("adolf")
    p.information
    println("abstract field = " + p.mark)
  }
}

/**
  * scala中,让子类继承父类,与java应用,也是使用extends关键字。
  * 继承代表,子类可以从父类继承父类的field和method,然后子类可以在自己内部放入父类所没有,子类特有的field和method。实现代码复用。
  * 子类可以覆盖父类的field和method,但是如果父类用final修饰,field和method用final修饰,则子类时无法被继承的,field和method无法被覆盖。
  */
class ParentClazz(val crapy: String, val grud: Int) {
  // final方法不能被覆盖
  final val version = "1.0.0"

  val e_age: Int = 20
  private val e_name = "extends"

  def name = e_name

  /**
    * protected
    * 跟java一样,scala中同样可以使用protected关键字来修饰field和method,这样在子类中就不需要super关键字,直接就可以访问field和method。
    * 还可以使用protected[this], 则只能在当前子类对象中访问父类的field和method,无法通过其他子类对象访问父类的field和method。
    */
  protected var clazz: String = "01"
  protected[this] var hobby: String = "game"

  def print = println("ParentClazz " + e_name)
}

/**
  * override和super
  * scala中,如果子类覆盖一个父类中的非抽象方法,则必须使用override关键字。
  * 注意：override只能覆盖父类的val变量,并且子类和父类的field都只能为val声明的
  * override关键字可以帮助我们尽早地发现代码里的错误,比如:override修饰的父类方法的方法名我们拼写错误,比如需要覆盖的父类方法的参数写错等等。
  * 此外,在子类覆盖父类方法之后,如果我们在子类中就是要调用父类被覆盖的方法和属性呢?那就可以使用super关键字,显示地指定要调用的父类方法和属性。
  */
class ChildrenClazz(override val crapy: String, grud: Int, var disabled: Boolean) extends ParentClazz(crapy, grud) {
  /**
    * 调用父类的constructor
    * scala中,每个类可以有一个主constructor和任意多个辅助的constructor,而每个辅助constructor的第一行都必须时调用
    * 其他辅助constructor或主constructor;因此子类的辅助constructor是一定不可能直接调用父类的constructor的。
    * 只能在子类的主constructor中调用父类的constructor, 以下这种语法就是通过子类的主构造函数来调用父类的构造函数。
    * 注意：如果是父类中接收的参数,比如crapy和age,子类中接收时,就不要用任何val或var来修饰了,否则会认为是子类要覆盖父类的field。
    * 注意：子类构造器参数override [var|val] field:Type认为父类的该field被覆盖了。
    */
  def this(crapy: String) {
    this(crapy, 0, false)
  }
  def this(age: Int) {
    this("kafka", 0, false)
  }

  private var e_score = "B"
  // 覆盖父类属性, 子类只能覆盖可见的属性或者方法
  // scala只能覆盖val变量
  override val e_age: Int = 18

  def score = e_score

  // 覆盖父类方法
  override def name = "Hi, " + super.name

  override def print: Unit = println("ChildrenClazz " + super.name)

  // hobby对象只能自己访问
  def info = println("class " + clazz + " will start " + hobby)

  /**
    * 匿名内部类
    * 在scala中,匿名子类时非常常见的,而且非常强大。spark的源码中也大量使用了这种匿名子类。
    * 匿名子类,也就是说,可以定义一个没有名称的子类,并直接创建七对,然后将对象的引用赋予一个变量。
    * 之后甚至可以将该匿名子类的对象传递给其他函数。
    */
  def innerClazz(scrapy: String, age: Int) = {
    // 匿名内部类
    val p = new ParentClazz(scrapy, age) {
      def display(scray: String, age: Int): Unit = {
        println("Inner Class {scray =" + scray + ", age = " + age + "}")
      }
    }
    p.display("sado", 10)
  }
}

/**
  * 抽象类
  * 如果在父类中,有某些方法无法立即实现,而需要依赖于不同的子类来覆盖,重写实现自己不同的方法实现。
  * 此时,可以将父类中的这些方法不给出具体的实现,只有方法签名,这种方法就是抽象方法。
  * 而一个类中,如果有一个抽象方法,那么累就必须用abstract来声明为抽象类,此时抽象类时不可以实例化的。
  * 在子类照片那个覆盖抽象类的抽象方法时,不需要使用override关键字
  */
abstract class Person(val name: String) {
  def information

  /**
    * 抽象field
    * 如果父类中,定义了field,但是没有给出初始值,则此field为抽象field。
    * 抽象field意味着,scala会根据自己的规则,为var或val类型的field生成对应的getter和setter方法,但是父类中是没有该field的。
    * 子类必须覆盖field,以定义自己的具体field,并且覆盖抽象field,不需要使用override关键字
    */
  val mark: String
}
class Student(name: String) extends Person(name) {
 def information = println("abstract implement")

  val mark: String = "left"
}