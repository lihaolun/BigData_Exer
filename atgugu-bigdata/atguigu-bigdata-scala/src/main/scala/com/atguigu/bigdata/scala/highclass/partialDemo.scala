package com.atguigu.bigdata.scala.highclass

object PartialDemo {
  def main(args: Array[String]): Unit = {
    //方法1
    val list=List(1,2,3,4,"abc")
    println(list.filter(filterOther).map(transType).map(addOne))
    //方法2,有瑕疵
   println(list.map(addOne2))
  }

  def filterOther(a:Any):Boolean={
    a.isInstanceOf[Int]
  }

  def addOne(a:Int): Int ={
      a + 1
  }
  def addOne2(a:Any):Any={
    a match {
      case x:Int => x+1
      case _=>
    }

  }

  def transType(a:Any):Int = {
    a.asInstanceOf[Int]
  }

}


