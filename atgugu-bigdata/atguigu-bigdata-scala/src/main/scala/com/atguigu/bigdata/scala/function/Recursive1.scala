package com.atguigu.bigdata.scala.function

object Recursive1 {
  def main(args: Array[String]): Unit = {
    println(recur1(7))
    println(recur2(3))
    println(recur3(1))
  }

  //给一个整数n，求出它的斐波那契数
  def recur1(num: Int): Int = {
    if (num == 1 || num == 2) {
      1
    } else {
      recur1(num - 1) + recur1(num - 2);
    }
  }

  //已知 f(1)=3; f(n) = 2*f(n-1)+1;
  def recur2(n: Int): Int = {
    if (n == 1) {
      3
    } else {
      2 * recur2(n - 1) + 1
    }
  }

  //猴子吃桃子问题
  //有一堆桃子，猴子第一天吃了其中的一半，并再多吃了一个！
  // 以后每天猴子都吃其中的一半，然后再多吃一个。当到第十
  // 天时，想再吃时（还没吃），发现只有1个桃子了。问题：最初共多少个桃子？
  //思路分析：day10 1
  //         day9  （1+1） * 2
  //         day8   (day9[4] + 1) * 2
  def recur3(day: Int): Int = {
    if (day == 10) {
      1
    } else {
      (recur3(day + 1) + 1) * 2
    }
  }
}
