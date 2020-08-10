package com.atguigu.bigdata.scala.demo

object printDemo {

  def main(args: Array[String]): Unit = {
    var str1: String = "hello"
    var str2: String = "world!"
    print(str1 + str2)
    var name: String = "faker"
    var age: Int = 10
    var sal: Float = 23.8f
    var height: Double = 140
    //格式化输出
    printf("名字=%s 年龄是%d 薪水%.2f 高%.3f", name, age, sal, height)
    println("\n个人信息如下: \n 名字$name \n 年龄$age \n薪水${sal*10000}")
  }

}
