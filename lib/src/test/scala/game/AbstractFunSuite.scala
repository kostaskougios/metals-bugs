package game

import org.scalatest.concurrent.Eventually
import org.scalatest.funsuite.AnyFunSuiteLike
import org.scalatest.matchers.should.Matchers
import org.scalatest.time.Millis
import org.scalatest.time.Span

import scala.reflect.ClassTag

abstract class AbstractFunSuite extends AnyFunSuiteLike with Eventually with Matchers:
  given PatienceConfig = PatienceConfig(timeout = Span(2000, Millis))

  extension [A](s: Seq[A])
    def allTyped[B <: A: ClassTag]: Seq[B] =
      s.collect:
        case b: B => b
