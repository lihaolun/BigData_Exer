package com.atguigu.bigdata.scala.arraydemo

object ListDemo {
  def main(args: Array[String]): Unit = {
    //说明
    //1. 在默认情况下 List 是scala.collection.immutable.List,即不可变
    //2. 在scala中,List就是不可变的，如需要使用可变的List,则使用ListBuffer
    //3. List 在 package object scala 做了 val List = scala.collection.immutable.List
    //4. val Nil = scala.collection.immutable.Nil // List()

    val list1 = List(1, 2, 3, 4) //创建列表直接给元素

    println(list1)
    //空集合
    val listN = Nil
    //访问list的元素
    println(listN)
    println("list(0)=" + list1(0))

    println("list追加元素后的效果")
    //通过 :+ 和 +: 给list追加元素(本身的集合并没有变化)
    val list = List(1, 2, 3, "six")
    // :+运算符表示在列表的最后增加数据
    val list2 = list :+ 7
    println(list)
    println(list2)
    val list3 = 10 +: list
    println(list3)
    //::符号的使用
    //在前面追加
    val list4 = List(1, 2, 3, "faker")
    val list5 = 4 :: 5 :: 6 :: list4 :: Nil
    println("list5=" + list5)

    val list6 = List(1, 2, 3, "bang")
    val list7 = 4 :: 5 :: 6 :: list4 ::: Nil
    println("list7=" + list7)
  }
}
