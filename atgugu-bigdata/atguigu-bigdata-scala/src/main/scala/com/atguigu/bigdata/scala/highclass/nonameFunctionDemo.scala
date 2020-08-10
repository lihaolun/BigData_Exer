package com.atguigu.bigdata.scala.highclass

object NonameFunctionDemo {
  def main(args: Array[String]): Unit = {
    val triple = (x:Double)=>{
      println("x="+x)
      3*x
    }
    println("triple=" + triple(3))

    val fun = (a:Int,b:Int) =>{
      a+b
    }
    println("fun="+ fun(3,4))
    println(fun)

  }

}
