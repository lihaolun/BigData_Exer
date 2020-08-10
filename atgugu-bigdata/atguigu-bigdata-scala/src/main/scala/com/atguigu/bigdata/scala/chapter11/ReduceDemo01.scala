package com.atguigu.bigdata.scala.chapter11

object ReduceDemo01 {
  /*
   使用化简的方式来计算list集合的和
    */
  def main(args: Array[String]): Unit = {
    val list = List(1, 2, 3, 4, 5, 6)
    val res = list.reduce(sum)
    println(res)
  }

  def sum(n1: Int, n2: Int): Int = {
    println("sum被调用")
    n1 + n2
  }
}
