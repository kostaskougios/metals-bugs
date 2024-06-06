package org.easygame.model

import org.easygame.maths.MathExt._

import scala.math

case class Vector3f(x: Float, y: Float, z: Float):
  def normalizeLocal: Vector3f =
    val length = x * x + y * y + z * z
    if (length != 1f && length != 0f)
      val l = 1.0f / math.sqrt(length).toFloat
      Vector3f(x * l, y * l, z * l)
    else this
  def normalize: Vector3f      =
    val length = sqrt(x * x + y * y + z * z)
    Vector3f(x / length, y / length, z / length)

  def withX(x: Float): Vector3f                      = copy(x = x)
  def withY(y: Float): Vector3f                      = copy(y = y)
  def withZ(z: Float): Vector3f                      = copy(z = z)
  def addX(dx: Float): Vector3f                      = copy(x = x + dx)
  def addY(dy: Float): Vector3f                      = copy(y = y + dy)
  def addXZ(dx: Float, dz: Float): Vector3f          = copy(x = x + dx, z = z + dz)
  def addXY(dx: Float, dy: Float): Vector3f          = copy(x = x + dx, y = y + dy)
  def add(dx: Float, dy: Float, dz: Float): Vector3f = copy(x = x + dx, y = y + dy, z = z + dz)
  def addZ(dz: Float): Vector3f                      = copy(z = z + dz)
  def unary_- : Vector3f                             = Vector3f(-x, -y, -z)
  def +(other: Vector3f): Vector3f                   = Vector3f(x + other.x, y + other.y, z + other.z)
  def -(other: Vector3f): Vector3f                   = Vector3f(x - other.x, y - other.y, z - other.z)
  def *(scalar: Float): Vector3f                     = Vector3f(x * scalar, y * scalar, z * scalar)

  /** Is this vector close to v (all coords less that d far away)
    */
  def isCloseTo(v: Vector3f, d: Float): Boolean = abs(v.x - x) <= d && abs(v.y - y) <= d && abs(v.z - z) <= d

object Vector3f:
  val Zero      = Vector3f(0, 0, 0)
  val NormalOnX = Vector3f(Pi / 2, 0, 0)
  val UnitXYZ   = Vector3f(1, 1, 1)
  val One       = UnitXYZ
  val Two       = Vector3f(2, 2, 2)
  val UnitX     = Vector3f(1, 0, 0)
  val UnitY     = Vector3f(0, 1, 0)
  val UnitZ     = Vector3f(0, 0, 1)

  extension (t: (Float, Float, Float))
    def toVector: Vector3f             = Vector3f(t._1, t._2, t._3)
    def toVectorOption: Some[Vector3f] = Some(Vector3f(t._1, t._2, t._3))
