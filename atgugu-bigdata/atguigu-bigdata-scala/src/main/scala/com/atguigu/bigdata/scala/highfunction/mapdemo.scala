package com.atguigu.bigdata.scala.highfunction

object mapDemo {
  def main(args: Array[String]): Unit = {
    val list = List(1,2,3,4)
    val list2 = list.map(multiple)
    println("list2="+list2)
  }

  def multiple(a:Int):Int = {
    a*2
  }

}
