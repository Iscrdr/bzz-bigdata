package com.atguigu.scala.chapter01

object StringPrint {

  def main(args: Array[String]): Unit = {
    val name = "张三"
    val age = 20
    val url = "www.atguigu.com"

    //printf("name=%s,age=%d,url=%s",name,age,url)

    //printf(f"name=${name},age=${age}%.2f,url")

    printf(raw"name,age,url")

  }

}
