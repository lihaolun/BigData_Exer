package com.atguigu.bigdata.scala.method

object MethodTest {
  def main(args: Array[String]): Unit = {

  }
  def rectangle(m: Int, n: Int) = {
    for (i <- 1 to m) {
      for (j <- 1 to n) {
        print("*")
      }
      println()
    }
  }

  def getArea(len: Double, width: Double) = {
    len * width
  }
}
