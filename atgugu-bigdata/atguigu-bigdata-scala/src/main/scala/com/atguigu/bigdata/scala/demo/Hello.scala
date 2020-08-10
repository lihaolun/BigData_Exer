package com.atguigu.bigdata.scala.demo

object Hello {
  def main(args: Array[String]): Unit = {
    /*  println("hello scalla")
      var name : String = "tom"
      var sal : Double = 1.2
      printf("name=%s sal=%f\n", name, sal)

      println(s"第三种方式 name=$name sal = ${sal + 1}")*/

    /*var age: Int = 10
    var sal: Double = 10.9
    var name:String = "tom"
    var isPass:Boolean = true
    //在scala中，小数默认为Double ,整数默认为Int
    var score:Float = 70.9f
    println(s"${age} ${isPass}")*/


    // / 的使用
    /*    var r1 : Int = 10 / 3 // 3
    println("r1=" + r1)
    var r2 : Double = 10 / 3 // 3.0
    println("r2=" + r2)
    var r3 : Double = 10.0 / 3 // 3.333333
    println("r3=" + r3 )
    println("r3=" + r3.formatted("%.2f") )// 3.33*/

    // % 的使用
    //1. % 的运算的原则: a % b = a - a/b * b
    /* println(10 % 3) // 1
    println(-10 % 3) // -1  // -10 % 3 = (-10)- (-3) * 3 = -10 + 9 = -1
    println(-10 % -3 ) // -1 // -10 % -3 = (-10)- (3) * -3 = -10 + 9 = -1
    println(10 % -3 ) // 1*/

    /* val res = for(i <- 1 to 10) yield {
      if (i % 2 == 0) {
        i
      }else {
        "不是偶数"
      }
    }
    println(res)
*/
   /* try {
      val i = 10 / 0
    } catch {
      case ex: ArithmeticException => {
        println("捕获了除数为零的算数异常")
      }
      case ex: Exception => {
        println("捕获了异常")
      } } finally
      {
        println("scala finally")
      }
*/
   /* test()
    def test(): Nothing = {
      throw new ArithmeticException("算术异常")//Exception("异常NO1出现~")
    }
*/
   val p1 = new Person2
    p1.name = "jack"
    p1.age = 10

    val p2 = p1
    println(p1 == p2) // true
    p1.name = "tom"
    println("p2.name=" + p2.name)

  }

  @throws(classOf[NumberFormatException])//等同于NumberFormatException.class
  def f11()  = {
    "abc".toInt
  }
    }
class A {
  var var1 :String = _  // null
  var var2 :Byte = _  // 0
  var var3 :Double = _  //0.0
  var var4 :Boolean = _  //false
}
class Person2 {
  var name = ""
  var age: Int  = _ //如果是用 _ 方式给默认值，则属性必须指定类型
}
