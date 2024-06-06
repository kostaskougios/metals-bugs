package org.easygame.model

case class NamedColor(
    name: String,
    color: ColorRGBA
)

object NamedColor:
  def color(color: ColorRGBA) = NamedColor("Color", color)

  def diffuse(color: ColorRGBA) = NamedColor("Diffuse", color)

  def specular(color: ColorRGBA) = NamedColor("Specular", color)
