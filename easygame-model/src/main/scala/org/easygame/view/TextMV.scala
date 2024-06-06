package org.easygame.view

import org.easygame.model.Vector3f
import org.easygame.model.fonts.FontDef
import org.easygame.view.ModelView.LocalRotation
import org.easygame.view.ModelView.LocalScale
import org.easygame.view.ModelView.SceneLocation

final case class TextMV(
    id: String,
    text: String,
    fontName: String = FontDef.Default.name,
    size: Float = 1,
    override val parents: Seq[String] = Nil,
    localTranslation: Option[Vector3f] = None,
    localRotation: Option[Vector3f] = None,
    localScale: Option[Vector3f] = None
) extends TextView
    with SceneLocation
    with LocalRotation
    with LocalScale:
  def withText(t: String): TextMV = copy(text = t)
