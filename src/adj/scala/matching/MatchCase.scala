package adj.scala.matching

import java.io.FileNotFoundException

object MatchCase {
  def main(args: Array[String]): Unit = {
    /**
      * 模式匹配
      * scala没有java的switch case语法的,相对应的,scala提供了更加强大的
      * match case语法,即模式匹配,类替代switch case,match case也称为模式匹配。
      * scala的match case与java的switch case最大的不同点在于,java的switch case
      * 仅能匹配变量的值,而scala的match case可以匹配各种情况,比如变量的类型、集合的
      * 元素、有值或者无值。
      * match case的语法如下：变量 match (case 值 => 代码)。
      * 如果值为下划线,代表不满足以上所有情况下的默认情况如何处理。此外,match case中,
      * 只要一个case分支满足并处理了,就不会继续判断下一个case分支了。(与java不同,java
      * switch case需要用break阻止。)
      *
      * match case最基本的使用,就是对值进行模式匹配。
      */
    // 成绩评价
    println("模式匹配")
    def judgeGrade(grade: String): Unit = {
      grade match {
        case "A" => println("excellent")
        case "B" => println("good")
        case "C" => println("middle")
        case "D" => println("normal")
        case _ => println("bad")
      }
    }
    judgeGrade("A")

    /**
      * 模式匹配中使用if进行守卫
      * scala中的模式匹配语法,有一个特点在于可以在scala后的条件判断中,不仅仅只是提供一个值,
      * 而是可以在值后再加一个if守卫,进行双重过滤。
      */
    // 成绩评价(升级版)
    println("\n模式匹配中使用if进行守卫")
    def judgeGradeUpdate(name: String, grade: String): Unit = {
      grade match {
        case "A" => println(name + ", you are excellent")
        case "B" => println(name + ", you are good")
        case "C" => println(name + ", you are middle")
        case "D" if name == "keven" => println(name + ", you are normal...")
        case _ => println("bad")
      }
    }
    judgeGradeUpdate("keven", "D")

    /**
      * 在模式匹配中进行变量赋值
      * scala的模式匹配语法,有一个特点在于,可以将模式匹配默认情况的下划线替换为一个变量名,此时模式匹配就会将要匹配的值
      * 赋值给这个变量,从而可以在后面的处理语句中使用要匹配的值。
      *
      * 为什么有这种语法?
      * 因为只要使用case匹配到的值,是不是我们就知道这个值了！！！在这个case的处理语句中,是不是就直接可以使用写程序时就知道的值。
      * 但是对于下划线这种情况,所有不满足的case的值,都会接入下滑下处理,此时如果我们处理语句中需要拿到具体的值进行处理呢?那就需
      * 要使用这种模式匹配中进行变量赋值的语法！！！
      */
    println("\n在模式匹配中进行变量赋值")
    def judgeGradeVar(name: String, grade: String): Unit = {
      grade match {
        case "A" => println(name + ", you are excellent")
        case "B" => println(name + ", you are good")
        case "C" => println(name + ", you are middle")
        case "D" if name == "keven" => println(name + ", you are normal...")
        case _grade => println("grade " + _grade + " is not existed")
      }
    }
    judgeGradeVar("keven", "F")

    /**
      * 对类型进行模式匹配
      * scala的模式匹配一个强大之处就在于,可以直接匹配类型,而不是值！！！这点是java的switch case绝对做不到的。
      *
      * 理论知识：对类型如何进行匹配?
      * 其他语法与匹配值其实是一样的,但是匹配类型的话,就是要用"case 变量:类型 => 代码"这种语法,而不是匹配值的
      * "case 值 => 代码"语法。
      */
    println("\n对类型进行模式匹配")
    def processException(e: Exception): Unit = {
      e match {
        case e1: IllegalArgumentException => println("arguments exception")
        case e2: FileNotFoundException => println("file not found")
        case _: Exception => println("unknown exception")
      }
    }
    processException(new FileNotFoundException())

    /**
      * 对Array和List进行模式匹配
      * 对Array进行模式匹配,分别可以匹配带着知道元素的数组、带有指定个数元素的素组、以某元素打头的数组。
      * 对List进行模式匹配,与Array类似,但是需要使用List特有的操作符。
      */
    // 案例：对朋友打招呼
    def greeting_array(arr: Array[String]): Unit = {
      arr match {
        case Array("Leo") => println("Hi Leo")
        case Array(grt1, grt2, grt3) => println("Hi, nice to meet you. " + grt1 + " and " + grt2 + " and " + grt3)
        case _ => println("Hey. who are you?")
      }
    }
    greeting_array(Array("Lucy", "Andru", "Jaxi"))
    def greeting_list(list: List[String]): Unit = {
      list match {
        case "Leo"::Nil => println("Hi Leo")
        case grt1::grt2::grt3::Nil => println("Hi, nice to meet you. " + grt1 + " and " + grt2 + " and " + grt3)
        case "Leo"::tail => println("Hi Leo, please introduce your friends for me")
        case _ => println("Hey. who are you?")
      }
    }
    greeting_list(List("Leo", "Jack"))

    /**
      * case class与模式匹配
      * scala中提供了一种特殊的类,用case class进行声明,中文也可以称为样例类,case class其实有点类似于javaBean的概念,
      * 即只定义field,并且由scala编译时自动提供getter和setter方法,但是没有method。
      * case class的主构造函数接收的参数不需要使用var或val修饰,scala自动就会使用val修饰(但是如果你自己使用var修饰,那么还是会按照var来)。
      * scala自动为case class定义了伴生对象,也就是object,并且定义了apply()方法,改方法接收主构造函数中相同的参数,并返回case class对象。
      */
    // 案例：学校门禁
    println("\ncase class与模式匹配")
    class SPerson
    case class STeacher(name: String, subject: String) extends SPerson // STeacher为SPerson的伴生对象
    case class SStudent(name: String, classroom: String) extends SPerson
    def judgeIdentify(p: SPerson): Unit = {
      p match {
        case STeacher(name, subject) => println("Teacher name is " + name + ", subject is" + subject)
        case SStudent(name, classroom) => println("Student name is " + name + ", classroom is " + classroom)
        case _ => println("illegal access, please go out of the school")
      }
    }
    val p: SPerson = SStudent("felix", "A01")
    judgeIdentify(p)

    /**
      * Option与模式匹配
      * scala有一种特殊的类型,叫做Option, Option有2种值, 一种是Some, 表示有值; 一种是None,表示无值。
      * Option通常会用于模式匹配中,用于判断某个变量是有值还是无值,这笔null来得更加简洁明了。
      * Option的用法必须掌握,因为Spark源码中大量地使用了Option, 比如Some(a)、None这种语法,
      * 因此必须看得懂Option模式匹配,才能读得懂Spark源码。
      */
    // 案例：成绩查询
    println("\nOption与模式匹配")
    val grades = Map("Leo" -> "A", "Jack" -> "B", "Jen" -> "C")
    def getGrade(name: String): Unit = {
      val grade = grades.get(name)
      grade match {
        case Some(grade) => println("your grade is " + grade)
        case None => println("Sorry, your grade information is not in the system")
      }
    }
    getGrade("Leo")
  }
}
