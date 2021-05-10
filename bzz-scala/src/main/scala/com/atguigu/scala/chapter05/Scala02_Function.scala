package com.atguigu.scala.chapter05

object Scala02_Function {
  def main(args: Array[String]): Unit = {

    test()
    test("test1")
    val res: String = test("test1","test2")
    println(res)

  }
  def test():Unit = {
    println("test")
  }
  //函数声明
  def test(s:String):Unit = {
    println(s)
  }
  //函数声明
  def test(s1:String,s2:String):String = {
    return s1+s2
  }



}
