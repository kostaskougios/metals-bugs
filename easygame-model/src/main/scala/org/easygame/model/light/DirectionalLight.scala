package org.easygame.model.light

import org.easygame.model.*

case class DirectionalLight(
    id: String,
    color: ColorRGBA,
    direction: Vector3f
) extends Light
