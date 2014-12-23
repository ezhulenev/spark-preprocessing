package org.apache.spark.preprocessing.statistics

import scalaz.Monoid

case class MinMax(minimum: Double, maximum: Double) {

  def update(v: Double): MinMax = {
    val min = if (v < minimum) v else minimum
    val max = if (v > maximum) v else maximum
    MinMax(min, max)
  }

}

object MinMax {
  implicit val MinMaxMonoid: Monoid[MinMax] = Monoid.instance[MinMax](
    f = (l, r) => MinMax(math.min(l.minimum, r.minimum), math.max(l.maximum, r.maximum)),
    z = MinMax(Double.MaxValue, Double.MinValue)
  )
}