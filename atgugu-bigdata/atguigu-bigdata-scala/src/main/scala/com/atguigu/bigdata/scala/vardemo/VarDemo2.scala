package com.atguigu.bigdata.scala.vardemo

object VarDemo2 {
  def main(args: Array[String]): Unit = {
    var num = 10
    println(num.isInstanceOf[Int])

    val dog = new Dog
    dog.age=10
    dog.name="小张"
    println(dog.toString)
  }
}

class Dog{
  var age:Int = 0
  var name:String = ""

  override def toString = s"Dog($age, $name)"
}
