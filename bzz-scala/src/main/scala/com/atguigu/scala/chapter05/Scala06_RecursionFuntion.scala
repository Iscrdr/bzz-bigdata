package com.atguigu.scala.chapter05

object Scala06_RecursionFuntion {
  def main(args: Array[String]): Unit = {
    /*
     * 递归
     * 1)函数的逻辑代码调用自身
     * 2)函数在调用自身时,传递的参数应该有规律
     * 3)函数应该有跳出的递归的逻辑,否则会出现死循环
     * 4)递归函数无法推断返回值类型,必须指定返回值类型
     */

    //阶乘
    println(test(3))
  }

  def test(i:Int): Int ={
    if(i==1){
        1
    }else{
      i * test(i-1)
    }
  }

}
