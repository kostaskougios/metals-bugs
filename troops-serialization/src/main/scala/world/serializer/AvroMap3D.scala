package world.serializer

import com.sksamuel.avro4s.Decoder
import com.sksamuel.avro4s.Encoder
import com.sksamuel.avro4s.SchemaFor
import org.apache.avro._
import world.model.Map3D
import world.serializing.SerializedHex
import world.serializing.SerializedMap

object AvroMap3D:
  private val shEncoder = implicitly[Encoder[SerializedMap]]
  private val shDecoder = implicitly[Decoder[SerializedMap]]
  private val shSchema  = implicitly[SchemaFor[SerializedMap]]

  given schema: SchemaFor[Map3D] = new SchemaFor[Map3D]:
    val schema: Schema = shSchema.schema

  given encoder: Encoder[Map3D] = new Encoder[Map3D]:
    def encode(schema: Schema): Map3D => Any = (map) =>
      val data = map.toMap.toSeq.flatMap: (cxy, m) =>
        m.map: (z, h) =>
          val coords = cxy.andZ(z)
          SerializedHex(coords, h)
      shEncoder.encode(schema)(SerializedMap(map.maxX, map.maxY, data))

  given decoder: Decoder[Map3D] = new Decoder[Map3D]:
    def decode(schema: Schema): Any => Map3D = a =>
      val sm = shDecoder.decode(schema)(a)
      Map3D(sm.maxX, sm.maxY, sm.hexes.map(_.toTuple))
