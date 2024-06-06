package org.easygame.model

import org.easygame.view.ViewPort

case class StateForFrame[S](
    state: S,
    viewPorts: Seq[ViewPort] = Nil
)
