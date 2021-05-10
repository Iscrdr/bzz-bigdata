package com.atguigu.scala.chapter04

import scala.util.control.Breaks

object Scala03_For {
  def main(args: Array[String]): Unit = {

    // 使用1 to 5表示循环范围,前后闭合,既包含头又包含尾
    for ( i <- 1 to 5){
      println(i)
    }
    println("---------------------------------")
    // 使用1 to 5表示循环范围,前闭后开,不包含尾
    for ( i <- 1 until  5){
      println(i)
    }
    println("---------------------------------")
    for(i <- 1 to 3){
      for(j<- 1 until(3)){
        println(s"${i}=${j}")
      }
    }

    println("---------------------------------")
    for(i <- 1 to 9){
      for(j <- i to 9){
        print(s"*")
      }
      println(s" ")
    }

    println("---------------------------------")
    for(i <- Range(1,18,2)){
      println(" "*((18-i)/2) + "*"*i +" "*((18-i)/2))
    }
    println("---------------------------------")
    for(i <- Range(1,18,2);j=(18-i)/2){
      println(" "*j + "*"*i +" "*j)
    }

    //TODO循环守卫
    for(i <- 1 to 5 if i%2==0){
     println(i)
    };

    //yield 返回值
    var res = for(i <- 1 to 5 if i%2==0) yield i*2
    println(res)

    //scala中没有break,但是可以采用对象的方式进行中断
    Breaks.breakable(
      for(i<- 1 to 5){
        if(i==3){
          Breaks.break()
        }
        println(s"${i}")
      }
    )
    println(s"===")

  }

}
