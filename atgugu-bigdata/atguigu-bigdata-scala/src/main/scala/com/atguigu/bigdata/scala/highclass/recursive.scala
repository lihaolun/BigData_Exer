package com.atguigu.bigdata.scala.highclass

import java.text.SimpleDateFormat
import java.util.Date

object recursive {
  def main(args: Array[String]): Unit = {
    //1-50的和
    val now:Date = new Date()
    val simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val date = simpleDateFormat.format(now)
    println("startDate="+date)

    var res = BigInt(0)
    var num = BigInt(1)
    var maxVal = BigInt(99999999l) //BigInt(99999999l)[测试效率大数]
    while (num <= maxVal) {
      res += num
      num += 1
    }
    println("res=" + res)
    val now2: Date = new Date()
    val date2 = simpleDateFormat.format(now2)
    println("date2=" + date2) //输出时间

  }
}
