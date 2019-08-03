package com.scala.study

object ValAndVar {

  def main(args: Array[String]): Unit = {
    val age = 18
    var name = "小明"
    println(name+":"+age)

    val res = 1+1
    val res1 = 1.+(2)
    println(res)
    println(res1)

    printf("name:%s,age:%d",name,age)

 /*   println("请输入")
    val str = readLine()
    println(str)*/

println()
    for(i <- 1 to 9;j <- 1 to i){
      print(j +" * " +  i + " = " + i * j+" ")
      if(i==j) println()
    }


    val words = Array("hello tom hello star hello sheep", "hello tao hello tom")
    val strings = words.flatMap(_.split(" ")).groupBy(x => x).toList.toString()
    println(strings)


    val list = words.flatMap(_.split(" ")).groupBy(x => x).map(t => (t._1, t._2.length)).toList.sortBy(_._2).reverse
    list.foreach(x=>println(x._1+":"+x._2))

    val arr = Array(1,2,3,4,5,6,7,8,9,10)
    val sum: Int = arr.par.sum

  }

}
