package com.atguigu.bigdata.scala.arraydemo

import scala.collection.mutable

object SetDemo {
  def main(args: Array[String]): Unit = {
    val set = Set(1, 2, 3) //不可变
    println(set)

    val set2 = mutable.Set(1,2,"hello") //可以变
    println("set2=" + set2)
    //添加
    set2.add(3)
    println("set2=" + set2)
    set2+='5'
    println("set2=" + set2)
    set2.+=(6)
    println("set2=" + set2)
    //删除
    set2.remove(1)
    println("set2=" + set2)
    set2-='5'
    println("set2=" + set2)
    set2.+=(6)
    println("set2=" + set2)

    for(i<- set2){
      println("item="+i)
    }

  }
}
