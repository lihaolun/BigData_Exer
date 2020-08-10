package com.atguigu.bigdata.scala.ifelse

import scala.io.StdIn

object IfelseDemo3 {
  def main(args: Array[String]): Unit = {
    println("请输入月份")
    val month = StdIn.readInt();
    println("请输入年龄")
    val age = StdIn.readInt();
    val price = 60;
    if (4 <= month && month <= 10) {
      if (age < 18) {
        println("票价是:" + price / 2)
      } else if (age > 60) {
        println("票价是:" + price / 3)
      } else {
        println("票价是:" + price)
      }
    } else {
      if (age >= 18 && age <= 60) {
        println("票价是:" + price / 3 * 2)
      } else {
        println("票价是:" + price / 3)
      }
    }

  }
}
