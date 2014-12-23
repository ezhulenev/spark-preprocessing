package org.apache.spark.preprocessing.statistics

import org.apache.spark.rdd.RDD

import scalaz.Monoid
import scalaz.syntax.monoid._

object SeriesStatistics {

  def minmax(rdd: RDD[Double]): MinMax = {
    require(rdd.count() > 0, "Required non-empty RDD")
    val M = implicitly[Monoid[MinMax]]
    rdd.aggregate[MinMax](M.zero)((_:MinMax).update(_), _ |+| _)
  }

  def summary(rdd: RDD[Double]): SeriesStatisticalSummary = ???

}
