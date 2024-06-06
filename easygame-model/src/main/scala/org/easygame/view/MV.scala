package org.easygame.view

import org.easygame.model.UserData
import org.easygame.model.Vector3f
import org.easygame.view.ModelView._

case class MV(
    id: String,
    modelName: String,
    onRenderLocalTranslation: Vector3f = Vector3f.Zero,
    localTranslation: Option[Vector3f] = None,
    onRenderLocalRotation: Vector3f = Vector3f.Zero,
    localRotation: Option[Vector3f] = None,
    onRenderLocalScale: Vector3f = Vector3f.One,
    localScale: Option[Vector3f] = None,
    override val parents: Seq[String] = Nil,
    onRenderPaint: Option[Paint] = None,
    animationName: Option[String] = None,
    rotate: Option[Vector3f] = None,
    userData: Seq[UserData[?]] = Nil
) extends ModelView
    with SceneLocation
    with OnRenderSceneLocation
    with LocalRotation
    with OnRenderLocalRotation
    with LocalScale
    with OnRenderLocalScale
    with OnRenderPaint
    with Animated
    with Rotated
    with UserDataProvider:
  def scale(xyz: Float): MV                    = copy(localScale = Some(Vector3f(xyz, xyz, xyz)))
  def rotate(x: Float, y: Float, z: Float): MV = copy(rotate = Some(Vector3f(x, y, z)))
