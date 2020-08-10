package com.atguigu.bigdata.scala.highfunction

object zipDemo {
  def main(args: Array[String]): Unit = {
    val list1 = List(1,2,3)
    val list2 = List("a","b","c")
    val tuples: List[(Int, String)] = list1.zip(list2)
    println(tuples)

    for(i<-tuples){
      println(i._1+" "+i._2)
    }
  }
}
