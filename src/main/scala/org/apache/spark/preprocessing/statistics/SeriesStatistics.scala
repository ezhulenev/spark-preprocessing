package org.apache.spark.preprocessing.statistics

import org.apache.spark.rdd.RDD

import scalaz.Monoid
import scalaz.syntax.monoid._

object SeriesStatistics {

  def apply(rdd: RDD[Double]): SeriesStatistics =
    new SeriesStatistics(rdd)

}

class SeriesStatistics(rdd: RDD[Double]) extends Serializable {

  require(rdd.count() > 0, "Required non-empty RDD")

  def minmax(): MinMax = {
    val M = implicitly[Monoid[MinMax]]

    def seqOp(m: MinMax, v: Double): MinMax = {
      val min = if (v < m.minimum) v else m.minimum
      val max = if (v > m.maximum) v else m.maximum
      // return new object only if min or max updated
      if (min != m.minimum || max != m.maximum) {
        MinMax(min, max)
      } else m
    }

    rdd.aggregate[MinMax](M.zero)(seqOp, _ |+| _)
  }

  def summary(rdd: RDD[Double]): SeriesStatisticalSummary = ???

}
