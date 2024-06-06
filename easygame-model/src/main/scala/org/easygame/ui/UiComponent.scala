package org.easygame.ui

import org.easygame.model.UserDataKey
import org.easygame.view.ModelIdentifiable
import org.easygame.view.ModelView._

trait UiComponent extends ModelIdentifiable with SceneLocation with LocalRotation with LocalScale

object UiComponent:
  val Key = UserDataKey[UiComponent]("ui-component")
