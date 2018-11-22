package com.scala.study

class Student(override val name:String)  extends Person("student") {

  private var myAge = 0;


  def age_=(newValue:Int): Unit ={
    if(newValue > 0){
      myAge = newValue
    }else{
      print("error age !")
    }
  }

  def age = myAge;
  def older(s:Student)={
    myAge > s.myAge;
  }


}
