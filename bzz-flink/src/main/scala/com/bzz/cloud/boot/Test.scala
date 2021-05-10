package com.bzz.cloud.boot

/**
  * @description：${description}
  * @author ：Iscrdr
  * @email ：624003618@qq.com
  * @date ：2020/02/25 17:42
  * @modified By：
  * @version: $version$
  */





object Test {

  class Req {
    class Response{
    }
    def getRes1(response:Response) = println("这是Response类型");
    def getRes2(response:Req#Response) = println("这Req中的Response类型")
  }

  def main(args: Array[String]): Unit = {
    val req1 = new Req
    val req2 = new Req
   req2.getRes1(new req2.Response)
    req2.getRes2(new req1.Response)

  }

}
