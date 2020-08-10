package com.atguigu.bigdata.scala.whilebreak

import scala.util.control.Breaks._

object WhileBreak {
  def main(args: Array[String]): Unit = {
    var n = 1
    breakable {
      while (n <= 20) {
        n += 1
        println("n=" + n)
        if (n == 18) {
          break()
        }
      }
    }
    println("ok")
  }
}
