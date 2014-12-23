package org.apache.spark.preprocessing

import org.apache.spark.{SparkConf, SparkContext}
import org.slf4j.LoggerFactory

trait TestSparkContext {
  private val log = LoggerFactory.getLogger(classOf[TestSparkContext])

  private lazy val sparkConf = {
    val master = "local[2]"
    val appName = "SparkPreProcessing"

    log.info(s"Create spark context. Master: $master. App Name: $appName")
    new SparkConf().
      setMaster(master).
      setAppName(appName)
  }

  implicit lazy val sc = new SparkContext(sparkConf)
}