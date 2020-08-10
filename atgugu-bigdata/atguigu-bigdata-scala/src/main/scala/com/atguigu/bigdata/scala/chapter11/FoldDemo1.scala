package com.atguigu.bigdata.scala.chapter11

object FoldDemo1 {
  def main(args: Array[String]): Unit = {
    val list = List(1, 2, 3, 4)
    def minus( num1 : Int, num2 : Int ): Int = {
      num1 - num2
    }

    println(list.foldLeft(5)(minus))
    println(list.foldRight(5)(minus))

  }

}
