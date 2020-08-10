package com.atguigu.bigdata.scala.associated

object ChildJoinGame {
  def main(args: Array[String]): Unit = {
    val child01 = new Child("faker")
    val child02 = new Child("bang")
    val child03 = new Child("wolf")
    Child.joinGame(child01)
    Child.joinGame(child02)
    Child.joinGame(child03)
    Child.getNum()
  }
}

class Child(cname: String) {
  var name = cname
}

object Child {
  var totalCount = 0

  def joinGame(child: Child): Unit = {
    printf("名字叫%s的小孩加入了游戏\n", child.name)
    totalCount += 1
  }

  def getNum():Unit={
    printf("现在有%d个小孩在玩游戏",totalCount)
  }
}