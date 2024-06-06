package world.serializer

import com.sksamuel.avro4s.AvroOutputStream
import com.sksamuel.avro4s._

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import scala.util.Using

abstract class AvroSerDes[A]:

  protected def output(out: OutputStream): AvroOutputStream[A]
  protected def input(in: InputStream): AvroInputStream[A]

  def serialize(in: A): Array[Byte] =
    val bos = new ByteArrayOutputStream(8192)
    serialize(in, bos)
    bos.close()
    bos.toByteArray()

  def serialize(in: A, f: File): Unit =
    Using.resource(new FileOutputStream(f)): out =>
      serialize(in, out)

  def serialize(in: A, out: OutputStream): Unit =
    val os = output(out)
    os.write(in)
    os.flush()
    os.close()

  def deserialize(f: File): A =
    Using.resource(new FileInputStream(f)): in =>
      deserialize(in)

  def deserialize(in: Array[Byte]): A = deserialize(new ByteArrayInputStream(in))
  def deserialize(in: InputStream): A =
    val aIn  = input(in)
    val data = aIn.iterator.next()
    aIn.close()
    data

abstract class AvroDataSerDes[A](using schemaFor: SchemaFor[A], encoder: Encoder[A], decoder: Decoder[A]) extends AvroSerDes[A]:
  override protected def output(out: OutputStream) = AvroOutputStream.data[A].to(out).build()
  override protected def input(in: InputStream)    = AvroInputStream.data[A].from(in).build

abstract class AvroBinarySerDes[A](using schemaFor: SchemaFor[A], encoder: Encoder[A], decoder: Decoder[A]) extends AvroSerDes[A]:
  override protected def output(out: OutputStream) = AvroOutputStream.binary[A].to(out).build()
  override protected def input(in: InputStream)    = AvroInputStream.binary[A].from(in).build(schemaFor.schema)
