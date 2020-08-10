package com.atguigu.bigdata.scala.fordemo

object ForDemo2 {
  def main(args: Array[String]): Unit = {
    //打印1到100之间所有是9的倍数的整数的个数及总和
    var count = 0
    var sum = 0
    for (i <- 1 to 100) {
      if (i % 9 == 0) {
        sum += i
        count += 1
      }
    }
    printf("count=%d,sum=%d\n",count,sum)
    //输出加法的循环表达式
    val num = 6
    for(i<-1 to num){
      printf("%d + %d = %d\n",i,(num-i),num)
    }
  }
}
