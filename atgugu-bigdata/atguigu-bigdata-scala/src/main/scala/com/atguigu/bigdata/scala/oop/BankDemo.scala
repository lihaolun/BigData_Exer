package com.atguigu.bigdata.scala.oop

//编写一个Account类
//属性：账号，密码，余额
//方法：取款，存款，查询
object BankDemo {
  def main(args: Array[String]): Unit = {

  }
}

/*
class Account(inAccount: Int, inPwd: String, inBalance: Double) {
  private val accountNo = inAccount
  private var pwd = inPwd
  private var balance = inBalance
  def this(inPwd:String){
    this(inAccount,inPwd,inBalance)
    this.pwd = inPwd
  }

    //查询
  def query(pwd: String): Unit = {
    if (!this.pwd.equals(pwd)) {
      println("密码错误")
    } else {
      printf("账号为%s，当前余额是%.2f\n", this.accountNo, this.balance)
    }
  }

  //存款
  def saveMoney(pwd: String, money: Double): Unit = {
    if (this.pwd.equals(pwd)) {
      this.balance = this.balance + money
      true
    } else {
      false
    }
  }

  //取钱
  def withDraw(pwd: String, money: Double): Any = {
    if (!this.pwd.equals(pwd)) {
      println("密码错误")
      return
    }
    //判断money是否合理
    if (this.balance < money) {
      println("余额不足")
      return
    }
    this.balance -= money
    balance
  }
  //转账
  def transfer(rollin:Int,pwd:String,money:Double): Unit ={
    //查询转出账号
    if(this.pwd=pwd){
      var rollout = accountNo
      if(money<this.balance){
        balance = this.balance - money
        return true
      }
    }else{
      return false
    }

  }
}*/
