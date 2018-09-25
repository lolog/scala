// 第三种方式：文件顶部定义
package adj.scala.advance

object PackageImport {
  def main(args: Array[String]): Unit = {
    /**
      * ****************************************************************
      * package
      * 为什么要有package?
      * 因为要对多个同名的类进行命名空间的管理,避免同名类发生冲突。
      * 比如说：scala.collection.mutable.Map和scala.collection.immutable.Map
      * ****************************************************************
      */
    val pc = new adj.scala.info.PackageClass("package-class")
    val tec = new adj.scala.test.TEClass("test-class")

    /**
      * package特性
      * 1.同一个包定义,可以在不同的scala源文件中; 一个scala源文件,可以包含2个包。
      * 2.子包中的类,可以访问父包中的类。
      * 3.层级包中,默认情况导入的包为相对路径的包;如果需要导入绝对路径的包,需要添加_root_.前缀导入。
      * 4.定义package对象。package内的成员,可以直接访问package对象内的成员。
      * 5.package可见性
      */
    val serviceName = Service.defaultName
    val serviceName_ = Service.serviceName

    /**
      *****************************************************************
      * import
      * 如果没有import,那么每次创建某个包下的类的对象,都得使用new 包.类 格式创建对象。
      * 所有如果用了import,那么你只要先import 包名,然后new 类,即可。
      *
      * 特性：
      * 1.用import 包路径._这种格式,可以导入包下的所有成员。
      * 2.scala和java不同之处在于,任何地方都可以使用import,比如类里、方法内,这种方式的好处在于,可以在一些作用域范围内导入。
      * 3.选择器、重命名、隐藏。
      * 4.隐式导入
      *****************************************************************
      */
    // 选择器、重命名、隐藏
    import java.awt.{Color, Font}             // 仅仅导入java.awt包下的Color、Font类
    import java.util.{HashMap => JavaHashMap} // 将导入的类进行重命名
    import java.util.{HashMap => _,_}         // 导入java.util包下所有的类,但是隐藏掉HashMap

    // 隐式导入
    // 每个scala程序默认都会隐式导入以下几个包下所有的成员
    import java.lang._
  }
}

package object Service {
  // package可见性,即advice包级的可见性
  private[advance] val serviceName = "Service"
  // package对象的属性
  val defaultName = "Service"
}

// package定义的第一种方式：多层级package定义(比较差的做法,一般不这么干)
package adj {
  package scala {
    package info {
      class PackageClass(val name: String) {
        /**
          * 在子包中使用scala.connection.mutable.ArrayBuffer会报错,
          * 因为默认情况下,只会使用相对的包名,绝对包名需要添加_root_.前缀。
          *
          * 报错
          * val array = new scala.collection.mutable.ArrayBuffer[Int]()
          */
        val array = new _root_.scala.collection.mutable.ArrayBuffer[Int]()
      }
    }
  }
}

// 第二种定义：串联式package定义(也不怎么样,也不这么干)
package adj.scala {
  package test {
    class TEClass(val name: String) {}
  }
}