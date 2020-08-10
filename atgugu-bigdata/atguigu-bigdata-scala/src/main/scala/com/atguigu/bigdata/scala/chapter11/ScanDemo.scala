package com.atguigu.bigdata.scala.chapter11

object ScanDemo {
  def main(args: Array[String]): Unit = {
    def minus(num1: Int, num2: Int): Int = {
      num1 - num2
    }

    val e = (1 to 5).scanLeft(6)(minus)
    println("e=" + e) //e=Vector(6, 5, 3, 0, -4, -9)
  }
}
