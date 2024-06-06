package org.easygame.view

import org.easygame.model.ColorRGBA

final case class Paint(
    color: ColorRGBA,
    materialNames: Set[String]
)
