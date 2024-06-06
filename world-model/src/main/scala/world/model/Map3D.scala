package world.model

class Map3D(val maxX: Int, val maxY: Int, private val m: Map[CoordsXY, Map[Int, Hex]]):
  def fill(x1: Int, y1: Int, x2: Int, y2: Int, z: Int, hex: Hex): Map3D =
    val nm = (x1 to x2).foldLeft(m): (nm, x) =>
      (y1 to y2).foldLeft(nm): (nnm, y) =>
        val c  = CoordsXY(x, y)
        val im = nnm.getOrElse(c, Map.empty) + (z -> hex)
        nnm + (c -> im)
    new Map3D(maxX, maxY, nm)

  def ++(cs: Seq[(Coords, Hex)]): Map3D =
    cs.foldLeft(this): (m, c) =>
      m + (c._1, c._2)

  def +(coords: Coords, hex: Hex): Map3D         =
    val xy = coords.toXY
    val zm = m.getOrElse(xy, Map.empty)
    new Map3D(maxX, maxY, m + (xy -> (zm + (coords.z -> hex))))
  def +(x: Int, y: Int, z: Int, hex: Hex): Map3D = this + (Coords(x, y, z), hex)

  def apply(coords: CoordsXY): Map[Int, Hex] = m.getOrElse(coords, Map.empty)

  def apply(coords: Coords): Hex =
    val h = for
      zm <- m.get(coords.toXY)
      h  <- zm.get(coords.z)
    yield h
    h.getOrElse(Undefined)

  def apply(x: Int, y: Int, z: Int): Hex   = apply(Coords(x, y, z))
  def apply(x: Int, y: Int): Map[Int, Hex] = apply(CoordsXY(x, y))

  /** The top most z coordinate on the specific coords
    */
  def maxZAt(coords: CoordsXY): Int       = m(coords).keys.max
  def topMostHexAt(coords: CoordsXY): Hex = apply(coords)(maxZAt(coords))
  def hasZAt(coords: CoordsXY): Boolean   = m.contains(coords) && m(coords).nonEmpty

  def minZ: Int = m.values.flatMap(_.keys).min
  def maxZ: Int = m.values.flatMap(_.keys).max

  override def hashCode(): Int           = m.hashCode()
  override def equals(obj: Any): Boolean =
    obj match
      case map: Map3D => map.m == m
      case _          => false

  override def toString                   = s"Map3D(num of hexes = ${m.size})"
  def toMap: Map[CoordsXY, Map[Int, Hex]] = m

  def toAsciiXYMapString: String =
    val map = (0 to maxY).map: y =>
      val line = (0 to maxX).map: x =>
        val coords = CoordsXY(x, y)
        if hasZAt(coords) then
          val z = maxZAt(coords)
          val h = apply(x, y, z)
          h.getClass.getSimpleName().charAt(0)
        else '-'
      line.mkString
    map.mkString("\n")
  def isPassable(c: Coords)      = apply(c) != Undefined && apply(c.upZ) == Undefined

object Map3D:
  def empty(maxX: Int, maxY: Int): Map3D                        = new Map3D(maxX, maxY, Map.empty)
  def apply(maxX: Int, maxY: Int, s: Seq[(Coords, Hex)]): Map3D =
    val m = s
      .groupBy(_._1.toXY)
      .map:
        case (xy, seq) =>
          (
            xy,
            seq
              .map: (coords, hex) =>
                (coords.z, hex)
              .toMap
          )

    new Map3D(maxX, maxY, m)
