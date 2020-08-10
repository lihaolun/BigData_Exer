package com.atguigu.bigdata.scala.dataconvert

object Demo03 {
  def main(args: Array[String]): Unit = {
    var char1: Char = 97
    println("char1=" + char1)
    //char可以直接当做数字进行运行
    var char2: Char = 'a'
    var num = 10 + char2
    println("num=" + num)
  }
}
