package com.atguigu.bigdata.scala.airth

object Demo02 {
  def main(args: Array[String]): Unit = {
    var num = 2
    num <<= 2
    num >>= 3
    println("num=" + num)


    val res = {
      if (num > 1) "hello 1" else "hello 2"
    }
    println("res=" + res)
  }
}
