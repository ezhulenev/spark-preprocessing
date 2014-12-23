package org.apache.spark.preprocessing.statistics

import org.apache.spark.rdd.RDD

object SeriesStatistics {

  def summary(rdd: RDD[Double]): SeriesStatisticalSummary = ???

}
