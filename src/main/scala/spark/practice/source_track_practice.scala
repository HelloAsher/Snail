package main.scala.spark.practice

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession

/**
  * Created by yzbfl on 2017-04-19.
  */
object source_track_practice {
  def main(args: Array[String]): Unit = {
    val sc = new SparkContext(new SparkConf().setAppName("source_track").setMaster("local[4]"))

    val data = Array("hello tom", "hello jerry", "hello ace")
    val source_rdd = sc.parallelize(data)
    val result_rdd = source_rdd.flatMap(_.split(" ")).map((_, 1)).reduceByKey(_ + _)
    result_rdd.foreach(println)

  }

}
