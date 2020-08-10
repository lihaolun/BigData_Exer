package com.atguigu.bigdata.scala.test

import scala.beans.BeanProperty

object exercise9 {

  //2.创建一个Student类，加入可读写的JavaBeans属性name(类型为String)和id(类型为Long)。有哪些方法被生产？
  // (用javap查看。)你可以在Scala中调用JavaBeans的getter和setter方法吗？
  class Student() {
    @BeanProperty var name: String = _
    @BeanProperty var id: Long = _
  }

}
