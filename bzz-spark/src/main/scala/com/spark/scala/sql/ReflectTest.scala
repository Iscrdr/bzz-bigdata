package com.spark.scala.sql

import java.lang.reflect.Constructor

//import oracle.jdbc.proxy.annotation.Methods
import org.apache.hadoop.conf.Configuration
import org.apache.spark.SparkConf
import org.apache.spark.deploy.SparkHadoopUtil

import scala.reflect.runtime.{universe => ru}
/**
  * @Author : yang qianli
  * @email: 624003618@qq.com
  * @Date: 2019-二月-26 21-30
  * @Modified by:
  * @Description:
  */
object ReflectTest {
  def main(args: Array[String]): Unit = {
    //val conf = new SparkConf()
    /*val cls = Class.forName("org.apache.spark.deploy.SparkHadoopUtil")
    val configuration = cls.getDeclaredMethod("newConfiguration", classOf[SparkConf])
      .invoke(null, conf).asInstanceOf[Configuration]
    println(configuration)*/

    /*val classMirror = ru.runtimeMirror(getClass.getClassLoader)
    val mirror = classMirror.reflect(org.apache.spark.deploy.SparkHadoopUtil)

    val method = ru.typeOf[org.apache.spark.deploy.SparkHadoopUtil.type].decl(ru.TermName("newConfiguration")).asMethod
    val newConfiguration = mirror.reflectMethod(method)

    println(newConfiguration())*/
    val obj: Class[_] = Class.forName("org.apache.spark.deploy.SparkHadoopUtil")
    val constructors: Array[Constructor[_]] = obj.getDeclaredConstructors
    constructors(0).setAccessible(true)
    val ins: Any = constructors(0).newInstance()
    val newConfiguration = obj.getDeclaredMethod("newConfiguration",classOf[SparkConf])
    val configuration = newConfiguration.invoke(ins,new SparkConf()).asInstanceOf[Configuration]
    println(configuration)
  }

}
