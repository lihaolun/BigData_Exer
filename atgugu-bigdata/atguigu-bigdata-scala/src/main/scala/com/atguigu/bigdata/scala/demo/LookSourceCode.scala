package com.atguigu.bigdata.scala.demo

object LookSourceCode {
  def main(args: Array[String]): Unit = {
    var arr = new Array[Int](10)
    for (elem <- arr) {
      println("elem="+ elem)
    }
  }
}
