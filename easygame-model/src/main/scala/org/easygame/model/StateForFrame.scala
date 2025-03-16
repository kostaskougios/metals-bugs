package org.easygame.model

import monocle.syntax.all.*
import org.easygame.view.ViewPort

case class StateForFrame[S](
    state: S,
    viewPorts: Seq[ViewPort] = Nil
):
  def changeState(s: S) = this.focus(_.state).replace(s)
