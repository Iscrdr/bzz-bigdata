package com.bzz.cloud.flink.loganalysis

import java.text.SimpleDateFormat
import java.util.{Date, Properties}

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.api.java.tuple.Tuple
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.watermark.Watermark
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.streaming.api.scala.function.WindowFunction
import org.apache.flink.util.Collector
import org.slf4j.LoggerFactory
/**
  * @program: bzz-bigdata
  * @description: ${description}
  * @author: 624003618@qq.com
  * @create: 2019-09-19 16:41
  *
  */
object LogAnalysis {

  val logger = LoggerFactory.getLogger(LogAnalysis.getClass)


  def main(args: Array[String]): Unit = {

    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)

    val prop = new Properties()
    prop.setProperty("bootstrap.servers",
                      "hadoop-01:9092," +
                      "hadoop-02:9092," +
                      "hadoop-03:9092," +
                      "hadoop-04:9092," +
                      "hadoop-05:9092")
    prop.setProperty("group.id","test")

    val topic = "pktest"
    val consumer = new FlinkKafkaConsumer[String](topic,new SimpleStringSchema(),prop)

    val data = env.addSource(consumer)
    //data.print().setParallelism(1);
    val logData = data.map(x => {
      val splits = x.split("\t")
      val level = splits(2)
      val timeStr = splits(3)
      var time = 0L
      try {
        val srcformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        time = srcformat.parse(timeStr).getTime
      } catch {
        case e:Exception => logger.error(s"time parse error: $timeStr" + e.getMessage)
      }

      val domain = splits(5)
      val traffic = splits(6).toLong
      (level, time, domain, traffic)
    }).filter(_._2 !=0).filter(_._1 == "E").map(x=>(x._2,x._3,x._4))
    /*
     * 过滤掉不符合规则的数据，例如：time=0的数据
     *
     *
     */
    //logData.print().setParallelism(1)

    logData.assignTimestampsAndWatermarks(new AssignerWithPeriodicWatermarks[(Long, String, Long)] {

      val maxOutOfOrderness = 10000L //seconds

      var currentMaxTimestramp: Long = _

      override def getCurrentWatermark: Watermark = {
        new Watermark(currentMaxTimestramp - maxOutOfOrderness)
      }
      override def extractTimestamp(t: (Long, String, Long), l: Long): Long = {
        val timestamp = t._1
        currentMaxTimestramp = Math.max(timestamp,currentMaxTimestramp)
        timestamp
      }
    }).keyBy(1)
      .window(TumblingEventTimeWindows.of(Time.seconds(60)))
      .apply(new WindowFunction[(Long,String,Long),(String,String,Long),Tuple,TimeWindow]{
        override def apply(key: Tuple, window: TimeWindow, input: Iterable[(Long, String, Long)], out: Collector[(String, String, Long)]): Unit = {

          val domain = key.getField(0).toString

          val iterator: Iterator[(Long, String, Long)] = input.iterator
          var sum = 0l

          var timeStr = ""
          while(iterator.hasNext){
            var next = iterator.next()
            sum += next._3
            var timeLong = next._1

            try {
              val format = new SimpleDateFormat("yyyy-MM-dd HH:mm")
              timeStr = format.format(new Date().setTime(timeLong))
            } catch {
              case e:Exception => logger.error(s"timeLong parse error: $timeLong" + e.getMessage)
            }
          }

          /**
            * 第一个参数：这一分钟的时间
            * 第二个参数：域名
            * 第三个参数：traffic的 和
            */


          out.collect((timeStr,domain,sum))
        }
      })


    ;
    logData.print().setParallelism(1);

    env.execute("LogAnalysis")
  }

}
