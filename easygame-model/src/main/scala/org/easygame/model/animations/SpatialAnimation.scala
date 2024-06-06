package org.easygame.model.animations

import org.easygame.view._

trait SpatialAnimation:
  def id: String
  def run: Seq[ModelIdentifiable]
  def onRemoved: Seq[ModelIdentifiable]
  def finished: Boolean
