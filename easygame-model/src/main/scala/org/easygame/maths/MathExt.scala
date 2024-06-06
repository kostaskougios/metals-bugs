package org.easygame.maths

object MathExt:
  val Pi                             = math.Pi.toFloat
  val HalfPi                         = Pi / 2
  def cos(th: Float): Float          = math.cos(th).toFloat
  def sin(th: Float): Float          = math.sin(th).toFloat
  def abs(f: Float): Float           = math.abs(f)
  def max(a: Int, b: Int): Int       = math.max(a, b)
  def max(a: Float, b: Float): Float = math.max(a, b)
  def min(a: Float, b: Float): Float = math.min(a, b)
  def sqrt(a: Float): Float          = math.sqrt(a).toFloat
