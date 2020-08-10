package com.atguigu.bigdata.scala.construct

object ConDemo01 {
  def main(args: Array[String]): Unit = {

    /*implicit def f1(d:Double): Int = { //底层 生成 f1$1
      d.toInt
    }

    val num: Int = 3.5 // 底层编译 f1$1(3.5) //idea ___
    println("num =" + num)*/
    var arr02 = Array(1, 3, "xx")
    arr02(1) = "xx"
    for (i <- arr02) {
      println(i)
    }

    for (index <- 0 until  arr02.length) {
      printf("arr02[%d]=%s", index , arr02(index) + "\t")
    }

  }
}
class Person(inName:String,inAge:Int){
  var name: String = inName
  var age: Int = inAge

  def this(name:String){
    this("jack",10)

    this.name = name //重新赋值
  }
}

//父类(基类)
class Base {
  var n1: Int = 1 //public n1() , public n1_$eq()
  protected var n2: Int = 2
  private var n3: Int = 3 // private n3() , private n3_$eq()

  def test100(): Unit = { // 默认 public test100()
    println(n1+n2)
  }
  println("A")

  protected def test200(): Unit = { // ？
    println(n1+n2)
  }

  private def test300(): Unit = { //private
    println(n1+n2)
  }
}

//Sub 继承 Base
class Sub extends Base {
  def this(name:String){
    this

  }

  def sayOk(): Unit = {
    this.n1 = 20 //这里访问本质this.n1_$eq()
    this.n2 = 40

    println("范围" + this.n1 + this.n2)

    test100() //
    test200() //

  }
}