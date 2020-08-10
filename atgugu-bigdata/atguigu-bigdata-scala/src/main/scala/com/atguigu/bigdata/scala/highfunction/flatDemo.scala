package com.atguigu.bigdata.scala.highfunction

object flatDemo {
  def main(args: Array[String]): Unit = {
    val names = List("Alice","Bob","Nice")
    val strings: List[String] = names.filter(startWithA)
    println(strings)
  }

  def startWithA(str:String):Boolean={
    str.startsWith("A")
  }

}
