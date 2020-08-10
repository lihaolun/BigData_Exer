package com.atguigu.bigdata.scala.dataconvert

object Demo02 {
  def main(args: Array[String]): Unit = {
    val char1:Char = 1
    val num3 = 1
   // val char2:Char = num3 错误,Int不能直接转换成char

    val s1 = "Hello"
   // println(s1.toInt) 错误，字符串转换基本数据类型必须确保它的值可以转换
    val s2 = "12.5"
    println(s2.toInt) //错误，同上
  }
}
