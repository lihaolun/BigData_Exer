package com.atguigu.bigdata.scala.chapter11

object MapOperateDemo2 {
  def main(args: Array[String]): Unit = {
    /*
    /*
   请将List(3,5,7) 中的所有元素都 * 2 ，
   将其结果放到一个新的集合中返回，即返回一个新的List(6,10,14), 请编写程序实现.

    */
    */
    val list = List(3, 5, 7)
    val list2 = list.map(mult2)
    println("list2=" + list2)

  }

  def mult2(d: Int): Int = {
    d * 2
  }
}

//模拟map实现
class MyList {
  val list1 = List(3, 5, 7, 9)
  //新的集合
  var list2 = List[Int]()

  //map的功能
  def map(f: Int => Int): List[Int] = {
    for (elem <- list1) {
      list2 = list2 :+ elem
    }
    list2
  }
}
