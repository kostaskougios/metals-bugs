package world.serializer

import world.serializing.GameSerDes

import AvroMap3D.given
import AvroTroop.given

class SerializeGameSerDes extends AvroDataSerDes[GameSerDes]

trait SerializeGameSerDesBeans:
  lazy val serializeGameSerDes = new SerializeGameSerDes
