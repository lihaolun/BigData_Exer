package com.atguigu.bigdata.scala.myextends

object TypeConvert {
  def main(args: Array[String]): Unit = {
    val a = 6.66
    //类的全类名
    println(classOf[Person200])
    //对象的类
    println(a.getClass.getName)

    var p1 = new Person200
    var emp = new Emp200
    //自动转型
    p1 = emp
    //向下转型（多态）
    var emp2 = p1.asInstanceOf[Emp200]
    emp2.printName()
  }
}

//Person类
class Person200 {
  var name: String = "tom"

  def printName() { //输出名字
    println("Person printName() " + name)
  }

  def sayHi(): Unit = {
    println("sayHi...")
  }
}

//这里我们继承Person
class Emp200 extends Person200 {
  //这里需要显式的使用override
  override def printName() {
    println("Emp printName() " + name)
    //在子类中需要去调用父类的方法,使用super
    super.printName()
    sayHi()
  }

  def sayHello(): Unit = {

  }
}