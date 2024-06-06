package world.serializer

import com.sksamuel.avro4s.Decoder
import com.sksamuel.avro4s.Encoder
import com.sksamuel.avro4s.SchemaFor
import org.apache.avro._
import world.model.Troop
import world.model.tanks.Tank

object AvroTroop:
  private val iEncoder = implicitly[Encoder[SerializedTroop]]
  private val iDecoder = implicitly[Decoder[SerializedTroop]]
  private val iSchema  = implicitly[SchemaFor[SerializedTroop]]

  given schema: SchemaFor[Troop] = new SchemaFor[Troop]:
    val schema: Schema = iSchema.schema

  given encoder: Encoder[Troop] = new Encoder[Troop]:
    def encode(schema: Schema): Troop => Any =
      case t: Tank =>
        iEncoder.encode(schema)(SerializedTroop(Some(t)))

  given decoder: Decoder[Troop] = new Decoder[Troop]:
    def decode(schema: Schema): Any => Troop = a =>
      val sm = iDecoder.decode(schema)(a)
      sm.tank.get

case class SerializedTroop(
    tank: Option[Tank] = None
)
