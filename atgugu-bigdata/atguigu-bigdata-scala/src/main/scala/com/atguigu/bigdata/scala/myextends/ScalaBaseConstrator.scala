package com.atguigu.bigdata.scala.myextends

object ScalaBaseConstrator {
  def main(args: Array[String]): Unit = {
    val emp = new Emp700("faker")
    println("-----------------------------")
    val emp2 = new Emp700("faker",23)
  }
}

//父类Person
class Person700(pName: String) {
  var name = pName
  println("Person...")

  def this() {
    this("默认的名字")
    println("默认的名字")

  }
}

//子类Emp继承Person
class Emp700(eName: String, eAge: Int) extends Person700(eName) {

  println("Emp ....")

  //辅助构造器
  def this(name: String) {

    this(name, 100) // 必须调用主构造器
    this.name = name
    println("Emp 辅助构造器~")
  }

  def showInfo(): Unit = {
    println("雇员的名字 ", name)
  }
}