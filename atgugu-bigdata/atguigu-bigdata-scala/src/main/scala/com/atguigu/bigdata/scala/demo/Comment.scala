package com.atguigu.bigdata.scala.demo

object Comment {
  def main(args: Array[String]): Unit = {
    val i:Int = sum(6,8)
    print(i)
  }

  /**
    * @example
    *          输入 6,8返回14
    * @param n1
    * @param n2
    * @return sum
    */
  def sum(n1: Int, n2: Int): Int = {
    return n1 + n2;
  }

}
