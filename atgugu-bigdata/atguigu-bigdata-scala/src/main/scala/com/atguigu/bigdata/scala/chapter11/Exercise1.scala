package com.atguigu.bigdata.scala.chapter11

object Exercise1 {
  def main(args: Array[String]): Unit = {
    val names = List("Faker", "Bang", "theshy")
    val names2 = names.map(toUpper)
    println("names=" + names2)
  }

  def toUpper(str: String): String = {
    str.toUpperCase
  }
}
