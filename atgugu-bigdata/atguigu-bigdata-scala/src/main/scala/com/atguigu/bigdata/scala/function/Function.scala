package com.atguigu.bigdata.scala.function

object Function {
  def main(args: Array[String]): Unit = {
    println("res=" + getRes(6, 10, '-'))
  }

  //定义函数
  def getRes(n1: Int, n2: Int, oper: Char) = {
    if (oper == '+') {
      n1 + n2
    } else if (oper == '-') {
      n1 - n2
    } else {
      null
    }


  }

}
