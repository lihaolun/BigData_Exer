package com.atguigu.bigdata.scala.test


import scala.beans.BeanProperty


object exercise7 {
  def main(args: Array[String]): Unit = {
    var time = new Time(18, 30)
    println(time.before(new Time(19, 1)))
  }
}

//1.编写一个Time类，加入只读属性hours和minutes，和一个检查某一时刻是否早于另一时刻的方法before(other:Time):Boolean。
// Time对象应该以new Time(hrs,min)方式构建。
class Time(hrs: Int, min: Int) {
  val hours: Int = hrs
  val minutes: Int = min

  def before(other: Time): Boolean = {
    if (this.hours < other.hours) {
      true
    } else if (this.hours > other.hours) {
      false
    } else {
      if (this.minutes < other.minutes) {
        true
      } else {
        false
      }
    }
  }

}