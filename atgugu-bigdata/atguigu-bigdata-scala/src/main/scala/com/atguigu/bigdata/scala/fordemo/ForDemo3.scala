package com.atguigu.bigdata.scala.fordemo

object ForDemo3 {
  def main(args: Array[String]): Unit = {
    for (i <- 1 to 3; j <- 1 to 3) {
      println("i=" + i +",j=" + j)
    }
  }
}
