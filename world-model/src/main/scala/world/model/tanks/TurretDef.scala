package world.model.tanks

import world.model.AttachmentDef

sealed trait TurretDef extends AttachmentDef:
  override def modelName: String
  override def materialNamesToRepaint: Set[String]

case object VickersMk7TurretDef extends TurretDef:
  override def modelName: String                   = "vickers-mk7-turret"
  override def materialNamesToRepaint: Set[String] = Set("Material_1", "Material_4")

case object Type80TurretDef extends TurretDef:
  override def modelName: String                   = "type-80-turret"
  override def materialNamesToRepaint: Set[String] = Set("material_4")
