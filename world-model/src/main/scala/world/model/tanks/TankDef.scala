package world.model.tanks

import world.model.TroopDef

sealed trait TankDef extends TroopDef:
  def turretSlots: Seq[TurretSlotDef]
  def firstTurretSlot: TurretSlotDef = turretSlots.head
  override def modelName: String
  override def materialNamesToRepaint: Set[String]

case object VickersMk7 extends TankDef:
  override def turretSlots: Seq[TurretSlotDef] =
    Seq(
      TurretSlotDef("turret", "on chassis")
    )
  override def modelName: String               = "vickers-mk7-chassis"

  override def materialNamesToRepaint: Set[String] = Set("Material_1", "Material_3")

case object Type80 extends TankDef:
  override def turretSlots: Seq[TurretSlotDef]     =
    Seq(
      TurretSlotDef("turret", "on chassis")
    )
  override def modelName: String                   = "type-80-chassis"
  override def materialNamesToRepaint: Set[String] = Set("material_4")

case object Ozelot extends TankDef:
  override def turretSlots: Seq[TurretSlotDef] = Seq(
    TurretSlotDef("turret", "on chassis")
  )
  override def modelName: String               = "ozelot-chassis"

  override def materialNamesToRepaint: Set[String] = Set("Material_4")
