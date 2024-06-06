package org.easygame.view

import org.easygame.model.UserData
import org.easygame.model.Vector3f
import org.easygame.model.light.Light

trait ModelView extends ModelIdentifiable:
  def modelName: String
  def toIdAndModelNameTuple: (String, String) = (id, modelName)

object ModelView:
  trait SceneLocation:
    /** local translation set on every frame
      */
    def localTranslation: Option[Vector3f]

  trait OnRenderSceneLocation:
    /** when the object is rendered for the first time, it set's it's local translation.
      */
    def onRenderLocalTranslation: Vector3f

  /** @param xAngle
    *   the X angle (in radians)
    * @param yAngle
    *   the Y angle (in radians)
    * @param zAngle
    *   the Z angle (in radians)
    */
  trait LocalRotation:
    def localRotation: Option[Vector3f]

  trait OnRenderLocalRotation:
    /** when the object is rendered for the first time, it set's it's local rotation.
      */
    def onRenderLocalRotation: Vector3f

  trait LocalScale:
    def localScale: Option[Vector3f]

  trait OnRenderLocalScale:
    /** when the object is rendered for the first time, it set's it's local scale.
      */
    def onRenderLocalScale: Vector3f

  trait Painted:
    def paint: Paint

  trait Lighted:
    def light: Light

  /** paints the model only when it is rendered for the first time
    */
  trait OnRenderPaint:
    def onRenderPaint: Option[Paint]

  trait Animated:
    def animationName: Option[String]

  trait Rotated:
    def rotate: Option[Vector3f]

  trait UserDataProvider:
    def userData: Seq[UserData[?]]
