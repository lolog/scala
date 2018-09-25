package adj.scala.advance

import java.text.AttributedCharacterIterator

object ScalaXml {
  def main(args: Array[String]): Unit = {
    /**
      * Scala中定义XML
      * Scala对xml有很好的支持,可以直接在Scala代码中定义一个xml文档元素
      * var books = <books><book>shirk</book></books>
      * 此时,doc类型是scala.xml.Elem,也就是一个xml元素
      *
      * Scala还可以直接定义多个同级别的xml元素
      * val books = <book>shirk</book><book>kafka</book>
      * 此时,doc的类型是scala.xml.NodeSeq,也就是一个xml节点序列
      *
      *
      * +++++++++++++++ xml节点类型 +++++++++++++++++++++++
      * Node类是所有xml节点类型的父类型,2个重要的子类型是Text和Elem
      * Elem表示一个xml元素,也就是一个xml节点。scala.xml.Elem类型的label属性,
      * 返回的时标签名; child属性,返回的是子元素。scala.xml.NodeSeq类型,是一个元素序列,
      * 可以用for循环遍历。
      * 可以通过scala.xml.NodeBuffer类型,来手动创建一个节点序列。
      *
      * val booksBuffer = new scala.xml.NodeBuffer
      * booksBuffer += <book>shirk</book>
      * booksBuffer += <book>kafka</book>
      * val books: scala.xml.NodeSeq = booksBuffer
      *
      * +++++++++++++++ xml元素的属性 +++++++++++++++++++++++
      * scala.xml.Elem.attributes属性,可以返回xml元素的属性,是Seq[scala.xml.Node]类型,继续调用
      * text属性,可以拿到属性的值。
      * val book = <book id="1" price="10.0">shirk</book>
      * val bookId = book.attributes("id").text
      *
      * 可以遍历属性
      * for(attr <- book.attributes) print(attr)
      * 还可以调用book.attributes.asAttrMap,获取一个属性Map
      */
    println("xml define")
    val books = <book>shirk</book><book>kafka</book>
    println("    " + books)

    println("\nxml node type")
    val booksBuffer = new scala.xml.NodeBuffer
    booksBuffer += <book>shirk</book>
    booksBuffer += <book>kafka</book>
    val nbooks: scala.xml.NodeSeq = booksBuffer
    for(book <- nbooks) println("    "+book)

    print("\nxml property\n   ")
    val book = <book id="1" price="10.0">shirk</book>
    val bookId = book.attributes("id").text
    val attrMap = book.attributes.asAttrMap

    for(attr <- book.attributes) print(attr)
    println("\n    book id=" + bookId)
    println("    " + attrMap)

    /**
      * xml嵌入scala代码
      */
    println("\nxml text use scala code")
    val tbooks = Array("shirk", "kafka")
    val xmlbooks = <books><book>{tbooks(0)}</book><book>{tbooks(1)}</book>{for(book <- tbooks) yield <book>last-{book}</book>}</books>
    println("    " + xmlbooks)

    println("\nxml attribute use scala code")
    val xmlAttrBooks = <book id="{tbooks(0)}">{tbooks(1)}</book>
    println("    " + xmlAttrBooks)

    /**
      * XML修改元素
      * 默认情况下,scala中的xml表达式是不可改变的,如果要修改xml元素的话,必须卡背一份再修改
      */
    val xmlTBooks = <books><book>shirk</book><book>kafka</book></books>
    // 添加一个子元素
    val xmlTBooksCp = xmlTBooks.copy(child = xmlTBooks.child ++ <book>spark</book>)

    val oneBook = <book id="1">scala</book>

    import scala.xml._
    // 修改一个属性
    val bookCopy = oneBook % Attribute(null, "id", "2", Null)
    println("\nedit xml by copy")
    println("    " + bookCopy)

    // 添加一个属性
    val addbycopy = oneBook % Attribute(null, "id", "5",  Attribute(null, "price", "10.0", Null))
    println("\nadd attribute by copy")
    println("    " + addbycopy)

    /**
      * xml加载外部文档
      */
    println("\nload outer xml")
    import java.io._
    // 使用scala的xml类加载
    val hydra = XML.loadFile("hydra.xml")
    println(hydra)

    // 使用Java的FileInputStream类加载
    val hydra0 = XML.load(new FileInputStream("hydra.xml"))

    // 使用Java的类指定加载编码
    val hydra1 = XML.load(new InputStreamReader(new FileInputStream("hydra.xml"), "UTF-8"))

    // 将内存中的xml对象,写入到xml文档
    XML.save("out/hydra.xml", hydra)
  }
}
