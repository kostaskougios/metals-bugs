package org.easygame.model.animations

final case class SpatialAnimations(
    animations: Seq[SpatialAnimation]
):
  def contains(id: String): Boolean                              = animations.exists(_.id == id)
  def remove(id: String): SpatialAnimations                      = copy(animations = animations.filterNot(_.id == id))
  def add(a: SpatialAnimation): SpatialAnimations                =
    if contains(a.id) then throw new IllegalStateException(s"Animation exists : ${a.id}")
    copy(animations :+ a)
  def addAll(ans: Iterable[SpatialAnimation]): SpatialAnimations =
    for a <- ans do if contains(a.id) then throw new IllegalStateException(s"Animation exists : ${a.id}")
    copy(animations ++ ans)
  def removed(prev: SpatialAnimations): Seq[SpatialAnimation]    =
    prev.animations.filterNot(a => animations.exists(_ eq a))

  def removeFinished: SpatialAnimations = copy(
    animations = animations.filterNot(_.finished)
  )
  override def toString()               = s"SpatialAnimations(${animations.map(_.id).mkString(", ")})"

object SpatialAnimations:
  val Empty = SpatialAnimations(Nil)
