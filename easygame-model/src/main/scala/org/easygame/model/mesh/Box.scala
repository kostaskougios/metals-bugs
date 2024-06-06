package org.easygame.model.mesh

import org.easygame.model.Vector3f

case class Box(
    min: Vector3f = Vector3f.Zero,
    max: Vector3f = Vector3f.UnitXYZ
) extends Mesh
