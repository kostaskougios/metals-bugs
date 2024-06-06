package world.model

trait AttachmentDef:
  def modelName: String
  def materialNamesToRepaint: Set[String]
