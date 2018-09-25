package adj.scala.advance

import scala.collection.mutable.ArrayBuffer

object InnerOuterClazz {
  def main(args: Array[String]): Unit = {
    /**
      * 内部类的作用域：外部类对象
      * 内部类可以访问外部内的所有对象。
      */
    class Class {
      private[this] val locationAddr: String = "SZU"

      val className: String = "Class-01"

      class Student(val studentName: String) {
        // 内部内可以访问外部内的变量
        println("    " + className + ":" + studentName)

        // 访问外部类的方法
        info

        // 内部类还可以访问外部类的private[this]方法
        def details = locationAddr + ":" + studentName
      }

      val students = new ArrayBuffer[Student]

      def register(name: String): Student = {
        new Student(name)
      }

      def info = println("    Class: " + className)
    }

    println("内部类访问外部类的权限")
    val clazz = new Class
    val leo = clazz.register("Leo")
    clazz.students += leo


    /**
      * 扩大内部类的作用域：伴生对象
      */
    println("\n扩大内部类的作用域：伴生对象")
    object BClass {
      class BStudent(val studentName: String)
    }
    class BClass {
      val students = new ArrayBuffer[BClass.BStudent]()

      def register(studentName: String) = new BClass.BStudent(studentName)
    }
    val bClass = new BClass
    val jack = bClass.register("Jack")
    bClass.students += jack

    /**
      * 扩大内部类作用域：类型投影
      */
    class TYClass {
      class TYStudent(val studentName: String){}

      val students = new ArrayBuffer[TYClass#TYStudent]()

      def register(studentName: String) = new TYStudent(studentName)
    }
    val tyc = new TYClass
    val sandy = tyc.register("Sandy")
    tyc.students += sandy

    /**
      * 内部类获取外部类的引用
      */
    println("\n内部类获取外部类的引用")
    class YYClass(val className: String) {
      // => 左边的变量为外部类的引用, 即下面的outer
      outer => class YYStudent(val studentName: String) {
        // 内部类通过outer拿到外部类的引用
        outer.details

        def introduceMyself = println("    Hello, my name is " + studentName + ", happy join class " + className)
      }
      def register(studentName: String) = new YYStudent(studentName)

      def details = println("   =>YYClass")
    }
    val yyc = new YYClass("01")
    val lucy = yyc.register("Lucy")
    lucy.introduceMyself


  }
}