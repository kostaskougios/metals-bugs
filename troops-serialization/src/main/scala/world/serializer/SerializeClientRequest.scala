package world.serializer

import troops.serverclient.model.ClientRequest

import AvroTroop.given

class SerializeClientRequest extends AvroBinarySerDes[ClientRequest]

trait SerializeClientRequestBeans:
  lazy val serializeClientRequest = new SerializeClientRequest
