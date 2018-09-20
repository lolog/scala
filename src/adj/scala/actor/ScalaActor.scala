package adj.scala.actor

import scala.swing.Dialog.Message

object ScalaActor {
  def main(args: Array[String]): Unit = {
    /**
      * Actor的创建、启动和消息收发
      * Scala提供了Actor Trait来让我们方便进行Actor多线程编程,就Actor Trait就类似于Java的Thread和Runnable一样,
      * 是基础的多线程类和接口。我们只要重写Actor Trait的act方法,即可实现自己的现场执行体,与Java中重写run方法类似。
      * 此外,使用start启动actor,使用符号"!"向actor发送信息, Actor内部使用receive和模式匹配接收消息。
      */
    // Actor Hello World
    println("Actor的创建、启动和消息收发")
    import scala.actors.Actor
    class HelloActor extends Actor {
      override def act(): Unit = {
        receive{
          case name: String => println("Hello. " + name)
        }
      }
    }
    val helloActor = new HelloActor
    helloActor.start()
    // 发送消息
    helloActor ! "mouse"

    /**
      * 收发case class类型的消息
      * Scala的Actor模型与Java的多线程模型自检,很大的一个区别就是：Scala Actor天然支持线程之间的精准通信,即一个Actor可以
      * 给其他Actor直接发送消息。这个给你是非常强大和方便。
      * 要给Actor发送消息,需要使用"Actor ! 消息"语法。在Scala中,通常建议使用样例类,即case class来作为小心脚下发送。然后
      * 在Actor接收消息之后,可以使用Scala强大的模式匹配功能来解析不同消息的处理。
      */
    // 案例：用户注册登录后台接口
    Thread.sleep(10)
    println("\n收发case class类型的消息")
    case class Login (username: String, password: String)
    case class Register(username: String, password: String)
    class UserManagerActor extends Actor {
      var times: Int = 0
      override def act(): Unit = {
        while (times < 2) {
          times += 1
          receive {
            case Login(username, password) => println("Login {username=" + username + ", password=" + password + "}")
            case Register(username, password) => println("Register {username=" + username + ", password=" + password + "}")
          }
        }
      }
    }
    val userManagerActor = new UserManagerActor
    userManagerActor.start()
    userManagerActor ! Register("felix", "felix")
    userManagerActor ! Login("felix", "felix")

    /**
      * Actor之间相互收发消息
      * 如果两个Actor之间要相互收发消息,那么Scala的建议是：一个Actor想另一个Actor发送消息时,同事带上自己的引用,
      * 其他Actor收到自己的消息时,直接通过发送消息的Actor引用,既可以给他回复消息。
      */
    // 案例：打电话
    Thread.sleep(10)
    println("\nActor之间相互收发消息")
    case class Message(content: String, sender: Actor)
    class LeoTelphoneActor extends Actor {
      override def act(): Unit = {
        receive {
          case Message(content, sender) => {
            println("[Leo Receive] "+ content)
            sender ! "I'm Leo, Please class me after 10 minutes"
          }
        }
      }
    }
    class JackTelphoneActor(val leo: Actor) extends Actor {
      override def act(): Unit = {
        leo ! Message("Hi Leo, I'm Jack", this)
        receive {
          case response: String => println("[Jack Receive] "+ response)
        }
      }
    }
    val leo = new LeoTelphoneActor
    val jack = new JackTelphoneActor(leo)
    leo.start()
    jack.start()

    /**
      * 同步消息和Future
      * 默认情况下,消息都是异步的;但是如果希望发送的消息是同步的,即对方接收后,一定要给对方返回结果,
      * 那么可以使用!?的方式发送消息。
      * 即：val reply = actor !? message。
      *
      * 如果要异步发送一个消息,但是在后续要获得消息的返回值,那么可以使用Future。即!!语法。
      * var future = actor !! message
      * val reply = future()
      */
  }
}
