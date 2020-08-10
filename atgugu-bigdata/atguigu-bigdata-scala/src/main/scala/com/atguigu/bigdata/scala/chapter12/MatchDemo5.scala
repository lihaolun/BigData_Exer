package com.atguigu.bigdata.scala.chapter12

import scala.collection.mutable.ArrayBuffer

object MatchDemo5 {
  def main(args: Array[String]): Unit = {
      //数组
   /* val arrs = Array(Array(0),Array(1,0),Array(0,1,0),Array(1,1,0),Array(1,1,0,1))

    for(i <-arrs){
    val result = i match {
        case Array(0) => "0"
        case Array(x,y) => x+"="+y
        case Array(0,_*) => "以0开头的数组"
        case Array(x,y,z,d) => "长度为4的数组"
        case _=> "什么集合都不是"
      }
      println("resulet="+result)
    }*/
    val arrs2 = Array(Array(0), Array(1, 0), Array(0, 1, 0),
      Array(1, 1, 0), Array(1, 1, 0, 1))
    for(i<-arrs2){
      val result = i match {
        case Array(x,y)=> ArrayBuffer(y,x)
        case _ => "不处理"
      }

        println(result)

    }

    //列表
    for (list <- Array(List(0), List(1, 0), List(88), List(0, 0, 0), List(1, 0, 0))) {
      val result = list match {
        case 0 :: Nil => "0" //
        case x :: y :: Nil => x + " " + y //
        case 0 :: tail => "0 ..." //
        case x :: Nil => x
        case _ => "something else"
      }
      //1. 0
      //2. 1 0
      //3. 0 ...
      //4. something else
      println(result)
    }
    //元组
    for(i<-Array((0, 1), (1, 0), (10, 30), (1, 1), (1, 0, 2))){
     val result =  i match {
        case (0,_) => "0 ..."
        case (x,0)=>x
        case (x,y)=>(y,x)
        case _ => "other"
      }
      println("result="+result)
    }
  }

}
