package org.easygame.view

import org.easygame.model.Vector3f
import org.easygame.view.ModelView.OnRenderSceneLocation
import org.easygame.view.ModelView.SceneLocation

final case class SMV(
    id: String,
    sound: SoundDef,
    override val parents: Seq[String] = Nil,
    onRenderLocalTranslation: Vector3f = Vector3f.Zero,
    localTranslation: Option[Vector3f] = None,
    positional: Boolean = false,
    looping: Boolean = false,
    volume: Float = 5
) extends ModelIdentifiable
    with SceneLocation
    with OnRenderSceneLocation
