package com.atguigu.bigdata.scala.arraydemo
import scala.collection.mutable.ArrayBuffer
object ArrayBufferDemo {
  def main(args: Array[String]): Unit = {
    val arr2 = ArrayBuffer[Any](1, 2, 3)
    //访问
    println("arr2(1)=" + arr2(1))

    //遍历
    for(i<- arr2){
      println(i)
    }
    println(arr2.hashCode())

    //修改
    //使用append 追加数据 ,append支持可变参数
    //可以理解成java的数组的扩容
    arr2.append("4",5)
    println(arr2.hashCode())
    println("===============")
    arr2(0) = 0
    println("arr2(0)=" + arr2(0))

    println("===============")
    //删除
    arr2.remove(0)
    for(i<- arr2){
      println(i)
    }
    println(arr2.length)
  }
}
