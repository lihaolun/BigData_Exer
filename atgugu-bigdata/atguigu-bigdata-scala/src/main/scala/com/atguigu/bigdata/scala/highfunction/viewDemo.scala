package com.atguigu.bigdata.scala.highfunction

import scala.collection.{SeqView, immutable}

object viewDemo {
  def main(args: Array[String]): Unit = {
    val value: SeqView[Int, immutable.IndexedSeq[Int]] = (1 to 100).view.filter(eq)
    for(i <- value){
      println("items="+i)
    }
  }
  def eq(a:Int):Boolean={
    println("eq被调用")
    a.toString.reverse.equals(a.toString)
  }

}
