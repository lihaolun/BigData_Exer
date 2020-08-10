package com.atguigu.bigdata.scala.traitdemo

object TraitDemo {
  def main(args: Array[String]): Unit = {

  }
}
trait Trait01{
  //定义规范
  def getConnect()
}

class A{}
class B extends A{}
class C extends A with Trait01{
  override def getConnect(): Unit = {
    println("获得mysql数据库连接")
  }
}
