package com.atguigu.bigdata.scala.arraydemo

import java.util

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object ArrayBuffer2JavaList {
  def main(args: Array[String]): Unit = {
    //scala集合和Java集合相互转换
    val arr = ArrayBuffer("1", "2", "3")
    import scala.collection.JavaConversions.bufferAsJavaList
    val processBuilder = new ProcessBuilder(arr)
    val list: util.List[String] = processBuilder.command()
    println(list)
    import scala.collection.JavaConversions.asScalaBuffer
    //java的list转成scala的ArrayBuffer
    //asScalaBuffer是一个隐式函数
    val scalaArr:mutable.Buffer[String] = list

    scalaArr.append("20k")
    scalaArr.append("14")
    println(scalaArr)
  }
}
