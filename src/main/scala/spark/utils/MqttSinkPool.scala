//package spark.utils
//
//import org.apache.commons.pool2.impl.{DefaultPooledObject, GenericObjectPool}
//import org.apache.commons.pool2.{BasePooledObjectFactory, PooledObject}
//import org.eclipse.paho.client.mqttv3.MqttClient
//import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence
//
///**
//  * Created by SCAL on 2017/1/26.
//  */
//object MqttSinkPool {
//  val poolSize = 8
//  val brokerUrl = ""
//  val clientPool = new GenericObjectPool[MqttClient](new MqttClientFactory(brokerUrl))
//  clientPool.setMaxTotal(poolSize)
//  sys.addShutdownHook{
//    clientPool.close()
//  }
//
//  def apply: GenericObjectPool[MqttClient] = {
//    clientPool
//  }
//
//}
//
//class MqttClientFactory(brokerUrl: String) extends BasePooledObjectFactory[MqttClient]{
//  override def create(): MqttClient = {
//    val client = new MqttClient(brokerUrl, MqttClient.generateClientId(), new MemoryPersistence())
//    client.connect()
//    client
//  }
//  override def wrap(t: MqttClient): PooledObject[MqttClient] = {
//    new DefaultPooledObject[MqttClient](t)
//  }
//  override def destroyObject(p: PooledObject[MqttClient]): Unit = {
//    p.getObject.disconnect()
//    p.getObject.close()
//  }
//
//  override def validateObject(p: PooledObject[MqttClient]): Boolean = p.getObject.isConnected
//
//  override def passivateObject(p: PooledObject[MqttClient]): Unit = {}
//
//
//
//
//}
