package com.atguigu.bigdata.scala.chapter12

object MatchDemo7 {
  def main(args: Array[String]): Unit = {
    val (x,y,z) = (1,2,3)
    println("x="+x+ " y="+y+" z="+z)
    val (a,b) = BigInt(10) /% 3
    println("a="+a+" b="+b)

    val array = Array(1,2,3,4,5)
    val Array(c,d,_*) = array
    println("c="+c,"d="+d)
  }
}
