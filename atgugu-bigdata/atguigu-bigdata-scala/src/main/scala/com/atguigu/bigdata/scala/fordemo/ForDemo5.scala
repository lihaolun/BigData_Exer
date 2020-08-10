package com.atguigu.bigdata.scala.fordemo

object ForDemo5 {
  def main(args: Array[String]): Unit = {
    //控制步长
    //方法一
    for (i <- Range(2, 20, 4)) {
      println("i=" + i)
    }
    println("--------------------------------" )
    //方法二
    for (i <- 1 to 30 if i % 3 == 0) {
      println("i=" + i)
    }
  }

}
