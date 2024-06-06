package org.easygame.model

import org.easygame.maths.MathExt._

trait Camera:
  def name: String
  def width: Int
  def height: Int

case class NormalCamera(
    name: String,
    width: Int,
    height: Int,
    frustumPerspective: FrustumPerspective,
    location: Vector3f,
    lookAt: LookAt
) extends Camera

case class ParallelProjectionCamera(
    name: String,
    width: Int,
    height: Int,
    frustum: Frustum
) extends Camera

case class Frustum(near: Float, far: Float, left: Float, right: Float, top: Float, bottom: Float)

case class FrustumPerspective(
    fovY: Float,
    aspect: Float,
    near: Float,
    far: Float
)

case class LookAt(pos: Vector3f, worldUpVector: Vector3f)
object LookAt:
  def byAngle(x0: Float, y0: Float, z0: Float, r: Float, th: Float, worldUpVector: Vector3f = Vector3f.UnitZ): LookAt =
    val pos = Vector3f(
      x0 + r * cos(th),
      y0 + r * sin(th),
      0
    )
    LookAt(pos, worldUpVector)

object Camera:
  def standard(
      name: String,
      width: Int,
      height: Int,
      location: Vector3f = Vector3f(0f, 0f, 10f),
      lookAt: LookAt = LookAt(Vector3f(0f, 0f, 0f), Vector3f.UnitZ)
  ) =
    NormalCamera(
      name,
      width,
      height,
      FrustumPerspective(45f, width.toFloat / height, 1f, 1000f),
      location,
      lookAt
    )
