package world.model

import org.easygame.maths.MathExt.HalfPi

case class Coords(x: Int, y: Int, z: Int):
  def toXY: CoordsXY  = CoordsXY(x, y)
  def downZ: Coords   = copy(z = z - 1)
  def upZ: Coords     = copy(z = z + 1)
  def toHumanReadable = s"($x,$y,$z)"

case class CoordsXY(x: Int, y: Int):
  def incX: CoordsXY       = CoordsXY(x + 1, y)
  def decX: CoordsXY       = CoordsXY(x - 1, y)
  def andZ(z: Int): Coords = Coords(x, y, z)

  /** @return
    *   all nearby coordinates for this hex
    */
  def nearby: NearBy =
    val cy = y % 2
    NearBy(
      CoordsXY(x - 1 + cy, y - 1),
      CoordsXY(x + cy, y - 1),
      CoordsXY(x - 1, y),
      CoordsXY(x + 1, y),
      CoordsXY(x - 1 + cy, y + 1),
      CoordsXY(x + cy, y + 1)
    )

  def facingFor(to: CoordsXY): Facing =
    val n = nearby
    if to == n.sw then Facing.SW
    else if to == n.se then Facing.SE
    else if to == n.e then Facing.E
    else if to == n.w then Facing.W
    else if to == n.ne then Facing.NE
    else if to == n.nw then Facing.NW
    else throw new IllegalArgumentException(s"not nearby, this=$this , to=$to")

case class NearBy(
    sw: CoordsXY,
    se: CoordsXY,
    w: CoordsXY,
    e: CoordsXY,
    nw: CoordsXY,
    ne: CoordsXY
):
  def toSeq: Seq[CoordsXY]         = Seq(nw, ne, w, e, sw, se)
  def rotation(c: CoordsXY): Float =
    if c == e then HalfPi
    else if c == w then -HalfPi
    else if c == se then HalfPi / 3
    else if c == sw then -HalfPi / 3
    else if c == ne then -HalfPi / 3
    else if c == nw then HalfPi / 3
    else 0f
