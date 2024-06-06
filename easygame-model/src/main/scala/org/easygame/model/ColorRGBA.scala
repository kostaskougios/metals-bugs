package org.easygame.model

case class ColorRGBA(r: Float, g: Float, b: Float, a: Float):
  def mult(f: Float): ColorRGBA = ColorRGBA(r * f, g * f, b * f, a)

object ColorRGBA:
  val White    = ColorRGBA(1f, 1f, 1f, 1f)
  val Yellow   = ColorRGBA(1f, 1f, 0f, 1f)
  val Blue     = ColorRGBA(0f, 0f, 1f, 1f)
  val Red      = ColorRGBA(1f, 0f, 0f, 1f)
  val Green    = ColorRGBA(0f, 1f, 0f, 1f)
  val Chaki    = ColorRGBA(0.0662331, 0.13361, 0.0760641, 1)
  val Military = ColorRGBA(62f / 255f, 57f / 255f, 41f / 255f, 1)
