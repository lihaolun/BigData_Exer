package com.atguigu.bigdata.scala.chapter15.project.service

import com.atguigu.bigdata.scala.chapter15.project.bean.Customer

import scala.collection.mutable.{ArrayBuffer, ListBuffer}
import util.control.Breaks._

class CustomerService {
  var customerNum = 0
  val customers = ArrayBuffer[Customer]()

  //添加客户
  def add(customer: Customer): Boolean = {
    customerNum += 1
    customer.id = customerNum
    //加入到customers
    customers.append(customer)
    true
  }

  //删除客户
  def delete(id: Int): Boolean = {
    //查找编号为id的用户的index
    val index = findIndexById(id)
    if (index != -1) {
      //删除
      customers.remove(index)
      true
    } else {
      false
    }
  }

  def list(): ArrayBuffer[Customer] = {
    this.customers
  }

  //id和index不对应，删除之前先查找有没有这样的id和id对应的index
  def findIndexById(id: Int): Int = {
    var index = -1
    breakable {
      for (i <- 0 until customers.length) {
        if (customers(i).id == id) {
          index = i
          break()
        }
      }
    }
    index
  }
}
