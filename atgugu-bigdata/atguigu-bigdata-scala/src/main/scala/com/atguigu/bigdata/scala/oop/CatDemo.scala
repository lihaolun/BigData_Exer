package com.atguigu.bigdata.scala.oop

object CatDemo {
  var cat: Cat = new Cat()
  cat.name = "tom"
  cat.age = 3
  cat.color = "黑色"

}

class Cat {
  var name: String = ""
  var age: Int = _
  var color: String = ""
}
