package main.scala.spark.sparkstreaming.custom

import java.util.{Timer, TimerTask}

import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.{CloseableHttpClient, HttpClients}
import org.apache.http.util.EntityUtils
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.dstream.ReceiverInputDStream
import org.apache.spark.streaming.receiver.Receiver

/**
  * Created by SCAL on 2017/1/20.
  */
class HttpInputDStream(@transient ssc_ : StreamingContext,
                       storageLevel : StorageLevel,
                       url :String,
                       interval : Long) extends ReceiverInputDStream[String](ssc_) {
  override def getReceiver(): Receiver[String] = {
    new HttpReceiver(storageLevel, url, interval)
  }
}

class HttpReceiver(storageLevel : StorageLevel,
                   url :String,
                   interval : Long) extends Receiver[String](storageLevel) {
  var httpClient : CloseableHttpClient = _
  var trigger : Timer = _


  override def onStart(): Unit = {
    httpClient = HttpClients.createDefault()
    trigger = new Timer()
    trigger.scheduleAtFixedRate(new TimerTask {
      override def run() = doGet()
    }, 0, interval * 1000)
    System.out.println("init receiver!")
  }

  override def onStop(): Unit = {
    if(httpClient != null){
      httpClient.close()
      System.out.println("disconnect httpclient!")
    }
  }

  def doGet() {
    val response = httpClient.execute(new HttpGet(url))
    try {
      val content = EntityUtils.toString(response.getEntity)
      store(content)
    } catch {
      case e : Exception => restart("error while connecting!", e)
    } finally {
      response.close()
    }
  }
}
