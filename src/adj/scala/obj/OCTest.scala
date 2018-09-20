package adj.scala.obj

object OCTest {
  def main(args: Array[String]): Unit = {
    val clazz: SimpleClazz = new SimpleClazz

    clazz.age = 10
    clazz.print

    // 调用自定义的setter方法
    clazz.sid_=(1201)
    println("sid = " + clazz.sid)

    println("message = " + clazz.message)

    val claz: SimpleClazz = new SimpleClazz
    clazz.call(claz)

    /**
      * Java风格的getter和setter
      */
    clazz.setAlias("Alias")
    println("alias = " + clazz.getAlias)
    println("alias = " + clazz.alias)

    val jfield: JField = new JField("alias")
    println("alias = " + jfield.getAlias)

    // 辅助构造器
    val sc: SConstructor = new SConstructor("feliix", 10)
    sc.print

    // 主构造器和辅助构造器
    var mc: MConstructor = new MConstructor("felix", 20) // 主构造器
    mc.print
    mc = new MConstructor("felix") // 辅助构造器
    mc.print

    // 内部类
    val outClazz: OutClazz = new OutClazz
    // 内部类只能被外部类的静态实例调用
    var innerClazz = new outClazz.InnerClazz("felix")
    var newInnerClazz = outClazz.getInner("felix")
    // innerClazz属于outClazz类的内部类
    // 注意：outClazz必须声明为val类型,不然报错：Error occurred in an application involving default arguments
    outClazz.innerArrays += newInnerClazz


    //========================  object ====================//
    // 不能调用其private field, 它只能被自身调用
    println("TObject object version = "+ TObject.getInfo)
    // 伴生对象的调用
    val oc = new TObject
    oc.print

    // 伴生对象调用伴生类的私语field
    TObject.print(oc)

    // object继承抽象类
    println("object继承抽象类")
    MySQLOparate.select("select * from test")

    // apply方法
    println("apply方法")
    val arrayPersons = ArrayPerson("felix", "adolf", "kafka")
    for(person <- arrayPersons) {
      person.print
    }

    // object实现枚举
    println("\nobject实现枚举")
    // 访问
    val season = Season(0)
    println(season == Season.SPRING)
    // 访问
    val oseason = OSeason.withName("spring")
    println(oseason == OSeason.SPRING)
    for(ele <- OSeason.values) {
      print(ele + "(" + ele.id + "," + ele.toString + ")  ")
    }
  }
}
