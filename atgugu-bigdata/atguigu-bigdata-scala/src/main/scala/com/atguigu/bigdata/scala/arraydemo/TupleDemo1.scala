package com.atguigu.bigdata.scala.arraydemo

object TupleDemo1 {
  def main(args: Array[String]): Unit = {
    //创建元组
    //简单说明: 为了高效的操作元组 ， 编译器根据元素的个数不同，对应不同的元组类型
    var tuple = (1,2,3,4,"five")
    println(tuple)

    println("=========访问元组==========")

    println(tuple._1)//访问第一个元素，从1开始
    println(tuple.productElement(0))//访问元组的第一个元素
    println("=========遍历元组==========")
    //元组的遍历需要使用到迭代器
    for(i<-tuple.productIterator) {
      println(i)
    }
  }
}
