package org.apache.spark.preprocessing.statistics

import org.apache.spark.rdd.RDD

import scalaz.Monoid
import scalaz.syntax.monoid._

import scala.language.implicitConversions

trait SeriesStatistics extends Serializable {

  def minmax: MinMax

  def mean: Double

  def variance: Double

  def range: Double = {
    val mm = minmax
    mm.maximum - mm.minimum
  }

  def standardDeviation: Double = {
    Math.sqrt(variance)
  }

  protected def seqOp(m: MinMax, v: Double): MinMax = {
    val min = if (v < m.minimum) v else m.minimum
    val max = if (v > m.maximum) v else m.maximum
    // return new object only if min or max updated
    if (min != m.minimum || max != m.maximum) {
      MinMax(min, max)
    } else m
  }
}

object SeriesStatistics {

  def apply(rdd: RDD[Double]): SeriesStatistics =
    new FullSeriesStatistics(rdd)

  def apply(sample: Array[Double]): SeriesStatistics =
    new SampleSeriesStatistics(sample)

  implicit def fullSeriesStatistics(rdd: RDD[Double]): SeriesStatistics =
    new FullSeriesStatistics(rdd)

  implicit def sampleSeriesStatistics(sample: Array[Double]): SeriesStatistics =
    new SampleSeriesStatistics(sample)

}

private class FullSeriesStatistics(rdd: RDD[Double]) extends SeriesStatistics {

  require(rdd.count() > 0, "Required non-empty RDD")

  lazy val minmax: MinMax = {
    val M = implicitly[Monoid[MinMax]]
    rdd.aggregate[MinMax](M.zero)(seqOp, _ |+| _)
  }

  lazy val mean: Double = {
    (rdd.aggregate[BigDecimal](BigDecimal(0.0))((bd, d) => bd + BigDecimal(d), _ + _) / rdd.count()).toDouble
  }

  lazy val variance: Double = {
    val meanV = mean
    val deviations = rdd.map(v => math.pow(v - meanV, 2))
    deviations.reduce(_ + _) / deviations.count()
  }
}

private class SampleSeriesStatistics(sample: Array[Double]) extends SeriesStatistics {
  require(sample.length > 0, "Required non-empty sample")

  lazy val minmax: MinMax = {
    val M = implicitly[Monoid[MinMax]]
    sample.foldLeft(M.zero)(seqOp)
  }

  lazy val mean: Double = {
    sample.sum / sample.length
  }

  lazy val variance: Double = {
    val meanV = mean
    val deviations = sample.map(v => math.pow(v - meanV, 2))
    deviations.reduce(_ + _) / deviations.length
  }
}