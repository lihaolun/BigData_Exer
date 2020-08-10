package com.atguigu.bigdata.scala.myextends

object Extends01 {
  def main(args: Array[String]): Unit = {
    val student = new Student
    student.name = "faker"
    student.study()
    student.showInfo()
  }
}

class Person {
  var name: String = _
  var age: Int = _

  def showInfo(): Unit = {
    println("名字是"+name +"\t年龄是"+age)
  }
}

class Student extends Person {
  def study(): Unit = {
    println(this.name + "学习scala中")
  }
}

