package com.atguigu.bigdata.scala.fordemo

object ForDemo1 {
  def main(args: Array[String]): Unit = {
    val start = 1
    val end = 10
    for (i <- start to end) {
      println("Hello Scala" + i)
    }
   var list = List("hello",10,30,"tom")
    for (elem <- list) {
      println(elem)
    }

  }
}
