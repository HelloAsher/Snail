package main.scala.spark.utils

import java.sql.DriverManager

import com.mysql.jdbc.Connection
import org.apache.commons.pool2.impl.{DefaultPooledObject, GenericObjectPool}
import org.apache.commons.pool2.{BasePooledObjectFactory, PooledObject}

/**
  * Created by SCAL on 2017/1/26.
  */
object MySQLSinkPool {
  val poolSize = 8
  val JDBCUrl = ""
  val connectionPool = new GenericObjectPool[Connection](new MySQLClientFactory(JDBCUrl))
  connectionPool.setMaxTotal(poolSize)
  sys.addShutdownHook {
    connectionPool.close()
  }


  def apply(): GenericObjectPool[Connection] = {
    connectionPool
  }
}

class MySQLClientFactory(JDBCUrl: String) extends BasePooledObjectFactory[Connection] {
  override def create(): Connection = {
    val connection = DriverManager.getConnection(JDBCUrl)
    connection.asInstanceOf[Connection]
  }

  override def wrap(t: Connection): PooledObject[Connection] = new DefaultPooledObject[Connection](t)

  override def destroyObject(p: PooledObject[Connection]): Unit = {
    p.getObject.close()
  }

  override def validateObject(p: PooledObject[Connection]): Boolean = !p.getObject.isClosed

  override def passivateObject(p: PooledObject[Connection]): Unit = {}
}
