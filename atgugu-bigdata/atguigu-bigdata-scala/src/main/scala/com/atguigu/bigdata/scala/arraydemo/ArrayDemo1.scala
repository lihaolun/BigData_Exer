package com.atguigu.bigdata.scala.arraydemo

object ArrayDemo1 {
  def main(args: Array[String]): Unit = {
    //定长数组的使用
    //说明
    //1. 创建了一个Array对象,
    //2. [Int] 表示泛型，即该数组中，只能存放Int
    //3. [Any] 表示 该数组可以存放任意类型
    //4. 在没有赋值情况下，各个元素的值 0
    //5.  arr01(3) = 10 表示修改 第4个元素的值
    val arr = new Array[Int](4); //底层 int[] arr = new int[4];

    for (elem <- arr) {
      println(elem)
    }
    println("====================")
    arr(0) = 1
    for (elem <- arr) {
      println(elem)
    }
  }
}
