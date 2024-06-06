package world.model

case class Movement(to: Coords, cost: Int):
  def toHumanReadable: String = s"${to.toHumanReadable} cost:$cost"
