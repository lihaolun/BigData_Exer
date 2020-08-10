package com.atguigu.bigdata.scala.chapter12

object MatchObjectDemo6 {
  def main(args: Array[String]): Unit = {
    val d: Double = 36

    d match {
      case Squre(n) => println("匹配成功 n="+ n)
      case _ => println("nothing matched")
    }

    val namesString = "Alice,Bob,Thomas" //字符串
    namesString match {
      case Names(a,b,c)=>{
        println(s"$a,$b,$c")
      }
      case _ =>
        println("nothing matched")
    }
  }
}
object Squre{
  def unapply(z: Double): Option[Double] = {
    println("unapply被调用 z 是=" + z)
    Some(math.sqrt(z))
  }

  def apply(z:Double):Double=z*z
}

object Names{
 //当构造器是多个参数时没救会触发这个对象提取器

  def unapplySeq(str:String): Option[Seq[String]] ={
    if (str.contains(",")) Some(str.split(","))
    else None
  }
}