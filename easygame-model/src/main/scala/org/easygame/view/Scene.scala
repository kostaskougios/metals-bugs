package org.easygame.view

import org.easygame.model.animations.SpatialAnimations
import org.easygame.model.light.Light

final case class Scene(
    id: String,
    lights: Seq[Light],
    objects: Seq[ModelIdentifiable],
    animations: SpatialAnimations = SpatialAnimations.Empty
)
