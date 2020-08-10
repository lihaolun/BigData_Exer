package com.atguigu.bigdata.scala.highclass

object CurrieFunDemo {
  def main(args: Array[String]): Unit = {
    //编写一个函数，接收两个整数，可以返回两个数的乘积
    //常规方式
    def product1(a:Int,b:Int):Int= a*b
    println(product1(3,5))
    //闭包方式
    def product2(a:Int)=(b:Int)=> a*b
    println(product2(3)(5))
    //柯里化
    def product3(a:Int)(b:Int)=a*b
    println(product3(3)(5))
  }





}
