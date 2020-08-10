package com.atguigu.bigdata.scala.construct

object ConDemo2 {
  def main(args: Array[String]): Unit = {
    val A = new AA("faker")

  }
}

class BB {
  println("bb")
}

class AA extends BB {
  println("aa")

  def this(name: String) {
    this
    println("A this(name:String)")
  }
}
