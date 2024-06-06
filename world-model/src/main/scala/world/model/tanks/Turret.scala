package world.model.tanks

import org.easygame.model.Vector3f
import world.model._

case class Turret(id: Int, definition: TurretDef, rotation: Vector3f = -Vector3f.NormalOnX) extends TroopAttachment
