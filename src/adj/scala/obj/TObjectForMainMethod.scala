package adj.scala.obj

/**
  * main方法。
  * 就如同java中,如果要运行一个程序,必须编写一个包含main方法类一样;
  * 在scala中,如果要运行一个应用程序,那么必须有一个main方法,作为入口。
  * scala中的main方法定义为def main(args: Array[tring]), 而且必须在scala的object中。
  *
  * 除了自己实现main方法外, 还可以继承App Trait, 然后将需要在main方法中运行的代码, 直接作为
  * object的构造器代码,而且用args可以接受传入的参数。
  *
  * App Trait的工作原理：App Trait继承自DelayedInit Trait, scalac命令进行编译时, 会把
  * 继承App Trait的object的构造器代码放在DelayedInit的delayedInit方法中执行
  */
object TObjectForMainMethod extends App {
  if (args.isEmpty) println("args is empty")
  else println("args size = " + args.length)
}
