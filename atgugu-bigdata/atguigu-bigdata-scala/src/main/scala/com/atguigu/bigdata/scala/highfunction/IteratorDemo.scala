package com.atguigu.bigdata.scala.highfunction

object IteratorDemo {
  def main(args: Array[String]): Unit = {
    val list1 = List(1,2,3,4)

    val iterator: Iterator[Int] = list1.iterator
    //遍历方式1
    while(iterator.hasNext){
      println(iterator.next())
    }
    println("=================================")

    //遍历方式2
    for(i<-iterator){
      println(i)
    }


  }

}
