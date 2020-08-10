package com.atguigu.bigdata.scala.arraydemo

import scala.collection.mutable.ArrayBuffer

object Array2ArrayBuffer {
  def main(args: Array[String]): Unit = {
    val arr2 = ArrayBuffer[Int]()
    arr2.append(1,2,3)
    println(arr2)
    //说明
    //1. arr2.toArray 调用 arr2的方法 toArray
    //2. 将 ArrayBuffer ---> Array
    //3. arr2本身没有任何变化
    val newArr = arr2.toArray
    println(newArr)

    //说明
    //1. newArr.toBuffer 是把 Array->ArrayBuffer
    val newArr2 = newArr.toBuffer
    println(newArr2)
  }
}
