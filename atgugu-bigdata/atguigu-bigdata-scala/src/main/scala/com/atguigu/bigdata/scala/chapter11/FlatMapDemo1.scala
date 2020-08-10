package com.atguigu.bigdata.scala.chapter11

object FlatMapDemo1 {
  def main(args: Array[String]): Unit = {
    //将List集合中的所有元素扁平化操作练习（把所有元素打散）
    val names = List("Faker", "Bang", "theshy")
    val names2 = names.flatMap(toUpper)
    println(names2)
  }

  def toUpper(str: String): String = {
    str.toUpperCase
  }
}
