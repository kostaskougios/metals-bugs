package org.easygame.ui

import org.easygame.model.ColorRGBA
import org.easygame.model.Material
import org.easygame.model.UserData
import org.easygame.model.Vector3f
import org.easygame.view.Font3D
import org.easygame.view.GMV
import org.easygame.view.Quad

final case class Button(
    id: String,
    font: Font3D,
    text: String,
    localTranslation: Option[Vector3f] = None,
    localRotation: Option[Vector3f] = None,
    localScale: Option[Vector3f] = None,
    width: Float = 1f,
    height: Float = 1f,
    backgroundMaterial: Material = Material.unshaded(ColorRGBA.Chaki),
    backgroundMargin: Float = 0.1f
) extends UiComponent:
  override def children =
    val bm = backgroundMargin * 2
    val ls = localScale.map(_ * 2).getOrElse(Vector3f.One)

    font(id + ".text", text) :+ GMV(
      id + ".box",
      Quad((width + bm) * ls.x, (height + bm) * ls.y),
      backgroundMaterial,
      onRenderLocalTranslation = Vector3f.Zero.addXZ((width / 2 - backgroundMargin - 0.2f) * ls.x, -0.2f),
      userData = Seq(UserData(UiComponent.Key, this))
    )
