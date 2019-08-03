package com.spark.scala.sql

import java.sql.{DriverManager, PreparedStatement, ResultSetMetaData}

object SparkSqlThriftServerApp {
  def main(args: Array[String]): Unit = {
    Class.forName("org.apache.hive.jdbc.HiveDriver")

    val conn = DriverManager.getConnection("jdbc:hive2://hadoop-01:10000","hadoop","")
    val psmt: PreparedStatement = conn.prepareStatement("select * from par_tab where sex = 'man'")
    val rs = psmt.executeQuery()
    while (rs.next()){
      println("name:"+rs.getString("name")+

        ",nation:"+rs.getString("nation")+
        ",sex:"+rs.getString("sex"))
    }

    rs.close()
    psmt.close()

  }

}
