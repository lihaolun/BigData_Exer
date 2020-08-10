package com.atguigu.bigdata.scala.arraydemo

object ListDemo2 {
  def main(args: Array[String]): Unit = {
    val list1 = List(1, 2, 3, "abc")
    //会报错，只有集合和集合之间可以用:::
   /* val list5 = 4 :: 5 :: 6 ::: list1 ::: Nil
    println(list5)*/
  }
}
