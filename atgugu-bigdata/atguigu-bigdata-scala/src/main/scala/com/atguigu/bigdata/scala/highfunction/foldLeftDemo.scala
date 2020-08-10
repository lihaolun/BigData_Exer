package com.atguigu.bigdata.scala.highfunction

object foldDemo {
  def main(args: Array[String]): Unit = {
    val list = List(1, 2, 3, 4)
    println(list.foldLeft(5)(minus)) //函数柯里化
    println(list.foldRight(5)(minus))

    println((5/:list)(minus))
    println((list:\5)(minus))


  }



  def minus( num1 : Int, num2 : Int ): Int = {
    num1 - num2
  }

}
