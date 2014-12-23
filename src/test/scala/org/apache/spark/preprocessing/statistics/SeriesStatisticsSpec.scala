package org.apache.spark.preprocessing.statistics

import org.apache.spark.preprocessing.TestSparkContext
import org.scalatest.FlatSpec

class SeriesStatisticsSpec extends FlatSpec with TestSparkContext {

  "Series Statistics" should "calculate min & max for series" in {
    val data = Seq(1.0, 2.0, 3.0, 4.0)

    val statistics = SeriesStatistics(sc.parallelize(data))
    val minmax = statistics.minmax()

    assert(minmax.minimum == 1.0)
    assert(minmax.maximum == 4.0)
  }
}
