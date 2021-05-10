package com.atguigu.scala.chapter060708

/**
  * @description：${description}
  * @author ：Iscrdr
  * @email ：624003618@qq.com
  * @date ：2020-11-06 18:28
  * @modified By：
  * @version: ${version}
  */
//伴生类
class Student {
  private val sname = "zhangsan"

}
//伴生对象
object Student{

  def apply(): Student = new Student()

  def test():Unit = {
    new Student().sname;
  }

}