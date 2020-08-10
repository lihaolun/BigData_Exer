package com.atguigu.bigdata.scala.test


object exercise6 {
  def main(args: Array[String]): Unit = {
    println(numValue(10))
    test2()
    countdown2(8)
    println(unicodeMult("Hello"))
    unicodeMult2("Hello")
    println(unicodeMult3("Hello"))
    println(mi(2.0,3))
  }

  //1、一个数字如果为正数，则它的signum为1;如果是负数,则signum为-1;如果为0,则signum为0.编写一个函数来计算这个值
  def numValue(num: Int): Int = {
    if (num > 0) {
      1
    } else if (num < 0) {
      -1
    } else {
      0
    }
  }

  //2、一个空的块表达式{}的值是什么？类型是什么？
  def test2(): Unit = {
    val t = {}
    println("t=" + t)
    println(t.isInstanceOf[Unit])
  }

  //3、针对下列Java循环编写一个Scala版本:
  //         for(int i=10;i>=0;i–)System.out.println(i);
  def test3(): Unit = {
    for (i <- 0 to 10 reverse) {
      println(i)
    }
  }

  //4.编写一个过程countdown(n:Int)，打印从n到0的数字
  //方法一
  def countdown1(n: Int) = {
    for (i <- 0 to n reverse) {
      println(i)
    }
  }

  //方法二
  def countdown2(n: Int) = {
    (0 to n).reverse.foreach(println)
  }

  //5.编写一个for循环,计算字符串中所有字母的Unicode代码（toLong方法）的乘积。
  // 举例来说，"Hello"中所有字符串的乘积为9415087488L
  var unicode = 1L

  def unicodeMult(str: String): Long = {
    for (i <- str) {
      unicode *= i.toLong
    }
    unicode
  }

  //6.同样是解决前一个练习的问题，请用StringOps的foreach方式解决。
  var res = 1L

  def unicodeMult2(str: String): Unit = {
    str.foreach(res *= _.toLong)
    println(res)
  }

  //7.把7练习中的函数改成递归函数

  def unicodeMult3(str: String): Long = {
    if (str.length == 1) {
      str.charAt(0).toLong
    } else {
      //toLong方法用char类型调用
      str.take(1).charAt(0).toLong * unicodeMult3(str.drop(1))
    }
  }

  //8.编写函数计算 ,其中n是整数，使用如下的递归定义:
  //x^n = x*x^(n-1) ,如果n是正数的话
  //x^0  = 1
  //x^n	  = 1/x^-n ,如果n是负数的话
  //•	不得使用return语句
  def mi(x: Double, n: Int): Double = {
    if (n == 0) {
      1
    } else if (n > 0) {
      x * mi(x, n - 1)
    } else {
      1 / mi(x, -n)
    }
  }
}
