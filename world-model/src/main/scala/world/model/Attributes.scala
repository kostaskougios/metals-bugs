package world.model

final case class Attributes(
    maxDistance: Int,
    maxPassableHeight: Int
)

object Attributes:
  val Default = Attributes(10, 1)
