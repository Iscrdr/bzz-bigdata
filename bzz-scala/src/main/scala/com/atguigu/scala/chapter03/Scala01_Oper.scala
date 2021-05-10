package com.atguigu.scala.chapter03

object Scala01_Oper {
  def main(args: Array[String]): Unit = {
    var i = 1;
    i += 1;
    println(i);

    var a = 1; //001
    var b = 2; //010

    a = a ^ b; //011
    b = a ^ b; //010
    a = a ^ b; //001


    println("a="+a+",b="+b);



  }

}
