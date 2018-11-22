package com.scala.study

class Person(val name:String) extends Logged {
  def sayHello: Unit ={
    println("Hi,I'm "+name)
    log("sayHello is invoked !")
  }
}
