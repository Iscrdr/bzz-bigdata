package com.atguigu.scala.chapter060708

/**
  *
  */
object Scala01_Object {
  def main(args: Array[String]): Unit = {
    val user = new User
    user.userName = "zhangsan"
    user.age = 10
    println(user)

    val student = Student
  }

}


class User{
  var userName:String = _
  var age:Int = _
  def login():Boolean={
    true
  }


}