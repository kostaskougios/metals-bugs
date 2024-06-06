package org.easygame.view
import org.easygame.model.Camera

final case class ViewPort(
    id: String,
    camera: Camera,
    scene: Scene,
    color: Boolean = true,
    depth: Boolean = true,
    stencil: Boolean = true
)
