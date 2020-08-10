package com.atguigu.bigdata.scala.highfunction

import scala.collection.mutable.ArrayBuffer

object exercise1 {
  //val sentence = "AAAAAAAAAABBBBBBBBCCCCCDDDDDDD"将sentence 中各个字符，
  // 通过foldLeft存放到 一个ArrayBuffer中，
  // 目的：理解flodLeft的用法. ArrayBufer('A','A','A'..)
  def main(args: Array[String]): Unit = {
    val sentence = "AAAAAAAAAABBBBBBBBCCCCCDDDDDDD"
    println(sentence.foldLeft(ArrayBuffer[Char]())(bufferadd))
  }

  def bufferadd(arr:ArrayBuffer[Char],a:Char): ArrayBuffer[Char] ={
      arr.append(a)
    arr
  }

}
