package com.atguigu.bigdata.scala.construct

object ConDemo3 {
  def main(args: Array[String]): Unit = {
      //创建
      val array: Array[Array[Int]] = Array.ofDim[Int](3,4)
      //遍历
    array(1)(2) = 6

    for(item <- array){  //取出二维数组的各个元素（一维数组）
      for(item2 <- item){
        print(item2+"\t")
      }
      println()
    }
    //使用传统的下标方式来遍历
    println("======================")
    for(i<-0 until  array.length){
      for(j<-0 until array(i).length){
        printf("array[%d][%d]=%d\t",i,j,array(i)(j))
      }
      println()
    }
  }

}
