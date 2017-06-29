package main.scala.spark.utils

import org.apache.spark.AccumulableParam

import scala.collection.mutable

/**
  * Created by Asher on 2017/2/18.
  */
object StockAccum extends AccumulableParam[mutable.HashMap[String, (Long, Long, Long)], (String, (Float, Long))] {
  override def addAccumulator(r: mutable.HashMap[String, (Long, Long, Long)],
                              t: (String, (Float, Long))):
                              mutable.HashMap[String, (Long, Long, Long)] = ???

  override def addInPlace(r1: mutable.HashMap[String, (Long, Long, Long)],
                          r2: mutable.HashMap[String, (Long, Long, Long)]):
                          mutable.HashMap[String, (Long, Long, Long)] = {
    val ss = r1 ++ r2.map {
      case (k, v2) => k -> {
        val v1 = r1.getOrElse(k, (Long.MaxValue, Long.MinValue, 0L))
        val newMin = if (v2._1 < v1._1) v2._1 else v1._1
        val newMax = if (v2._2 > v1._2) v2._2 else v1._2
        (newMin, newMax, v1._3 + v2._3)
      }
    }
    ss.asInstanceOf
  }




  override def zero(initialValue: mutable.HashMap[String, (Long, Long, Long)]):
                    mutable.HashMap[String, (Long, Long, Long)] = {
    new mutable.HashMap[String, (Long, Long, Long)]()
  }

}
