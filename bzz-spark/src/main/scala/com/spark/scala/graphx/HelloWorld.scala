package com.spark.scala.graphx

import org.apache.spark.graphx.{Edge, Graph, VertexId}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object HelloWorld {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("HelloWorld").setMaster("local[1]")

    val sc = new SparkContext(conf)

    //创建一个顶点的集合，VertexId是一个Long类型，顶点属性为一个二元数组
    val users: RDD[(VertexId, (String, String))] = sc.parallelize(Array((3L, ("rxin", "sutdent")), (7L, ("jgonzal", "postdoc")),
      (5L, ("franklin", "prof")), (2L, ("istoica", "prof"))))

    //创建一个边集合，属性为String
    val relationships = sc.parallelize(Array(Edge(3L, 7L, "collab"), Edge(5L, 3L, "advisor"),
      Edge(2L, 5L, "colleague"), Edge(5L, 7L, "pi")))


    val defaultUser = ("John Doe","Missing")

    //创建一个图
    val graph = Graph(users,relationships,defaultUser)

    val verticesCount = graph.vertices.filter{case(id,(name,pos)) => pos == "postdoc"}.count

    println("verticesCount："+verticesCount)

    val edgeCount = graph.edges.filter(e => e.srcId > e.dstId).count()

    println("edgeCount："+edgeCount)




    sc.stop()

  }

}
