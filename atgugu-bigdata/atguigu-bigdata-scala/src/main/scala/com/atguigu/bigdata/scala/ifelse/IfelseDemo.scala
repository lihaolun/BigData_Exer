package com.atguigu.bigdata.scala.ifelse

object IfelseDemo {
  def main(args: Array[String]): Unit = {
    val age = 18;
    val res = if (age >= 18) {
      println("已成年")
      "age>18"
    } else {
      println("未成年")
    }
    println(res)
  }

}
