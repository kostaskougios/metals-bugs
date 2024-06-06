package world.model

import org.easygame.model.UserDataKey

sealed trait Hex:
  def spatialName: String
  def isSameTypeAs(h: Hex): Boolean
  def isWater: Boolean

case class Grass() extends Hex:
  override def spatialName                   = "grass"
  override def isSameTypeAs(h: Hex): Boolean = h match
    case _: Grass => true
    case _        => false

  override def isWater = false

case class Mud() extends Hex:
  override def spatialName                   = "mud"
  override def isSameTypeAs(h: Hex): Boolean = h match
    case _: Mud => true
    case _      => false
  override def isWater                       = false

case class Water() extends Hex:
  override def spatialName                   = "water"
  override def isSameTypeAs(h: Hex): Boolean = h match
    case _: Water => true
    case _        => false
  override def isWater                       = true

case object Undefined extends Hex:
  override def spatialName                   = "undefined"
  override def isSameTypeAs(h: Hex): Boolean = h eq this
  override def isWater                       = false

case object WorldEdge extends Hex:
  override def spatialName                   = "world-edge"
  override def isSameTypeAs(h: Hex): Boolean = h eq this
  override def isWater                       = false

object Hex:
  val SpatialKey = UserDataKey[HexInfo]("hex")
