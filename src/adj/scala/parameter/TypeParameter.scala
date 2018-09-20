package adj.scala.function

object TypeParameter {
  def main(args: Array[String]): Unit = {
    /**
      * 泛型类
      * 泛型类,顾名思义,其实就是在类的声明中,定义一些泛型内部,比如field或者method,就可以使用这些泛型类型。
      * 使用泛型类,通常是需要对类中的某些成员,比如某些field和method中的参数或变量,进行统一的类型限制,这样
      * 可以保证程序更好的健壮性和稳定性。
      * 如果不适用泛型进行统一的类型限制,那么在后期程序运行过程中,难免会出现问题,比如传入了不希望的类型,导致程序出问题。
      * 在使用类的时候,比如创建类的对象,将类参数替换为实际的类型,即可。或者,直接使用了泛型类型的field赋值时,scala会自动机械类型推断。
      */
    // 案例：新生报到,每个学生来自不同的地方,id可能是Int, 可能是String
    println("泛型类")
    class Student[T] (val localId: T) {
      def getSchoolId(cardId: T) = println("S-" + cardId + "-" + localId)
    }
    val leo = new Student[Int](111)
    leo.getSchoolId(123456)

    /**
      * 泛型函数
      * 泛型函数,与泛型类类似,可以给某个函数在声明时指定泛型类型,然后在函数体内,多个变量或者返回值之间,就可以使用
      * 泛型类型进行声明,从而对某个特殊的变量,或者多个变量,进行强制性的类型限制。
      * 与泛型类一样,你可以通过给使用了泛型类型的变量传递值来让scala在佛那个推断类型的实际类型,也可以在函数调用函数
      * 时,手动指定泛型类型。
      */
    // 案例：卡片售货机,可以指定卡片的内容,内容可以是String类型或Int类型。
    println("\n泛型函数")
    def getCard[T] (content: T) = {
      if(content.isInstanceOf[Int]) "card:[Int], " + content
      else if(content.isInstanceOf[String]) "card: [String], " + content
      else "card: [Other], " + content
    }
    var card = getCard[Int](1)
    println(card)
    card = getCard(0.01)
    println(card)

    /**
      * 上下边界Bounds
      * 在指定泛型类型的时候,有时我们需要对泛型类型的范围进行界定,而不是可以是任意的类型。比如,我们可能要取某个
      * 泛型类型,他就必须是某个类或者其子类,这样在程序中就可以放心地调用泛型类型继承的父类的方法,程序才能正常的使用和运行。
      * 此时就可以使用上下边界Bounds的特性。
      * scala的上下边界特性允许泛型类型必须是某个类的子类,或者必须是某个类的父类。
      */
    // 案例：在派对上交朋友
    // 上边界Bounds：<:表示, 泛型类指定接收的类必须是这个类或者该类的子类
    println("\n上边界Bounds")
    class PDPerson(val name: String) {
      def greet = println("Hello, I'm " + name)
      def makeFrieds(p: PDPerson) ={
        greet
        p.greet
      }
    }
    class PDStudent(name: String) extends PDPerson(name)
    class Party[T <: PDPerson](p1: T, p2: T) {
      def play = p1.makeFrieds(p2)
    }
    val p1 = new PDPerson("Sandru")
    val p2 = new PDStudent("Jake")
    new Party[PDPerson](p1, p2).play

    // 案例： 领身份证
    // 下边界：>:表示,泛型类指定接收的类必须是这个类或者该类的父类
    class Father(val name: String)
    class Child(name: String) extends Father(name)
    def getIDCard[T >: Child](p: T): Unit = {
      if(p.getClass == classOf[Child]) println("please tell me your parents' name")
      else if(p.getClass == classOf[Father]) println("please sign your name to get your child's id card.")
      else println("Sorry, you are not allowed to get id card...")
    }
    val f = new Father("Father")
    getIDCard(f)

    /**
      * View Bounds
      * 上下边界Bounds,虽然可以让一种泛型类型,支持有父子关系的多种类型。但是,在某个类与上下边界Bounds指定的父子类型
      * 范围内的类都没有任何关系,则默认是肯定不能接受的。
      * 然后,View Bounds作为一种上下边界Bounds的加强版,支持可以对类型进行隐式转换,将指定的类型进行隐式转换后,再判断
      * 是否在边界指定的类型范围内。
      */
    // 案例：跟小狗交朋友
    println("\nView Bounds")
    class GDog(val name: String) {def greet = println("Wang, wang, I'm " + name)}
    implicit def dog2person(d: Object): PDPerson = {
      if(d.isInstanceOf[GDog]) {
        val _dog = d.asInstanceOf[GDog]
        new PDPerson(_dog.name)
      }
      else Nil
    }
    class GParty[T <% PDPerson] (P1: T, p2: T)
    val dog = new GDog("Dog")
    new Party[PDPerson](dog, p1).play

    /**
      * Context Bounds
      * Context Bounds是一种特殊的Bounds,它会根据泛型类型的声明,比如"T:类型"要求必须存在一个类型为"类型[T]"的
      * 隐式值。其实个人认为,Context Bounds之所以叫Context,是因为它基于的是一种全局的上下文,需要使用到上下文中的
      * 隐式值。
      */
    println("\nContext Bounds")
    // 案例：使用scala内置的比较器比较大小
    class Calculator[T: Ordering] (val p1: T, val p2: T) {
      def max(implicit order: Ordering[T]) = {
        if (order.compare(p1, p2) > 0) p1
        else p2
      }
    }
    val cal = new Calculator[Int](1, 2)
    println("max = " + cal.max)

    /**
      * Manifest Context Bounds
      * 在scala中,如果要实例化一个泛型数组,就必须使用Manifest Context Bounds。也就是说,如果数组元素类型为T的话,
      * 需要为类或函数定义[T:Manifest]泛型类型,这样才能实例化Array[T]这种泛型数组。
      */
    // 案例：打包饭菜(一种食品打成一包)
    println("\nManifest Context Bounds")
    class Meat(val name: String)
    class Vegetable(val name: String)
    def packageFood[T: Manifest] (foods: T*): Array[T] = {
      val fooPackages = new Array[T](foods.length)
      for(i <- 0 until foods.length) fooPackages(i) = foods(i)
      fooPackages
    }
    val meatpackages: Array[Meat] = packageFood(new Meat("Meat-01"), new Meat("Meat-02"))
    for(meat <- meatpackages) {
      print(meat.name + " ")
    }

    /**
      * 协变和逆变
      * scala的协变和逆变是非常有特色的！完全解决了Java中的泛型的一大缺憾。
      * 举例来说,Java中如果有Professional时Master的子类,那么Card[Professional]是不是Card[Master]的子类？
      * 答案：不是。因此对于开发程序造成很多的麻烦。
      *
      * 而scala中,只要灵活使用协变和逆变,就可以解决Java泛型的问题。
      * C[+T]：如果A是B的子类, 那么C[A]是C[B]的子类。
      * C[-T]：如果A是B的子类, 那么C[B]是C[A]的子类。
      * C[T]：无论A和B是什么关系, C[A]和C[B]没有从属关系
      */
    // 案例：进入会场
    class Master
    class Professional extends Master

    // 协变
    class MCard[+T] (val name: String)
    // 允许MCard[Master]以及其的协变子类,即其协变子类为MCard[Professional]
    def enterMeet(card: MCard[Master]) = println("welcome to have this meeting")

    // 逆变
    class PCard[-T](val name: String)
    // 允许MCard[Professional]以及其的逆变子类,即其逆变子类为MCard[Master]
    def enterMeet2(card: PCard[Professional]) = println("welcome to have this meeting")

    println("\n\n协变和逆变")
    // 大师以及大师级以下的名片都可以入会场
    // 协变, mCard是pCard的父类
    val mACard = new MCard[Master]("professional")
    val pACard = new MCard[Professional]("master")
    enterMeet(mACard)
    enterMeet(pACard)

    // 只要专家级别的名片就可以入会场,如果大师级别的过来了,当然也可以了
    // 协变之后, mBCard是pBCard的父类
    val mBCard = new PCard[Master]("professional")
    val pBCard = new PCard[Professional]("master")
    enterMeet2(mBCard)
    enterMeet2(pBCard)

    /**
      * Existential Type
      * 我们想对一些对象做一些操作,但是不关心对象内部的具体类型。
      * 或者说我们只向知道对象可以进行哪些操作,但是不关心它是什么以及可以进行哪些其他操作。
      * 比如我们想取List的第一个元素或者List的长度,但是不关心List是什么类型。
      * 因此我们需要知道该对象可以进行获取长度的操作,但是不关心其元素是什么类型。
      *
      * 即Existential type做到了隐藏我们不关心的对象结构, 同时暴露我们想要进行的操作，恰如其分地对外暴露协议。
      *
      * Array[_]                     : 任意类型的Array
      * Array[T] forSome { type T; } : 任意类型的Array
      * Array[T forSome { type T; }] : Array[Any]
      *
      * Map[Class[T forSome {type T}], String]  : Map[Class[Any],String]
      * Map[Class[T] forSome {type T}, String]  : key为任意类型的Class
      * Map[Class[T], String] forSome {type T}
      */
    println("\nExistential Type")
    def printFirst(x : Array[T] forSome {type T}) = println(x(0))
    // 等价于
    // def printFirst[T](x : Array[T]) = println(x(0))
    printFirst(Array(1, 2, 3))
    def getArray: Array[_] = {
      Array(1, 2, 3)
    }
  }
}
