package com.spark.scala.study

/**
  * @Title: SecondSortByKey
  * @ProjectName bzz-cloud
  * @Auther: clouder
  * @Date: 2018-07-14-1:30
  * @Description:
  */
class SecondSortByKey(val x:String, val y:Int) extends Ordered[SecondSortByKey] with Serializable{
  override def compare(that: SecondSortByKey): Int = {
    if(!this.x.equals(that.x)){
      this.x.hashCode - that.x.hashCode
    }
    else {
      this.y - that.y
    }


  }
}
