package com.atguigu.bigdata.scala.ifelse

import scala.io.StdIn

object IfElseDemo2 {
  def main(args: Array[String]): Unit = {
    println("请输入运动员的成绩")
    val  speed = StdIn.readDouble();
    if (speed > 8 ){
      println("请输入性别")
      val gender = StdIn.readChar();
      if (gender == "男"){
        println("进入男子组")
      }else{
        println("进入女子组")
      }
    }else{
      println("被淘汰")
    }
  }
}
