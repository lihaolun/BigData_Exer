package com.atguigu.bigdata.scala.test

import util.control.Breaks._

//100以内的数求和，求出当和第一次大于20的当前数
object exercise4 {
  def main(args: Array[String]): Unit = {
    var sum = 0
    breakable {
      for (i <- 1 to 100) {
        sum += i
        if (sum > 20) {
          println("和第一次大于20的当前数为:" + i)
          break()
        }
      }
    }

    //循环守卫实现中断
    var loop = true
    var sum2 = 0
    for (i <- 1 to 100 if loop == true) {
      sum2 += i
      if (sum2 > 20) {
        println("和第一次大于20的当前数为:" + i)
        loop = false
      }
      println("i=" + i)
    }
  }
}
