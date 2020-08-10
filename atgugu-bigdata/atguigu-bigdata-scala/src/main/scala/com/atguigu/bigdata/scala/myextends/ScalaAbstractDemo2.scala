package com.atguigu.bigdata.scala.myextends

object ScalaAbstractDemo2 {
  def main(args: Array[String]): Unit = {
    val monster = new Monster {
      override var name: String = _

      override def cry(): Unit = {
        println("---cry")
      }
    }
    monster.cry()
  }
}

abstract class Monster {
  var name: String

  def cry()
}