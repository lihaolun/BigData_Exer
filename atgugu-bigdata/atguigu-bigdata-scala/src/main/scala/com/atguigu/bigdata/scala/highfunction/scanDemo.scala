package com.atguigu.bigdata.scala.highfunction

object scanDemo {
  def main(args: Array[String]): Unit = {
      val a = (1 to 5).scanLeft(5)(minus)
      println(a)
  }


  def minus(a:Int,b:Int):Int={
    a - b
  }
}
