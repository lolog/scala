package adj.scala.imconvert

object ImplicitConvert {
  def main(args: Array[String]): Unit = {
    /**
      * 隐式转换
      * 要实现隐式转换,只要程序可见的范围内定义隐式转换即可。scala会自动使用隐式转换函数。
      * 隐式转换函数与普通函数唯一的语法区别就是：要以implicit修饰,而且最好要定义函数的返回类型。
      */
    println("隐式转换")
    // 案例：特殊受骗窗口(只接收特殊人群,比如学生、老人等)
    class TicketSpecialPerson (val name: String)
    class TicketStudent(val name: String)
    class TicketOlder(val name: String)

    // 隐式转换函数
    implicit def object2SpecialPerson(obj: Object): TicketSpecialPerson = {
      if(obj.getClass == classOf[TicketStudent]) {
        val stu = obj.asInstanceOf[TicketStudent]
        new TicketSpecialPerson(stu.name)
      }
      else if(obj.getClass == classOf[TicketOlder]) {
        val older = obj.asInstanceOf[TicketOlder]
        new TicketSpecialPerson(older.name)
      }
      else Nil
    }
    def sellSpecialTicket(p: TicketSpecialPerson): Unit = {
      println("Sell to " + p.name)
    }
    val stu = new TicketStudent("student")
    val older = new TicketOlder("older")
    sellSpecialTicket(stu)
    sellSpecialTicket(older)

    /**
      * 使用隐式转换加强现有类型
      * 隐式转换强大的一个功能,就是可以再不知不觉中加强现有类型的功能。也就是说,可以为现有类定义一个加强版的类,并且定义
      * 互相之间的隐式转换,从而让源类在使用加强版的方法时,由scala自动进行隐式转换为加强肋,然后再调用该方法。
      */
    // 案例：超人变身
    println("\n使用隐式转换加强现有类型")
    class SMan(val name: String)
    class Superman(val name: String) {
      def emitLaser = println(name + ", emit a laster!")
    }
    // 隐式转换
    implicit def man2superman(man: SMan): Superman = new Superman(man.name)
    val man = new SMan("SMan")
    man.emitLaser

    /**
      * 导入隐式转换函数作用域与导入
      * scala默认会使用2中隐式转换,一种是源类型(或者目标类型的伴生对象内的隐式转换函数),一种是当前程序作用域内的可以用唯一
      * 标志符表示的隐式转换函数。
      * 如果隐式转换函数不在上述两种情况下的话,那么就必须手动import语法引入某个包下的隐式转换函数,比如import test._。通常
      * 建议,仅仅在需要进行隐式转换的地方,比如某个函数或者非法内,用import导入隐式转换函数,这样可以缩小隐式转换函数作用域,
      * 避免不需要的隐式转换。
      *
      *
      * 隐式转换发生的时机
      * 1.调用某个函数,但是给函数传入参数的类型与函数定义的接收参数类型不匹配时(案例：特殊售票窗口)。
      * 2.使用某个类型的对象,调用某个方法,而这个方法并不存在与该类型时(案例：超人变身)。
      * 3.使用某个类型的对象,调用某个方法,虽然该类型有这个方法,但是给方法传入的参数类型与方法定义的接收参数的类型不匹配（案例：特殊售票窗口加强版)。
      */
    // 案例：特殊售票窗口加强版
    println("\n隐式转换发生的时机-特殊售票窗口加强版")
    class TicketHouse {
      var ticketNumber = 0
      def sellSpecialTicket(p: TicketSpecialPerson) = {
        ticketNumber += 1
        println(p.name + ", T-" + ticketNumber)
      }
    }
    val ticketHouse = new TicketHouse
    ticketHouse.sellSpecialTicket(stu)
    ticketHouse.sellSpecialTicket(older)

    /**
      * 隐式参数
      * 所谓的隐式参数,指的是在函数或者方法中,定义一个用implicit修饰的参数,此时scala会尝试找到一个指定类型的,用
      * implicit修饰的对象,即隐式值,并注入参数。
      * scala会在两个范围内查找：一种是当前作用域内可见的val或者var定义的隐式变量,一种是隐式参数类型的伴生对象的隐式值。
      */
    // 考试签到
    println("\n隐式参数")
    class SignPen {
      def write (content: String) = println("[SignPen] " + content)
    }
    // 很多人只需要一支签到笔就OK了
    implicit val signPen = new SignPen
    // 方法的implicit signPen: SignPen回去作用域找到signPen的隐式值
    def signForExam(content: String)(implicit pen: SignPen): Unit = {
      pen.write(content)
    }
    signForExam("Alice")
  }
}
