package com.atguigu.bigdata.scala.function

object VarParameters {
  def main(args: Array[String]): Unit = {
    //编写一个函数，求出1到多个int的和
    println(sum(1,3,4,2))
  }

  def sum(n1: Int, args: Int*): Int = {
    var sum = n1
    for (item <- args) {
      sum += item
    }
    sum
  }
}
