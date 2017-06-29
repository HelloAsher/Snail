package main.scala.scalaAPI

/**
  * Created by Asher on 2017/2/18.
  */
object ScalaAPITest {
  def main(args: Array[String]): Unit = {
    val ss = Map("hh" -> "sss", "kk" -> "mmm")
    val sse = Map("hh" -> "sss", "kk" -> "mmm")
    val gg = ss.map{
      case (k, v) =>{
        sse.foreach(fun2)
        (k, v + sse.getOrElse(k, ("jj", "ss")))
      }
    }
  }
  def fun(v: (String, String)):(String, String) ={
    println(v._1 + "----" + v._2)
    ("ppp", "000")
  }
  def fun2(v: (String, String)):Unit ={
    println(v._1 + "--4444--" + v._2)
  }

}
