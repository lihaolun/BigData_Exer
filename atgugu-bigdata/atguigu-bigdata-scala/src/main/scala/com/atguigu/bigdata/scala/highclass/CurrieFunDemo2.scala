package com.atguigu.bigdata.scala.highclass

object CurrieFunDemo2 {
  def main(args: Array[String]): Unit = {
    def eq1(s1:String,s2:String):Boolean={
      s1.equals(s2)
    }
    implicit class TestEQ(s:String){
      def checkEq(ss:String)(f:(String,String)=>Boolean):Boolean={
        f(s.toLowerCase,ss.toLowerCase)
      }
    }

    val str1 = "hello"
    println(str1.checkEq("HeLLO")(eq1))

    //简写
    println(str1.checkEq("HELLO")(_.equals(_)))

  }
}
