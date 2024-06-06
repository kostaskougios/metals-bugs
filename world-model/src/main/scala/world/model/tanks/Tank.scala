package world.model.tanks

import org.easygame.model.ColorRGBA
import world.model.Attributes
import world.model.Coords
import world.model.Facing
import world.model.Troop

case class Tank(
    id: Int,
    definition: TankDef,
    coordinates: Coords,
    facing: Facing = Facing.E,
    turrets: Map[TurretSlotDef, Turret] = Map.empty,
    color: ColorRGBA = ColorRGBA(0.0662331, 0.13361, 0.0760641, 1),
    attributes: Attributes = Attributes.Default
) extends Troop:
  override type Type = Tank
  override def face(f: Facing) = copy(facing = f)

  def withTurret(slot: TurretSlotDef, turret: Turret): Tank  =
    if !definition.turretSlots.contains(slot) then throw new IllegalArgumentException(s"$slot not part of tanks slots: ${definition}")
    withTurrets(turrets + (slot -> turret))
  def withTurrets(turrets: Map[TurretSlotDef, Turret]): Tank = copy(turrets = turrets)
  override def attached                                      = turrets.values.toSeq
