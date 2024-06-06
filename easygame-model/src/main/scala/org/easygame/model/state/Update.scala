package org.easygame.model.state

import org.easygame.model.input.Action
import org.easygame.view.ViewPort

case class Update[S](
    state: S,
    actions: Seq[Action],
    tpf: Float,
    viewPorts: Seq[ViewPort]
):
  def findViewPort(id: String): Option[ViewPort] = viewPorts.find(_.id == id)
