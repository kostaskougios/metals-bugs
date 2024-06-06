package org.easygame.model

case class Texture(name: String, path: String)

object Texture:
  def colorMap(path: String) = Texture("ColorMap", path)

  def diffuseMap(path: String) = Texture("DiffuseMap", path)

  def normalMap(path: String) = Texture("NormalMap", path)
