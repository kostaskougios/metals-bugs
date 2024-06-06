package world.model

case class HexInfo(hex: Hex, mapX: Int, mapY: Int, mapZ: Int):
  def mapCoords: Coords = Coords(mapX, mapY, mapZ)
