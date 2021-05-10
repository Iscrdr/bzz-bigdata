package com.atguigu.scala.chapter05

object Scala08_LazyFuntion {
  def main(args: Array[String]): Unit = {
    lazy val res = sum(10,20)
    println("-----------------------")
    println("-----------------------")
    println("-----------------------")
    println("-----------------------")
    println("res:"+res)

  }

  def sum(a:Int,b:Int): Int ={
    println("sum 执行")
    a+b
  }

}
