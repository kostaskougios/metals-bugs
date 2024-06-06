package org.easygame.model.animations

import org.easygame.maths.MathExt.cos
import org.easygame.model.ColorRGBA
import org.easygame.view._

class ColorAnimation(
    val id: String,
    spatialIds: Seq[String],
    defaultColor: ColorRGBA,
    materialNames: Set[String],
    startTime: Long = System.currentTimeMillis()
) extends SpatialAnimation:

  override def finished: Boolean = false

  override def run       =
    val dt  = System.currentTimeMillis() - startTime
    val mvs = spatialIds.map: spatialId =>
      val dColour = cos(dt.toFloat / 200)
      val colour  = defaultColor.mult(dColour)
      MVColorAnimation(spatialId, Paint(colour, materialNames))
    mvs
  override def onRemoved =
    spatialIds.map: spatialId =>
      MVColorAnimation(spatialId, Paint(defaultColor, materialNames))

case class MVColorAnimation(
    id: String,
    paint: Paint
) extends ModelView.Painted
    with ModelIdentifiable
