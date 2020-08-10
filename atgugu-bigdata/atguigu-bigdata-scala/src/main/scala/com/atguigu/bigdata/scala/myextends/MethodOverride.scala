package com.atguigu.bigdata.scala.myextends

object MethodOverride {
  def main(args: Array[String]): Unit = {
    val emp = new Emp100
    emp.printName()
  }
}

//Person类
class Person100 {
  var name: String = "tom"

  def printName() { //输出名字
    println("Person printName() " + name)
  }

  def sayHi(): Unit = {
    println("sayHi...")
  }
}

//继承Person
class Emp100 extends Person100{
  //重写需显示使用
  override def printName(): Unit = {
    super.printName()
    sayHi()
  }
}