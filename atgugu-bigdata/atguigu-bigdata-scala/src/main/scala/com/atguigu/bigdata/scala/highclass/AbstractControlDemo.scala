package com.atguigu.bigdata.scala.highclass

object AbstractControlDemo {
  def main(args: Array[String]): Unit = {
    def myRunInThread(f1: () => Unit) = {
      new Thread {
        override def run(): Unit = {
          f1() //只写了 f1
        }
      }.start()
    }

    myRunInThread{
      ()=>
        println("干活咯！5秒完成...")
        Thread.sleep(5000)
        println("干完咯！")
    }


  }
}
