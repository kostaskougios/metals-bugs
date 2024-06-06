package org.easygame.model.spatials

import org.easygame.model.Material

case class Model(
    name: String,
    path: String,
    material: Option[Material] = None
) extends Spatial
