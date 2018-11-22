package com.scala.study

import scala.collection.mutable.ArrayBuffer

object ArrayTest {
  def main(args: Array[String]): Unit = {
    /*var arr1 = new Array[Int](10);
    arr1(0) = 1;
    arr1(1) = 2;
    arr1(2) = 3;
    arr1(3) = 4;
    println(arr1.length,arr1(1));

   val b = ArrayBuffer[Int]();
    b += 1;
    b += (2,34,6);
   println(b)
    b.insert(3,7);
    println(b.trimEnd(4))

    var student = new Student();
    student.age_=(20);
    println( student.age)*/

   /* val p1 = new Person("leo")
    p1.sayHello
    val p2 = new Person("jack") with MyLogged
    p2.sayHello*/

    //forTest(5);
    forTest("Hello");
  }

  def forTest(i:Int): Unit ={
    for( a <- 0 to i; from = 4-i;j<- from to 3){
      println(j)
    }
  }
  def forTest(s:String): Unit = {
    val border = "-" * s.length + "--\n";
    println("\n"+border + "|" +s + "|\n" +border)

  }
}
