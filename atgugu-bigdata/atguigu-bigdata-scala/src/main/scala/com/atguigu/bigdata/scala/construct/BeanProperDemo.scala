package com.atguigu.bigdata.scala.construct

import scala.beans.BeanProperty

object BeanProperDemo {
  def main(args: Array[String]): Unit = {
    val car = new Car
    car.name = "BWM"
    car.setName("奔驰")
    println(car.getName)
  }

  class Car{
    @BeanProperty var name:String = null
  }
}
