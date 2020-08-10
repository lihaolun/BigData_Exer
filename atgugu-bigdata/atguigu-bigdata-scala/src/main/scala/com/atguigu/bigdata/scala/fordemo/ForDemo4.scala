package com.atguigu.bigdata.scala.fordemo

object ForDemo4 {
  def main(args: Array[String]): Unit = {
    println(sayOk("mary"))
  }
  def sayOk(name:String = "Jack"):String ={
    return name + " ok";
  }
}
