package com.atguigu.bigdata.scala.arraydemo

object MutiplyArray {
  def main(args: Array[String]): Unit = {
    //创建多维数组
    val arr = Array.ofDim[Int](3, 4)
    //遍历
    for (elem <- arr) {
      for (elem2 <- elem) {
        print(elem2 + "\t")
      }
      println()
    }
    arr(1)(1) = 1
    println(arr(1)(1))

    //使用传统的方法遍历
    println("============")
    for (i<- 0 to arr.length-1){
      for(j<- 0 to arr(i).length-1){
        printf("arr[%d][%d]=%d\t",i,j,arr(i)(j))
      }
      println()
    }
  }

}

