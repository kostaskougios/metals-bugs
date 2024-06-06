package org.easygame.model.fonts

case class FontDef(name: String, fntFile: String)

object FontDef:
  val Default = FontDef("default", "Interface/Fonts/Default.fnt")
