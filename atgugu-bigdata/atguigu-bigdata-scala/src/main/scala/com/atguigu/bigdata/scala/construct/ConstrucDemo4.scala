package com.atguigu.bigdata.scala.construct

object ConstrucDemo2 {
  def main(args: Array[String]): Unit = {
    var worker = new Worker("faker")
    println(worker.name)
  }
  //1.主构造器是Worker(inName:String),那么 inName 就是一个局部变量
  class Worker(inName:String){
    var name = inName
  }
  //2.主构造器是Worker(val inName:String),那么 inName 就是一个private的只读属性
  class Worker2(val inName:String){
    var name = inName
  }
  //2.主构造器是Worker(val inName:String),那么 inName 就是一个private的可读写属性
  class Worker3(var inName:String){
    var name = inName
  }
}
