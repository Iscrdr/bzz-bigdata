package com.scala.study

object  HelloWorld {

  def main(args: Array[String]): Unit = {
    println("Hello World")
    println(fab(10))
    sayHello1("tom",20)
    sayHello2(age=20,name="tom")
    sayHello1()
    println(sum(1,2,3))
    println(sum2(1,2,3))
  }
  def sum2(nums:Int*):Int = {
    if(nums.length == 0)
      0
    else
      nums.head + sum2(nums.tail: _*)
  }
  def sum(nums:Int*):Int = {
    var result = 0;
    for(num <- nums){
      result += num;
    }
    result
  }

  def fab(n:Int):Int = {
    if(n <= 0)
      1
    else
      fab(n-1) + fab(n-2)
  }
  def sayHello1(name:String="heo",age:Int=10):Unit = {
    println("name:"+name+",age:"+age)
  }


  def sayHello2(name:String,age:Int):Unit = {
   println("name:"+name+",age:"+age)
  }

}
