package com.atguigu.bigdata.scala.arraydemo

object CollectionDemo1 {
  def main(args: Array[String]): Unit = {
    val str = "hello" //字符串在scala就是Char的集合 IndexedSeq
    for(item<- str){
      println(item)
    }
    println(str(2))
  }
}
