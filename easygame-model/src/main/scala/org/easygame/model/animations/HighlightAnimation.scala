package org.easygame.model.animations

import org.easygame.model.light.Light
import org.easygame.view.ModelIdentifiable
import org.easygame.view.ModelView.Lighted

class HighlightAnimation(val id: String, spatialId: String, light: Light, afterMs: Long = 0) extends SpatialAnimation:

  private val startTime = System.currentTimeMillis

  override def finished: Boolean = false

  override def run: Seq[ModelIdentifiable] =
    if System.currentTimeMillis > startTime + afterMs then Seq(MVLight(spatialId, light))
    else Nil

  override def onRemoved: Seq[ModelIdentifiable] = Nil

case class MVLight(id: String, light: Light) extends ModelIdentifiable with Lighted
