package com.atguigu.bigdata.scala.arraydemo

import scala.collection.mutable.ListBuffer

object ArrayDemo2 {
  def main(args: Array[String]): Unit = {
    //说明
    //1. 使用的是 object Array 的apply
    //2. 直接初始化数组，这时因为你给了 整数和 "", 这个数组的泛型就Any
    //3. 遍历方式一样

    var arr2 = Array(1, 2, "three")
    arr2(1) = 4
    for (elem <- arr2) {
      println(elem)
    }

    //可以用传统方式遍历，使用下标的方式遍历
    for (index<-0 until arr2.length){
      printf("arr2[%d]=%s",index,arr2(index)+"\t")
    }

  }
}
