package org.apache.spark.preprocessing.statistics


case class SeriesStatisticalSummary(
  range: Double,
  minimum: Double,
  maximum: Double,
  mean: Double,
  median: Double,
  standardDeviation: Double,
  variance: Double,
  momentSkewness: Double,
  medianSkewness: Double,
  quartileSkewness: Double,
  excessKurtosis: Double
)