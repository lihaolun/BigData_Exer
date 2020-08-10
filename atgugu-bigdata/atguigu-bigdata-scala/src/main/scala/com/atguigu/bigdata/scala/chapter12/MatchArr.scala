package com.atguigu.bigdata.scala.chapter12

import scala.collection.mutable.ArrayBuffer

object MatchArr {
  def main(args: Array[String]): Unit = {
    //给你一个数组集合，如果该数组时  Array(10,20) , 请使用默认匹配，返回Array(20,10)
    val arrs2 = Array(Array(0), Array(1, 0), Array(0, 1, 0),
      Array(1, 1, 0), Array(1, 1, 0, 1))
    for (elem <- arrs2) {
      val result = elem match {
        case Array(x, y) => ArrayBuffer(y, x)
        case _ => "不处理"
      }
      println("res=" + result)
    }
  }
}
