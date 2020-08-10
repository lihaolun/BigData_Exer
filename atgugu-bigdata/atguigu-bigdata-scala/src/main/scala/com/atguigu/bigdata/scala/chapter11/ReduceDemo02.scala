package com.atguigu.bigdata.scala.chapter11

object ReduceDemo02 {
  def main(args: Array[String]): Unit = {
    val list = List(1, 2, 3, 4, 5, 6)

    def minus(num1: Int, num2: Int): Int = {
      num1 - num2
    }

    println(list.reduceLeft(minus))

    println(list.reduceRight(minus))

    println("最小值是" + list.reduce(min))
  }

  def min(num1: Int, num2: Int): Int = {
    if (num1 < num2) {
      num1
    } else {
      num2
    }
  }
}
