package com.atguigu.bigdata.scala.chapter11

object FoldDemo2 {
  def main(args: Array[String]): Unit = {
    var list = List(1, 3)

    def minus(num1: Int, num2: Int): Int = {
      num1 - num2
    }

    var b = list.foldLeft(1)(minus)
    var a = (1 /: list) (minus) //=等价=> list.foldLeft(1)(minus)
    println(a == b)

    var c = (list :\ 10) (minus)
    var d = list.foldRight(10)(minus)
    println("c=" + c + " d=" + d)
  }
}
