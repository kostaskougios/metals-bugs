package game.collections

import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue

class ProducerConsumerIterator[A] private (items: BlockingQueue[A], transformer: A => A = identity[A]) extends Iterator[A]:
  def produce(a: A): Unit       =
    val na = transformer(a)
    items.put(na)
  override def hasNext: Boolean =
    !items.isEmpty
  override def next(): A        =
    items.remove()

  def waitForNext(): A =
    items.take()

  def waitTillEmpty(): Unit =
    while hasNext do Thread.sleep(2)

  def currentSize: Int              = items.synchronized(items.size)
  def mustHaveAtLeast(n: Int): Unit = if currentSize < n then throw new IllegalStateException("Iterator doesn't have enough items")

  def withTransformer(transformer: A => A): ProducerConsumerIterator[A] = new ProducerConsumerIterator(items, transformer)

object ProducerConsumerIterator:
  def empty[A]: ProducerConsumerIterator[A]               = new ProducerConsumerIterator[A](new LinkedBlockingQueue[A])
  def apply[A](elements: A*): ProducerConsumerIterator[A] =
    val q = new LinkedBlockingQueue[A]()
    for e <- elements do q.put(e)
    new ProducerConsumerIterator[A](q)
