package com.scala.reflect


import scala.reflect.ClassTag

/**
  * @Author : yang qianli 
  * @email: 624003618@qq.com
  * @Date: 2019-二月-27 18-52
  * @Modified by:
  * @Description:
  */
class Ref {
  private [this] val defaultAskTimeout = 1
  def askSync[T:ClassTag](message:Any):T = askSync(message,defaultAskTimeout)
  def askSync[T:ClassTag](message:Any,timeout:Int):T = {
    println("执行:"+message+","+timeout)
    val ret: T = "123".asInstanceOf[T]
    ret
  }
}
