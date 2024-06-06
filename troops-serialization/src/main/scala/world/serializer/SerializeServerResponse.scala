package world.serializer

import com.sksamuel.avro4s._
import org.apache.avro.Schema
import troops.serverclient.model.ServerResponse
import world.model.tanks.Turret
import world.model.tanks.TurretSlotDef

import AvroMap3D.given
import AvroTroop.given

class SerializeServerResponse extends AvroBinarySerDes[ServerResponse]

trait SerializeServerResponseBeans:
  lazy val serializeServerResponse = new SerializeServerResponse

class MapSchema[K: SchemaFor, V: SchemaFor] extends SchemaFor[Map[K, V]]:
  override val schema: Schema = SchemaFor[Seq[(K, V)]].schema

class MapEncoder[K: Encoder, V: Encoder] extends Encoder[Map[K, V]]:
  private val enc                                       = implicitly[Encoder[Seq[(K, V)]]]
  override def encode(schema: Schema): Map[K, V] => Any = m => enc.encode(schema)(m.toSeq)

class MapDecoder[K: Decoder, V: Decoder] extends Decoder[Map[K, V]]:
  private val dec                                       = implicitly[Decoder[Seq[(K, V)]]]
  override def decode(schema: Schema): Any => Map[K, V] = s => dec.decode(schema)(s).toMap

given SchemaFor[Map[TurretSlotDef, Turret]] = new MapSchema[TurretSlotDef, Turret]
given Encoder[Map[TurretSlotDef, Turret]]   = new MapEncoder[TurretSlotDef, Turret]
given Decoder[Map[TurretSlotDef, Turret]]   = new MapDecoder[TurretSlotDef, Turret]
