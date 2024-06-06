package game.collections

import game.fibers.FiberExecutor.fiberExecutor
import org.scalatest.funsuite.AnyFunSuiteLike
import org.scalatest.matchers.should.Matchers._

class ProducerConsumerIteratorTest extends AnyFunSuiteLike:
  test("iterates"):
    val it = ProducerConsumerIterator.empty[Int]
    it.produce(5)
    it.produce(6)
    it.next() should be(5)
    it.next() should be(6)
    it.isEmpty should be(true)

  test("NoSuchElementException"):
    val it = ProducerConsumerIterator(1, 2)
    it.next()
    it.next()
    an[NoSuchElementException] should be thrownBy:
      it.next()

  test("operations don't affect iteration"):
    val pit = ProducerConsumerIterator(1, 2)
    val it  = pit.map(_ * 2)
    it.toList should be(List(2, 4))
    pit.produce(5)
    it.toList should be(List(10))
    pit.produce(4)
    pit.produce(5)
    val r   = it.fold(1): (i1, i2) =>
      i1 + i2
    r should be(19)

  test("waitForNext"):
    val it = ProducerConsumerIterator.empty[Int]
    fiberExecutor.submit:
      Thread.sleep(100)
      it.produce(5)
    it.waitForNext() should be(5)

  test("waitTillEmpty"):
    val it = ProducerConsumerIterator.empty[Int]
    it.produce(1)
    fiberExecutor.submit:
      it.next()
    it.waitTillEmpty()
    it.isEmpty should be(true)
