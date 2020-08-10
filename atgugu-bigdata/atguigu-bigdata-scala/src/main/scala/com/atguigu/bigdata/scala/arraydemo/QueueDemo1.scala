package com.atguigu.bigdata.scala.arraydemo

import scala.collection.mutable

object QueueDemo1 {
  def main(args: Array[String]): Unit = {
    //创建队列
    val q1 =  new mutable.Queue[Int]
    println(q1)

    q1+=9
    println("q1="+q1)
    q1 ++= List(1,2,3)
    println("q1="+q1)
    val queueElement: Int = q1.dequeue()
    println("queueElement=" + queueElement + " q1="+q1)

    q1.enqueue(8,9)
    println("q1="+q1)

    println("head="+q1.head+" last="+q1.last)
    //取出队尾的数据 ,即：返回除了第一个以外剩余的元素，可以级联使用
    println(q1.tail)
  }

}
