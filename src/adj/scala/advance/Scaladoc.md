##Scaladoc的使用
Scala是什么：Scala Api文档, 包含scala所有的API以及使用说明, class、object、trait、
function、method、implicit等。

为什么要查阅Scaladoc: 如果只是写一些普通的Scala程序, 课程中讲解(Scala编程详解)的内容
基本够用了;但是(在现在、未来,实际的工作环境中)如果要编写复杂的Scala程序, 那么还是需要
参考Scaladoc的。

通过url：http://www.scala-lang.org/api/current#package, 可以在线浏览。

以下是一些Scaladoc使用的Tips：
1.直接在左上角的搜索框中, 搜索你需要寻找的包、类即可。
2.O和C, 分别代表了某个类的伴生对象以及伴生类的概念。
3.标记为implicit的方法, 代表的时隐式转换。
4.举例：搜索StringOps, 可以看到String的增强类, StringOps的所有方法说明。