package org.easygame.model.input

import org.easygame.model.Vector2f

case class MousePosition(x: Float, y: Float, dx: Float, dy: Float, tpf: Float) extends Action:
  override def name: String = "mouse-position"

  def toVector2f: Vector2f = Vector2f(x, y)

object MousePosition:
  val Zero = MousePosition(0f, 0f, 0f, 0f, 0f)
