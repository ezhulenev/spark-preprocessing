package org.apache.spark.preprocessing.statistics

import org.apache.spark.preprocessing.TestSparkContext
import org.scalatest.FlatSpec

class SeriesStatisticsSpec extends FlatSpec with TestSparkContext {

  "Series Statistics" should "calculate min & max for RDD" in {
    val data = Seq(1.0, 2.0, 3.0, 4.0)
    val rdd = sc.parallelize(data)

    val minmax = SeriesStatistics(rdd).minmax

    assert(minmax.minimum == 1.0)
    assert(minmax.maximum == 4.0)
  }

  it should "calculate min & max for sample" in {
    val data = Seq(1.0, 2.0, 3.0, 4.0)
    val sample = data.toArray

    val minmax = SeriesStatistics(sample).minmax

    assert(minmax.minimum == 1.0)
    assert(minmax.maximum == 4.0)
  }
}
