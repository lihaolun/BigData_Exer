package com.atguigu.bigdata.scala.arraydemo

import scala.collection.mutable

object MapDemo {
  def main(args: Array[String]): Unit = {
    val map1 = mutable.Map("Alice" -> 10, "Bob" -> 20, "Kotlin" -> "北京")
    println(map1)

    val map3 = new scala.collection.mutable.HashMap[String, Int]
    println(map3)
    val map4 = mutable.Map(("Alice" , 10), ("Bob" , 20), ("Kotlin" , "北京"))
    println("map4=" + map4)

    map4("yan") = "nvshen"
    map4 -= ("Alice","Bob")
    println("map4=" + map4)

    for(a <- map4 )
      println("item="+a)

    val map6 = mutable.Map(("A", 1), ("B", "北京"), ("C", 3))
    println("----(k, v) <- map6--------")
    for ((k, v) <- map6) println(k + " is mapped to " + v)

    println("----v <- map6.keys--------")
    for (v <- map6.keys) println(v)
    println("----v <- map6.values--------")
    for (v <- map6.values) println(v)

    //这样取出方式 v 类型是 Tuple2
    println("----v <- map6--------")
    for (v <- map6) println(v + " key =" + v._1 + " val=" + v._2) //v是Tuple?

  }

}
