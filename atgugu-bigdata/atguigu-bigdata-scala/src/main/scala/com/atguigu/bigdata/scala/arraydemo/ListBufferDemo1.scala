package com.atguigu.bigdata.scala.arraydemo

import scala.collection.mutable.ListBuffer

object ListBufferDemo1 {
  def main(args: Array[String]): Unit = {
      val listBuffer: ListBuffer[Int] = ListBuffer[Int](1,2,3)

      println("list(2)="+listBuffer(2))

    for(i <- listBuffer){
      println("item="+i)
    }
    //动态的增加元素，lst1就会变化, 增加一个一个的元素
    val list1 = new ListBuffer[Int]
    list1+=4
    list1.append(5)
    println(list1)
    listBuffer++= list1
    println(listBuffer)

    val lst2 = listBuffer ++ list1 // lst2(1, 2, 3,4,5,4,5)
    println("lst2=" + lst2)
    val lst3 = listBuffer :+5
    println("lst3=" + lst3)


    val list4 = ListBuffer[String]("hehe","haha","aa")
    for(i<-list4){
      println(i)
    }

    list4+="ee"
    val str: String = list4.mkString("|")
    println(str)
  }
}
