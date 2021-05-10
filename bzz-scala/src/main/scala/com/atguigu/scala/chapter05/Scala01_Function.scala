package com.atguigu.scala.chapter05

import scala.util.control.Breaks

object Scala01_Function {
  def main(args: Array[String]): Unit = {

    //java中方法和scala中的函数都可以进行功能的封装,但是方法必须和雷胸绑定,但是函数不需要

    //函数声明
    test("Hello")
    //函数调用

  }

  //函数声明
  def test(s:String):Unit = {
    println(s)
  }

}
