## 引言
scala的Actor类似于Java中的多线程编程。但是不同的是,scala的Actor提供的模型与多线程有所不同。
Scala的Actor尽可能地避免锁和共享状态,从而避免多线程并发时出现资源争用的情况,进而提升多线程编程的
性能。此外,scala Actor的这种模型还可以避免死锁等一系列传统多线程编程的问题。

Spark中使用的分布式多线程框架是Akka,Akka也是实现了类似于scala Actor的模型,其核心概念同样也是
Actor。因此只要掌握Scala Actor, 那么在Spark源码研究时,至少即可看明白Akka Actor相关的代码。
但是,换句话说,由于Spark内部使用大量的Akka Actor,因此对于Scala Actor也是至少必须掌握,这样才能
学好Sprk源码。

课程大纲：
1.Actor的创建、启动和消息收发(案例：Actor Hello World)。
2.收发case class类型的消息(案例：用户注册登录后台系统)。
3.Actor之间互相收发消息(案例：打电话)。
4.同步消息和Future