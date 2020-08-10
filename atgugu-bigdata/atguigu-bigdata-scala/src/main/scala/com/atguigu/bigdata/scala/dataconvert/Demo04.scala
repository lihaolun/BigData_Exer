package com.atguigu.bigdata.scala.dataconvert

object Demo04 {
  def main(args: Array[String]): Unit = {
    println(sayHello)
    var num1 = 1.2
    var num2 = 1.3f
    num2 = num1.toFloat
  }
  //用Nothing做返回值，就是明确说明该方法没有正常返回值
  def sayHello:Nothing = {
    throw new Exception("抛出异常")
  }
}
