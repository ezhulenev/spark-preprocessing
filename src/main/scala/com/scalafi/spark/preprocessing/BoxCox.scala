package com.scalafi.spark.preprocessing

import org.apache.spark.rdd.RDD


case class BoxCoxTransformation(
  lambda1: Double,
  lambda2: Double
)

object BoxCox {

  def estimate(rdd: RDD[Double], sampleSize: Int = 100000): BoxCoxTransformation = {
    require(rdd.count() > 0, "Required non-empty RDD")

    val sample = rdd.takeSample(withReplacement = false, num = sampleSize)

    ???
  }
}
