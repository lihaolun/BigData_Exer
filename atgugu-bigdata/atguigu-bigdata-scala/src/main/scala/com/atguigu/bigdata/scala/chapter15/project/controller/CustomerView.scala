package com.atguigu.bigdata.scala.chapter15.project.controller

import com.atguigu.bigdata.scala.chapter15.project.bean.Customer
import com.atguigu.bigdata.scala.chapter15.project.service.CustomerService

import scala.io.StdIn
import util.control.Breaks._

class CustomerView {
  var key = ' '
  var loop = true
  val customerService = new CustomerService()

  /*  ----------------- 客户信息管理软件 -----------------

    1 添 加 客 户
    2 修 改 客 户
    3 删 除 客 户
    4 客 户 列 表
    5 退 出

    请选择(1 - 5)
    ： _*/
  def showMenu(): Unit = {

    do {
      println("-----------------客户信息管理软件-----------------")
      println("-----------------1 添 加 客 户")
      println("-----------------2 修 改 客 户")
      println("-----------------3 删 除 客 户")
      println("-----------------4 客 户 列 表")
      println("-----------------5 退         出")
      println("请选择(1-5)：_")
      key = StdIn.readChar()
      key match {
        case '1' => this.add()
        case '2' => println("修 改 客 户")
        case '3' => this.delete()
        case '4' => this.list()
        case '5' => this.loop = false
      }
    } while (loop)
    println("退出系统")
  }

  def add(): Unit = {
    println("-----------------添 加 客 户-----------------")
    println("姓名: ")
    val name = StdIn.readLine()
    println("性别: ")
    val gender = StdIn.readChar()
    println("年龄: ")
    val age = StdIn.readShort()
    println("电话: ")
    val tel = StdIn.readLine()
    println("邮箱: ")
    val email = StdIn.readLine()
    val customer = new Customer(name, gender, age, tel, email)
    customerService.add(customer)
    println("-----------------添 加 完 成-----------------")
  }

  def delete(): Unit = {
    println("-----------------删除客户-----------------")
    println("请输入要删除客户的编号（-1退出）: ")
    val id = StdIn.readInt()
    if (id == -1) {
      println("-----------------删除没有完成-----------------")
      return
    }
    println("确认是否删除?Y/N: ")
    var flag = ' '
    breakable {
      do {
        flag = StdIn.readChar().toLower
        if (flag == 'y' || flag == 'n') {
          break()
        }
        println("确认是否删除?Y/N: ")
      } while (true)
    }

    if (flag == 'y') {
      if (customerService.delete(id)) {
        println("-----------------删除完成-----------------")
        return
      }
    }
    println("-----------------删除没有完成-----------------")
  }

  def list(): Unit = {
    println("-----------------客 户 列 表-----------------")
    println("编号\t\t姓名\t\t性别\t\t年龄\t\t电话\t\t邮箱")
    val customers = customerService.list()
    for (elem <- customers) {
      println(elem)
    }
    println("-----------------客户列表完成-----------------")
  }

}
