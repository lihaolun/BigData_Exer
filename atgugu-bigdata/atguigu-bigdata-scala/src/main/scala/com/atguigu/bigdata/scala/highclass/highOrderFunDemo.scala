package com.atguigu.bigdata.scala.highclass

object HighOrderFunDemo {
  def main(args: Array[String]): Unit = {
    //1. minusxy是高阶函数,因为它返回匿名函数
    //2. 返回的匿名函数 (y: Int) => x*y
    //3. 返回的匿名函数可以使用变量接收
    def minusxy(x:Int)={
      (y:Int)=>x*y //匿名函数
    }

    val f1 = minusxy(3)
    println("f1的类型="+f1)
    println(f1(10))
    println(f1(20))
    println(f1(30))
    println(minusxy(3)(10))

    def test(f:Double=>Double,f2:Double=>Int,n1:Double)={
      f(f2(n1))
    }

    def sum(d:Double):Double={
      d*2
    }

    def trans(d:Double):Int = {
      d.toInt
    }

    val res: Double = test(sum,trans,5.0)
    println("res="+res)
  }
}
