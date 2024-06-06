package org.easygame.model

case class DisplayMode(width: Int, height: Int, bitDepth: Int, refreshRate: Int, isRetina: Boolean):
  def aspectRatio: Float = width.toFloat / height.toFloat
