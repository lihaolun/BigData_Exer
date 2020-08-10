package com.atguigu.bigdata.scala.highclass

object ParamFunctionDemo {
  def main(args: Array[String]): Unit = {
    def plus(x:Int) = 3+x
    val result1 = Array(1,2,3,4).map(plus(_))
    println(result1.mkString(","))
    println(plus _)
  }
}
