package adj.scala.obj

object ObjectTrait {
  def main(args: Array[String]): Unit = {
    val person = new SPerson("adolf")
    person.info
    person.makeFriends
    person.log("message")
    println("trait field age = " + person.age)
    println("abstract field, message=" + person.message)

    /**
      * 为实例混入Trait
      * 有时,我们可以再创建类的对象时,指定该对象混入某个Trait,这样就只有这个对象混入该Trait,而类的其他对象则没有。
      * 混入的方法不能和原来的类有冲突。
      */
    val allPerson = new SPerson("felix") with Logger
    allPerson.error("object with trait")

    /**
      * Trait调用链
      * scala中,支持让类继承多个Trait后,一次调用多个Trait中的同一个方法,只要让多个Trait的同一个方法,在最后都执行super方法即可。
      * 类中调用多个Trait中都有的方法时,首先会从最右边的Trait的方法开始执行,然后一次往左执行,形成一个调用链条。
      * 这种特性非常强大,其实就相当于设计模式的责任链模式的一种就是实现依赖。
      */
    println("Linked callable")
    val linkPerson = new SPerson("felix") with Logger
    linkPerson.handle("link")

    println("混合使用Trait的具体方法和抽象方法")
    val work = new LocalWork
    work.run


    println("\nTrait构造器")
    new LocalTTask

    /**
      * Trait field的初始化
      * 在scala中,trait时没有接受参数的构造函数,这是trait与class唯一的区别,但是如果要求对trait的field
      * 进行初始化,该怎么办呢? 只能使用scala中非常特殊的一种高级特性 - 提前定义。
      *
      * 另一种方式：使用lazy
      */
    // 提前定义
    val conn = new {
      val header = "Info"
    } with Conn with TLConn
    println("\nTrait field的初始化, header=" + conn.header + ", body="+conn.body)
    val con = new {
      val header = "Before Define"
    } with TLConn {}
    println("\nTrait field的初始化, header=" + conn.header + ", body="+con.body)
  }
}

trait BaseTrait {
  def handle(arg: String) = println("BaseTrait handle, " + arg)
}

trait PeopleTrait extends BaseTrait {
  def info

  override def handle(arg: String) = {
    println("PeopleTrait handle")
    super.handle(arg)
  }
}

/**
  * 将Trait作为接口使用
  * scala中的Trait时一种特殊的概念
  * 首先,我们可以将Trait作为接口来使用,此时的Trait就与Java中的接口非常类似。
  * 在trait中可以定义抽象方法,就与抽象类中的抽象方法一样,只要不给出方法的具体实现即可。
  * 类可以使用extends关键字继承Trait。
  * 注意：这里不是implement,而是extends。
  * scala里面每天implement的概念,无论继承类还是trait,统一都是extends。
  * 类继承trait后,必须实现其中的抽象方法,实现时不需要使用override关键字。
  * scala不支持对类就多继承,但是支持多重继承trait,使用with关键字即可。
  */
trait FriendsTrait extends BaseTrait {
  def makeFriends

  /**
    * 在Trait中定义具体字段
    * scala中的Trait可以定义具体field,此时继承Trait的类就自动获得了Trait中定义的field,
    * 但是这种获取field的方式与继承class是不同的,如果是继承class获取的field,实际是定义在
    * 父类中的,而继承Trait获取的field,就直接被添加到了类中。
    */
  val age: Int = 10

  /**
    * 在Trait中定义抽象字段
    * 在scala中的Trait可以定义抽象的field,而Trait中的具体方法则可以基于抽象field来编写。
    * 但是,继承Trait的类,必须覆盖抽象field,提供具体的值。
    */
  val message: String

  /**
    * 在Trait中定义具体方法
    * scala中的Trait可以不是只定义抽象方法,还可以定义具体方法,此时Trait更像是包含了通用工具方法的类。
    * 有一个专有的名词来形容这种情况,就是说Trait的功能混入了类。
    * 举例来说,Trait可以包含一些很多类都通用的功能方法,比如打印日志等等,spark中就使用了Trait来定义通用的日志打印方法。
    */
  def log(message: String) = println(message)

  override def handle(arg: String) = {
    println("FriendsTrait handle")
    super.handle(arg)
  }
}

class SPerson(val name: String) extends PeopleTrait with FriendsTrait {
  override val message: String = "Message"

  override def info: Unit = println("info")

  override def makeFriends: Unit = println("make friends")
}

trait Logger {
  def error(message: String) = println("[error] = " + message)
}


trait OxyTrait {
  def sub
}

trait OxyTraitImpl extends OxyTrait {
  /**
    * 在Trait中覆盖抽象方法
    * 在Trait中,是可以覆盖父Trait的抽象方法的。
    * 但是覆盖时,如果使用了super.方法的代码,则无法通过编译。因为,super.方法就会去掉用父Trait的抽象方法,此时子Trait的该方法还是会被认为是抽象的。
    * 此时,如果要通过编译,就会给子Trait的方法加上abstract override修饰。
    */
  abstract override def sub: Unit = {
    super.sub
  }
}

trait BaseWork {
  def before

  def run = {
    println("run")
    before
  }
}

/**
  * 混合使用Trait的具体方法和抽象方法
  * 在Trait中,可以混合使用具体方法和抽象方法。
  * 可以让具体方法依赖于抽象方法,而抽象方法则放到继承Trait的类中去实现。
  * 这种Trait气势就是设计模式的模板设计模式的体现。
  */
class LocalWork extends BaseWork {
  override def before = {
    println("before")
  }
}

/**
  * Trait的构造机制
  * 在scala中,Trait也是有构造代码的,也就是Trait中不包含在任何方法中的代码,
  * 而继承了Trait类的构造机制如下：
  * 1.父类的构造函数执行。
  * 2.Trait的构造代码执行,多个Trait从左往右一次执行。
  * 3.构造Trait时,会先构造父Trait,如果多个Trait继承同一个Trait,则父Trait只会构造一次。
  * 4.所有Trait构造完毕之后,子类的构造函数执行。
  */
class TTask {
  println("class TTask")
}

trait TLogger {
  println("trait TLogger")
}

trait SQLTLogger extends TLogger {
  println("trait SQLTLogger")
}

trait TimeTLogger extends TLogger {
  println("trait TimeTLogger")
}

class LocalTTask extends TTask with SQLTLogger with TimeTLogger {
  println("class LocalTTask extends TTask with SQLTLogger with TimeTLogger")
}

/**
  * Trait field的初始化
  */
trait TLConn {
  /**
    * Trait field的初始化
    * 在scala中,trait时没有接受参数的构造函数,这是trait与class唯一的区别,但是如果要求对trait的field
    * 进行初始化,该怎么办呢? 只能使用scala中非常特殊的一种高级特性 - 提前定义。
    *
    * 另一种方式：使用lazy
    */
  val header: String
  lazy val body: String = null
}
class Conn
class TLFtp extends TLConn {
  override val header: String = "TLFtp Header"
  override lazy val body: String = "TLFtp body"
}

/**
  * 在scala中,trait也可以继承自class,此时这个class就会成为所有继承该trait类的父类
  */
class AbstractSTask {
  def info(message: String) = println("AbstractSTask info")
}
trait SLogger extends AbstractSTask {
  def log(message: String) = info(message)
}