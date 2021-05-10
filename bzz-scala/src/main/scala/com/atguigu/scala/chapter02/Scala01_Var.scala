package com.atguigu.scala.chapter02

import java.lang.reflect.Field


object Scala01_Var {

  val name1:String = "zhang";

  def main(args: Array[String]): Unit = {
    println(name1)

    var name: Field = Scala01_Var.getClass.getDeclaredField("name1")
    name.setAccessible(true)

  }

}
