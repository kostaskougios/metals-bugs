package org.easygame.view

import org.easygame.model.UserData
import org.easygame.model.Vector3f
import org.easygame.view.ModelView._

final case class Node(
    id: String,
    localTranslation: Option[Vector3f] = None,
    localRotation: Option[Vector3f] = None,
    localScale: Option[Vector3f] = None,
    override val parents: Seq[String] = Nil,
    onRenderPaint: Option[Paint] = None,
    rotate: Option[Vector3f] = None,
    override val children: Seq[ModelIdentifiable] = Nil,
    userData: Seq[UserData[?]] = Nil
) extends ModelIdentifiable
    with SceneLocation
    with LocalRotation
    with LocalScale
    with OnRenderPaint
    with Rotated
    with UserDataProvider
