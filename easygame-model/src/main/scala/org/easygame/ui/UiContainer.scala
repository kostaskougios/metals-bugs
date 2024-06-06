package org.easygame.ui

import org.easygame.model.Vector3f
import org.easygame.view.ModelIdentifiable

final case class UiContainer(
    id: String,
    localTranslation: Option[Vector3f] = None,
    localRotation: Option[Vector3f] = None,
    localScale: Option[Vector3f] = None,
    override val children: Seq[ModelIdentifiable] = Nil
) extends UiComponent
