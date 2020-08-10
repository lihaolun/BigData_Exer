package com.atguigu.bigdata.scala.highfunction

import scala.collection.mutable

object exercise2 {
  def main(args: Array[String]): Unit = {
    val sentence = "AAAAAAAAAABBBBBBBBCCCCCDDDDDDD"
    val map = mutable.Map[Char,Int]()
    sentence.foldLeft(map)(mapadd)
    println(map)
  }

  def mapadd(map:mutable.Map[Char,Int],char:Char):mutable.Map[Char,Int]={
    map+=(char -> (map.getOrElse(char,0)+1))
  }
}
