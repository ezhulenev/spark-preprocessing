package com.scalafi.spark.preprocessing

import com.scalafi.spark.preprocessing.statistics.SeriesStatistics
import org.apache.spark.rdd.RDD

object SeriesTransformation {

  def standardize(rdd: RDD[Double]): RDD[Double] = {
    val stat = SeriesStatistics(rdd)
    val mean = stat.mean
    val sd = stat.standardDeviation

    if (sd == 0.0) {
      rdd.map(_ => 1.0)
    } else {
      rdd.map(v => (v - mean) / sd)
    }
  }
}
