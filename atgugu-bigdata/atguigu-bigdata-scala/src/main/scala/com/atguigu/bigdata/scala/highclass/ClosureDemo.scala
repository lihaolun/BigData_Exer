package com.atguigu.bigdata.scala.highclass

object ClosureDemo {
  def main(args: Array[String]): Unit = {
    def minusxy(x:Int) = (y:Int) =>x-y
    //f函数就是闭包
    val f = minusxy(20)
    println("f(1)=" + f(1))
    println("f(2)=" + f(2))

    def makeString(suffix:String)={
      (filename:String)=>{
        if(filename.endsWith(suffix)){
          filename
        }else{
          filename+suffix
        }
      }
    }
    val file1 = makeString(".jpg")
    println(file1("a.jpg"))
    println(file1("b"))

  }
}
