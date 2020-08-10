package com.atguigu.bigdata.scala.ifelse

import scala.math._

object IfelseDemo1 {
  def main(args: Array[String]): Unit = {
    val a = 3
    val b = 100
    val c = 6
    var cond = b * b - 4 * a * c
    var x1 = 0.0
    var x2 = 0.0
    if (cond > 0) {
      x1 = (-b + sqrt(cond)) / 2 * a
      x2 = (-b - sqrt(cond)) / 2 * a
      println("有两个解 x1=" + x1.formatted("%.2f") + "\tx2=" + x2.formatted("%.2f"))
    } else if (cond == 0) {
      x1 = (-b + sqrt(cond)) / 2 * a
      println("有一个解 x1=" + x1.formatted("%.2f"))
    }else{
      println("无解")
    }
  }

}
