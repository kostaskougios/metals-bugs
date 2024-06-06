package game.collections

extension [A](it: Iterator[A])
  def waitNext(): A =
    while !it.hasNext do Thread.sleep(1)
    it.next()
