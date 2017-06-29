package main.scala.spark.sparkstreaming

import java.util.Calendar

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * Created by Asher on 2017/1/13.
  */
object StreamingProgram {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("socket-stream")
      .setMaster("local[4]")
    val ssc = new StreamingContext(conf, Seconds(10))
    val ss = ssc.socketTextStream("localhost", 50070)
      .map(rec => rec.split(","))
      .map(x => {
        val x1 = x.map(xx => {
          xx.substring(1, xx.length - 1)
        })
        x1
      })
      .map(rec => (rec(13), rec(0).toInt))
      .reduceByKey(_ + _)
      .map(pair => {
        (pair._2, (Calendar.getInstance().get(Calendar.YEAR) - pair._1.toInt).toString)
      })
      .transform(rdd => rdd.sortByKey(ascending = false))

    ss.print(10)
    ssc.start()
    ssc.awaitTermination()

  }

}
