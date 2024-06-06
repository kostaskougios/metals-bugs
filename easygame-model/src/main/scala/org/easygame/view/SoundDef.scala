package org.easygame.view

case class SoundDef(
    path: String,
    dataType: DataType = DataType.Stream
)
enum DataType:
  case Buffer // buffer the whole sound file into memory
  case Stream // stream the file from disk while playing
