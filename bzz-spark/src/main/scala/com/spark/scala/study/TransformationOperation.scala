package com.spark.scala.study

import org.apache.spark.{SparkConf, SparkContext}

object TransformationOperation {



  def map(): Unit ={
    val sparkConf = new SparkConf().setMaster("local").setAppName("TransformationOperation")

    val sparkContext = new SparkContext(config = sparkConf)

    val nums : List[Int] = List(1,2,3,4,5)

    val sparkRDD = sparkContext.parallelize(nums)
    val numberRDD =  sparkRDD.map(num => num * 2)

    numberRDD.foreach(num => {
      println(num )
    })
  }

  def filter():Unit = {
    val sparkConf = new SparkConf().setMaster("local").setAppName("TransformationOperation")
    val sparkContext = new SparkContext(sparkConf)
    val nums : List[Int] = List(1,2,3,4,5)
    val numbersRDD = sparkContext.parallelize(nums)
    val numRDD = numbersRDD.filter(num => num % 2 == 1)
    numRDD.foreach(num =>{
      println(num)
    })
  }
  def flatMap():Unit = {
    val sparkConf = new SparkConf().setMaster("local").setAppName("flatMap")
    val sparkContext = new SparkContext(sparkConf)
    val nums : List[String] = List("hello you ","hello me ","hello world ","hello she ","hello he ")
    val numsRDD = sparkContext.parallelize(nums)
    val wordsRDD = numsRDD.flatMap(num => num.split(" "))
    wordsRDD.foreach(s => println(s))
  }
  def groupByKey():Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("groupByKey");
    val context = new SparkContext(conf)

    val socres : List[Tuple2[String,Int]] = List(
      ("math1",80),
      ("math2",80),
      ("math3",80),
      ("math1",81),
      ("math2",65),
      ("math3",90)
    )
    val scoresPairRDD = context.parallelize(socres);
    val scoresRDDIterate = scoresPairRDD.groupByKey()
    scoresRDDIterate.foreach(score => score._2.foreach(t => println(score._1+" ===> "+ t)))

  }

  def reduceByKey():Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("groupByKey");
    val context = new SparkContext(conf)

    val socres : List[Tuple2[String,Int]] = List(
      ("math1",80),
      ("math2",80),
      ("math3",80),
      ("math1",81),
      ("math2",65),
      ("math3",90)
    )
    val scoresPairRDD = context.parallelize(socres);
    val scoresRDDIterate = scoresPairRDD.reduceByKey(_ + _)
    scoresRDDIterate.foreach(score => println(score._1+" ===> " + score._2))
  }
  def sortByKey():Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("groupByKey");
    val context = new SparkContext(conf)

    val socres : List[Tuple2[String,Int]] = List(
      ("math1",80),
      ("math2",80),
      ("math3",80),
      ("math1",81),
      ("math2",65),
      ("math3",90)
    )
    val scoresPairRDD = context.parallelize(socres);
    val scoresPairRDDs = scoresPairRDD.map(x => (x._2,x._1))

    val scoresRDDIterate = scoresPairRDDs.sortByKey()
    scoresRDDIterate.foreach(score => println(score._2+" ===> " + score._1))
  }
  def join():Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("groupByKey");
    val context = new SparkContext(conf)
    val student : List[Tuple2[Int,String]] = List(
      (1,"math1"),
      (2,"math2"),
      (3,"math3")
    )
    val socres : List[Tuple2[Int,Int]] = List(
      (1,80),
      (2,85),
      (3,82)
    )
    val studentPairRDD = context.parallelize(student);
    val scoresPairRDD = context.parallelize(socres);
    val ssPairRDD = studentPairRDD.join(scoresPairRDD).sortBy(x => x._2._2)

    ssPairRDD.foreach(score => println(score._1 + " ===> " + score._2._1 + " ===> " + score._2._2))
  }
  def cogroup():Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("groupByKey");
    val context = new SparkContext(conf)
    val student : List[Tuple2[Int,String]] = List(
      (1,"math1"),
      (2,"math2"),
      (3,"math3")
    )
    val socres : List[Tuple2[Int,Int]] = List(
      (1,80),
      (2,85),
      (3,82)
    )
    val studentPairRDD = context.parallelize(student);
    val scoresPairRDD = context.parallelize(socres);
    val ssPairRDD = studentPairRDD.cogroup(scoresPairRDD)

    ssPairRDD.foreach(score => println(score._1 + " ===> " + score._2._1.iterator.next() + " ===> " + score._2._2.iterator.next()))
  }

  def main(args: Array[String]): Unit = {
    //map()
    //filter()
    //flatMap()
    //groupByKey()
    //reduceByKey()
    //sortByKey()
    //join()
    cogroup()
  }
}
