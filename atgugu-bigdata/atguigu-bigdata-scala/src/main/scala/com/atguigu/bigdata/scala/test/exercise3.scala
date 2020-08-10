package com.atguigu.bigdata.scala.test

//打印出九九乘法表
object exercise3 {
  def main(args: Array[String]): Unit = {
    val num = 9
    for (i <- 1 to num) {
      for (j <- 1 to i) {
        printf("%d * %d = %d\t", j, i, i * j)
      }
      println()
    }
  }
}
