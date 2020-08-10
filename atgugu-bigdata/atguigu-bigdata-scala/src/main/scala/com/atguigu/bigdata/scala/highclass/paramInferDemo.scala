package com.atguigu.bigdata.scala.highclass

object ParamInferDemo {
  def main(args: Array[String]): Unit = {
    val list = List(1,2,3,4)
    println(list.map((x:Int)=>x+1))
    println(list.map((x)=>x+1))
    println(list.map(x=>x+1))
    println(list.map(_+1))
    println(list.reduce(_+_))

    println(list.reduce(f1))
    println(list.reduce((a:Int,b:Int)=>a+b))
    println(list.reduce((a,b)=>a+b))
    println(list.reduce(_+_))

    val res = list.reduce(_+_)

  }
  def f1(a:Int,b:Int):Int ={
    a+b
  }
}
