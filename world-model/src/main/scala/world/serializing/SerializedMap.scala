package world.serializing

import world.model._

case class SerializedMap(maxX: Int, maxY: Int, hexes: Seq[SerializedHex])

case class SerializedHex(
    coords: Coords,
    hex: Hex
):
  def toTuple: (Coords, Hex) = (coords, hex)
