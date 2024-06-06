package world.model

import game.model.Id
import org.easygame.maths.MathExt.Pi
import org.easygame.model.ColorRGBA
import org.easygame.model._

abstract class Troop extends Id[Int]:
  type Type <: Troop
  def id: Int
  def definition: TroopDef
  def coordinates: Coords
  def facing: Facing
  def color: ColorRGBA
  def attached: Seq[TroopAttachment]
  def attributes: Attributes

  def face(f: Facing): Type
  def facingFor(movements: Seq[Movement]): Type =
    val newFacing = if movements.sizeIs > 1 then
      val mr   = movements.reverse
      val from = mr.head.to.toXY
      val to   = mr.tail.head.to.toXY
      from.facingFor(to)
    else facing
    face(newFacing)

  def navigationFor(map: Map3D): NavigateResults =
    val maxDistance = attributes.maxDistance
    val n           = new Navigator(maxDistance, attributes.maxPassableHeight, costCalc, map)
    n.navigate(coordinates)

  /** @return
    *   The rotation vector for the current facing
    */
  def rotationVectorForFacing: Vector3f = Vector3f.NormalOnX.addZ(rotationFor)

  private def rotationFor: Float = facing match
    case Facing.E  => Pi / 2
    case Facing.SE => Pi / 4
    case Facing.SW => -Pi / 4
    case Facing.W  => -Pi / 2
    case Facing.NW => -Pi / 2 - Pi / 4
    case Facing.NE => Pi / 2 + Pi / 4

  private def costCalc(h: Hex): Int = h match
    case _: Grass => 1
    case _: Mud   => 2
    case _        => 1000000

object Troop:
  val SpatialKey = UserDataKey[Troop]("troop")
