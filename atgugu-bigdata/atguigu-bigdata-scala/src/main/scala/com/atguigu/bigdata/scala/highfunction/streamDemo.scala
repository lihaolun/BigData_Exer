package com.atguigu.bigdata.scala.highfunction

object streamDemo {
  def numsForm(n:Int):Stream[Int]= n #::numsForm(n+1)

  def main(args: Array[String]): Unit = {
    val stream: Stream[Int] = numsForm(1)
    println(stream)
    println("head="+stream.head)
    println("head="+stream.tail)
    println(stream)

  }
}
