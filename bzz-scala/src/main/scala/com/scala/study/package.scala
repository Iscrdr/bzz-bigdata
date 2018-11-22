package com.scala

import java.io.{FileNotFoundException, IOException}
import java.util

import com.scala.study.Person

import scala.language.postfixOps


object Grade {

  def main(args: Array[String]): Unit = {
   /* judgeGrade("A");
    judgeGrade("f");

    judgeGrade("jack","A");
    judgeGrade("leo","f");
    judgeGrade2("F");
    processException(new IllegalArgumentException("文件找不到"));
    greeting1(Array("Leo1"));
    greeting1(Array("Leo1","Leo2","Leo3"));*/

    greeting(List("Leo1","Leo2","Leo3"));
  }
/*  def judgeIdentify(p:Person): Unit ={
    p match {
      case Teacher(subject) => println(" Teacher, subject is  " +subject )
    }
  }*/

  def greeting1(arr:Array[String]){
    arr match {
      case Array("Leo") => println(" Hello , Leo ！ " )
      case Array(girl1,girl2,girl3) => println(" Hello , grils,nice to meet you,"+girl1+"," +girl2+","+girl3)
      case Array("Leo",_*) => println(" Hi,Leo ,please introduce your friends to me " )
      case strange => println(strange + ", hey , who are you ?")
    }
  }
  def greeting(list:List[String]){
    list match {
      case "Leo"::Nil => println(" Hello , Leo ！ " )
      case "Leo"::Nil => println(" Hello , Leo ！ " )
      case girl1::girl2::girl3::Nil => println(" Hello , grils,nice to meet you,"+girl1+"," +girl2+","+girl3)
      case "Leo"::tail => println(" Hi,Leo ,please introduce your friends to me " )
      case _ => println(" hey , who are you ? " )
    }
  }
  def processException(e:Exception){
    e match {
      case e1:IllegalArgumentException => println(" argumentException: " + e1)
      case e2:FileNotFoundException => println(" FileNotFoundException: " + e2)
      case e3:IndexOutOfBoundsException => println(" IndexOutOfBoundsException: " + e3)
      case e4:IOException => println(" IOException: " + e4)
      case _:Exception => println(" Exception: " )
    }
  }
  def judgeGrade2(grade:String): Unit ={
    grade match {
      case "A" => println("you are Execellent")
      case "B" => println("you are Good")
      case "C" => println("you are just so so")
      case badGrade => println("you got "+ badGrade +" grade,I hope that you can get C grade next time!")
    }
  }
  def judgeGrade(grade:String): Unit ={
    grade match {
      case "A" => println("you are Execellent")
      case "B" => println("you are Good")
      case "C" => println("you are just so so")
      case _ => println("you are study handler")
    }
  }

  def judgeGrade(name:String,grade:String): Unit ={
    grade match {
      case "A" => println(name+",you are Execellent")
      case "B" => println(name+",you are Good")
      case "C" => println(name+",you are just so so")
      case _ if name =="leo" => println(name+",you are study handler")
      case _ => println("you are study handler")
    }
  }
}
