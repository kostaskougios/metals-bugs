package world.model

trait TroopDef:
  def modelName: String
  def materialNamesToRepaint: Set[String]
