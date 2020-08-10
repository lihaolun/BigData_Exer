package com.atguigu.bigdata.scala.ifelse

object IfelseDemo4 {
  def main(args: Array[String]): Unit = {
    val year = 2018
    if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
      println(s"${year}" + "是闰年")
    } else {
      println(s"${year}" + "不是闰年")
    }
  }

  var sumVal = 90
  var result = if(sumVal>20){
    "结果大于20"
  }
  println("res="+result)
}
