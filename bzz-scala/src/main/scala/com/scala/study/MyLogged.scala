package com.scala.study

trait MyLogged extends Logged{
  override def log(msg: String): Unit ={
    println("LOG : "+msg)
  }
}
