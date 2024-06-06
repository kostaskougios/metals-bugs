package world.model

import org.easygame.model.Vector3f

class Grid:
  private val dx = 2f * 3f / 4f + 0.2f
  private val dy = 2f

  def maxView: Int                       = 40
  def mapToWorldX(x: Int, y: Int): Float = x * dx + ((dx / 2) * (y % 2)) - maxView / 2
  def mapToWorldY(y: Int): Float         = y * (3f * dy) / 4 - maxView / 2 - 6
  def mapToWorldZ(z: Int): Float         = z.toFloat / 2f

  def mapToWorld(c: Coords): Vector3f = Vector3f(mapToWorldX(c.x, c.y), mapToWorldY(c.y), mapToWorldZ(c.z))

  def worldToMapX(drawX: Float): Int = (drawX / dx).toInt
  def worldToMapY(drawY: Float): Int = (4f * drawY / (3f * dy)).toInt

  def mapViewForCamera(location: Vector3f, maxView: Int): (Int, Int) =
    val l    = location.addXY(-14, -5)
    val posX = worldToMapX(l.x)
    val posY = worldToMapY(l.y)
    (posX + maxView / 2, posY + maxView / 2)

trait GridBeans:
  lazy val grid = new Grid
