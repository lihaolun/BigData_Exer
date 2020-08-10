package com.atguigu.bigdata.scala.highclass

object PartialDemo2 {
  //使用偏函数解决
  def main(args: Array[String]): Unit = {

    val list=List(1,2,3,4,"abc",3.3)

    val partialFun = new PartialFunction[Any,Int] {
      override def isDefinedAt(x: Any): Boolean = {
        println("x="+x)
        x.isInstanceOf[Int]
      }

      override def apply(v1: Any): Int = {
        println("v1="+v1)
        v1.asInstanceOf[Int]+1
      }
    }
    //使用偏函数
    val list2: List[Int] = list.collect(partialFun)
    println("list2="+list2)

    //偏函数的简写
    def partialFun2: PartialFunction[Any,Int] = {
      //简写成case 语句
      case i:Int => i + 1
      case j:Double => (j * 2).toInt
    }

    val list3 =list.collect(partialFun2)
    println("list3="+list3)

    //第二种简写形势
    val list4: List[Int] = list.collect {
      case i: Int => i + 1
      case j: Double =>
        (j * 2).toInt
    }
    println("list4="+list4)
  }
}
