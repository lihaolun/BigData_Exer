package com.atguigu.bigdata.scala.chapter15.project.app

import com.atguigu.bigdata.scala.chapter15.project.controller.CustomerView

object MenuMain {
  def main(args: Array[String]): Unit = {
    new CustomerView().showMenu()
  }
}
