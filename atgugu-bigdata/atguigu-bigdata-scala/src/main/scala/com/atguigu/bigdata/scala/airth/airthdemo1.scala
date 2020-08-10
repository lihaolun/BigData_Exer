package com.atguigu.bigdata.scala.airth

object airthdemo1 {
  def main(args: Array[String]): Unit = {
    // /的使用
    var r1: Int = 10 / 3
    println("r1=" + r1)
    var r2: Double = 10 / 3 //3.0
    println("r2=" + r2)
    var r3: Double = 10.0 / 3
    println("r3=" + r3)
    println("r3=" + r3.formatted("%.2f"))

    println("-----------------")

    //%的使用
    //1.%的运算原则：a%b = a - a/b *b
    println(10 % 3) //1
    println(-10 % 3) //-1
    println(-10 % -3) //-1
    println(10 % -3) //1
    println("-----------------")

    //++和--
    var num1 = 1
    num1 += 1
    println(num1)
    num1 -= 1
    println(num1)
  }
}
