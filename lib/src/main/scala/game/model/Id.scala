package game.model

trait Id[A]:
  def id: A
  def `=id=`(id2: Id[A]): Boolean = id == id2.id
  def `!=id`(id2: Id[A]): Boolean = id != id2.id

extension [A](id1: Option[Id[A]])
  /** @return
    *   true if id1 is defined and equals to id2
    */
  def `=id=`(id2: Option[Id[A]]): Boolean =
    val i1 = id1.map(_.id)
    val i2 = id2.map(_.id)
    i1 != None && i1 == i2

extension [A](s: IterableOnce[Id[A]])
  def containsSameId(id2: Id[A]): Boolean =
    s.iterator.exists(_ `=id=` id2)
