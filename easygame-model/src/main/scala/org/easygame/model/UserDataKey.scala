package org.easygame.model

final case class UserDataKey[A](key: String):
  def value(v: A): UserData[A] = UserData(this, v)
