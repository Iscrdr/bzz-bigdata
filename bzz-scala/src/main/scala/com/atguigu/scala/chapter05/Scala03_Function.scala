package com.atguigu.scala.chapter05

/**
 * @description：函数式编程
 * @author     ：Iscrdr
 * @date       ：2020-09-06 03:20
 * @email      ：624003618@qq.com
 * @modified By：
 * @version:     1.0.0
 */
object Scala03_Function {


  //1.如果函数声明时,明确无返回值,那么即使函数有返回值,也不起作用
  //1.1如果函数没有返回值那么等号可以省略,省略后编译器不会将函数最后一行代码作为返回值
  def test():Unit = {
    return "zhangsan"
  }
  def test01(){
    "zhangsan"
  }

  //2.如果将函数的最后一行代码进行返回,那么return关键字可以省略
  def test1():String = {
    "zhangsan1"
  }
  //3.如果可以根据函数的返回值推断类型,那么函数的返回值可以省略
  def test2(){
    "zhangsan2"
  }
  //4.如果函数中只有一行代码,那么大括号可以省略
  def test3() = "zhangsan3"
  //5.如果函数中没有参数列表,那么小括号可以省略.但是在函数调用时也不能加小括号
  def test4 = "zhangsan4"

  //6.匿名函数
  ()->println("xxxxx")

  //7.可变参数
  def test7(name:String*):Unit={
    println(name)
  }

  //8.默认参数,调用时参数匹配规则从前到后,从左到右
  def test8(name:String,age:Int=20): Unit ={
    println(s"${name},${age}")
  }
  def test9(name2:String="lisi",name1:String): Unit ={
    println(s"${name1},${name2}")
  }
  //
  def main(args: Array[String]): Unit = {

    val unit1: Unit = test()
    val unit2: Unit = test01()
    val str1: String = test1()
    val unit: Unit = test2()
    val str: String = test3()
    val test5: String = test4
    val json: Unit = test7("json", "xml")
    test8("zhangsan",18)
    test9("zhangsan1","zhangsan2")


    //scala是完全面向对象式的编程语言
    def f():Unit={
      println("function")
    }
    def f0()={
      //返回函数
      //直接返回函数,有问题,须要增加特殊符号：_
      f _
    }
   //f0()()

    def f1(i:Int) ={
      def f2(j:Int)={
        i*j
      }
      f2 _
    }
    println(f1(2)(3))

    //函数的柯里化
    def f3(i:Int)(j:Int):Int={
      i*j
    }
    println(f3(2)(3))

    // 一个函数在实现逻辑时,将外部的变量引入函数的内容,改变了这个变量的生命周期,称之为闭包
    def f11(i:Int) ={
      def f22(j:Int)={
        i*j
      }
      f22 _
    }

    //将函数作为参数传入另一个函数时,将采用特殊的方式声明
    //()=>unit
    val toInt :(Int) =>Int = f1(10)

    def f4(f1:() => Int):() => Int={

      f1
    }
    def f5():Int={
      5
    }
    println(f4(f5))

    //匿名函数改善代码
    def f6(f:()=>Unit):Unit={
      println("f666")
      f()
    }
    f6(()=>{println("f77777")})

  }
}
