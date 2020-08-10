package com.atguigu.bigdata.scala.dataconvert

object Demo01 {
  def main(args: Array[String]): Unit = {
    val a1 = 10
    val a2 = 20
    val a3 = a1 + a2
    print(a3)

    val char : Char = 1

    var a = 30;
    var b = 20;
    a = a + b - a
    b = a + b - b
    print("a=" + a,"b=" + b)
  }
}
