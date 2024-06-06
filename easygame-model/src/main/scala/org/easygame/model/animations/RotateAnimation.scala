package org.easygame.model.animations

import org.easygame.maths.MathExt.abs
import org.easygame.model.Vector3f
import org.easygame.view.ModelIdentifiable
import org.easygame.view.ModelView.LocalRotation

class RotateAnimation(
    val id: String,
    spatialId: String,
    startRotation: Vector3f,
    endRotation: Vector3f,
    durationInMs: Long,
    timeProvider: () => Long = () => System.currentTimeMillis()
) extends SpatialAnimation:
  private val startTime = timeProvider()

  override def onRemoved: Seq[ModelIdentifiable] = Seq(MVRotate(spatialId, Some(endRotation)))
  override def run: Seq[ModelIdentifiable]       = Seq(
    MVRotate(spatialId, Some(calcRotation))
  )

  private def calcRotation =
    val now = timeProvider()
    if now > startTime + durationInMs then endRotation
    else
      val d  = (now - startTime).toFloat / durationInMs.toFloat
      val dz = calcD(d, startRotation.z, endRotation.z)
      startRotation.addZ(dz)

  private def calcD(d: Float, s: Float, e: Float) =
    println(s"from $s to $e")
    -d * (abs(s) + abs(e))
  override def finished: Boolean                  =
    val now = timeProvider()
    now > startTime + durationInMs

case class MVRotate(id: String, localRotation: Option[Vector3f]) extends ModelIdentifiable with LocalRotation
