package main.scala.spark.practice

import org.apache.spark.sql.SparkSession

/**
  * Created by SCAL on 2017/5/12.
  */
object testSQL {
  def main(args: Array[String]): Unit = {
    val sc = SparkSession.builder()
      .appName("testSQL")
      .master("local[4]")
      .config("spark.sql.warehouse.dir", "spark-warehouse")
      .getOrCreate()
      .sparkContext
    sc.setLogLevel("WARN")
    val rdd = sc.parallelize(List((1, 2), (1, 3), (1, 4), (2, 3), (2, 5)), 3)
    def seq(a: Int, b:Int): Int ={
      println("seq: " + a + "\t " + b)
      return math.max(a, b)
    }
    def comb(a: Int, b:Int): Int ={
      println("comb: " + a + "\t " + b)
      return a+b
    }

    val aggregateByKeyRDD = rdd.aggregateByKey(0)(seq,comb)
    aggregateByKeyRDD.foreach(println)


    sc.stop()

  }
}
