package com.scalafi.spark.preprocessing.statistics

import scalaz.Monoid

case class MinMax(
  minimum: Double,
  maximum: Double
)

object MinMax {

  implicit val MinMaxMonoid: Monoid[MinMax] =
    Monoid.instance[MinMax](
      f = (l, r) => MinMax(math.min(l.minimum, r.minimum), math.max(l.maximum, r.maximum)),
      z = MinMax(Double.MaxValue, Double.MinValue)
    )

}