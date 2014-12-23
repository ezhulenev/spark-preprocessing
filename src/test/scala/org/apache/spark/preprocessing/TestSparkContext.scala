package org.apache.spark.preprocessing

import org.apache.spark.{SparkConf, SparkContext}
import org.scalatest.{Suite, BeforeAndAfterAll}
import org.slf4j.LoggerFactory

trait TestSparkContext extends BeforeAndAfterAll { self: Suite =>

  private val log = LoggerFactory.getLogger(classOf[TestSparkContext])

  @transient var sc: SparkContext = _

  override def beforeAll() {
    super.beforeAll()
    val master = "local[2]"
    val appName = "SparkPreProcessing"

    log.info(s"Create spark context. Master: $master. App Name: $appName")
    val conf = new SparkConf().
      setMaster(master).
      setAppName(appName)

    sc = new SparkContext(conf)
  }

  override def afterAll() {
    if (sc != null) {
      sc.stop()
    }
    super.afterAll()
  }
}