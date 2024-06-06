package org.easygame.view

import org.easygame.model.Material
import org.easygame.model.UserData
import org.easygame.model.Vector3f
import org.easygame.view.ModelView._

final case class GMV(
    id: String,
    mesh: Mesh,
    material: Material,
    onRenderLocalTranslation: Vector3f = Vector3f.Zero,
    localTranslation: Option[Vector3f] = None,
    onRenderLocalRotation: Vector3f = Vector3f.Zero,
    localRotation: Option[Vector3f] = None,
    onRenderLocalScale: Vector3f = Vector3f.One,
    localScale: Option[Vector3f] = None,
    rotate: Option[Vector3f] = None,
    override val parents: Seq[String] = Nil,
    userData: Seq[UserData[?]] = Nil
) extends ModelIdentifiable
    with SceneLocation
    with LocalRotation
    with LocalScale
    with Rotated
    with UserDataProvider
    with OnRenderSceneLocation
    with OnRenderLocalRotation
    with OnRenderLocalScale
