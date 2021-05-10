package com.bzz.cloud.flink.sink

import com.bzz.cloud.boot.Student
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.api.scala._
import org.apache.flink.streaming.connectors.redis.RedisSink
import org.apache.flink.streaming.connectors.redis.common.config.FlinkJedisPoolConfig
import org.apache.flink.streaming.connectors.redis.common.mapper.{RedisCommand, RedisCommandDescription, RedisMapper}
/**
  * @description：flink sink redis
  * @author ：Iscrdr
  * @email ：624003618@qq.com
  * @date ：2020/02/01 22:39
  * @modified By：
  * @version: 1.0
  */
object RedisSink {

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(1)


    val lines = env.readTextFile("D:\\testdata\\student.txt")

    val data:DataStream[Student] =  lines.map(data => {
      val strings = data.split(",")
      Student(strings(0).toInt,strings(1),strings(2).toInt)
    })

    val conf = new FlinkJedisPoolConfig.Builder()
        .setHost("192.168.132.150")
        .setPort(6379)
        .build()


    data.addSink(new RedisSink(conf,new MyRedisMapper))

  }

}

class MyRedisMapper() extends RedisMapper[Student]{
  //定义保存到redis的命令
  override def getCommandDescription: RedisCommandDescription = {
    new RedisCommandDescription(RedisCommand.HSET,"student")
  }
//构建key
  override def getKeyFromData(t: Student): String = {
    "name_"+t.name
  }
//构建value
  override def getValueFromData(t: Student): String = {
    t.age+""
  }
}