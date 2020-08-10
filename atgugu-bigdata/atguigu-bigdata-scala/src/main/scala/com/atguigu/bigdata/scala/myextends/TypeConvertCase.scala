package com.atguigu.bigdata.scala.myextends

object TypeConvertCase {
  def main(args: Array[String]): Unit = {
    val stu = new Student400
    val emp = new Emp400
    test(stu)
    test(emp)
  }

  def test(p: Person400): Unit = {
    //使用scala中类型检查和转换
    if(p.isInstanceOf[Student400]){
      p.asInstanceOf[Student400].cry()
      p.asInstanceOf[Student400].printName()
    }else if(p.isInstanceOf[Emp400]){
      p.asInstanceOf[Emp400].showInfo()
    }else{
      println("类型转换失败")
    }
  }
}

class Person400 {
  def printName(): Unit = {
    println("Person400 printName")
  }

  def sayOk(): Unit = {
    println("Person400 sayOk")
  }
}

class Student400 extends Person400 {
  val stuId = 100

  override def printName(): Unit = {
    println("Student400 printName")
  }

  def cry(): Unit = {
    println("学生的id=" + this.stuId)
  }
}

class Emp400 extends Person400 {
  val empId = 80

  override def printName(): Unit = {
    println("Person400 printName")
  }

  def showInfo(): Unit = {
    println("雇员的id=" + empId)
  }
}