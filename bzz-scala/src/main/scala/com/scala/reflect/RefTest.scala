package com.scala.reflect


import scala.reflect.ClassTag
import scala.reflect.runtime.{universe => ru}
/**
  * @Author : yang qianli 
  * @email: 624003618@qq.com
  * @Date: 2019-二月-27 18-56
  * @Modified by:
  * @Description:
  */
object RefTest {

  def main(args: Array[String]): Unit = {

    val ref = new Ref
    val m = ru.runtimeMirror(ref.getClass.getClassLoader)
    val refType = ru.typeTag[Ref].tpe
    val method = refType.decl(ru.TermName("askSync")).alternatives(0).asMethod
    val im = m.reflect(ref)
    val askSync: ru.MethodMirror = im.reflectMethod(method)
    askSync( "abcde", implicitly[ClassTag[Any]]) //ArrayIndexOutOfBoundsException

  }
}
