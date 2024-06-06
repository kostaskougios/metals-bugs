package world.model

import org.scalatest.funsuite.AnyFunSuiteLike
import org.scalatest.matchers.should.Matchers._
import world.model.WorldBuilders._

class Map3DTest extends AnyFunSuiteLike:
  val Empty = Map3D.empty(100, 100)
  test("get on undefined coords"):
    Empty.apply(5, 6, 7) should be(Undefined)

  test("adds"):
    val m = Empty + (5, 10, 15, water())
    m(5, 10, 15) should be(water())

  test("equals positive"):
    Empty + (5, 10, 15, water()) should be(Empty + (5, 10, 15, water()))

  test("equals negative"):
    Empty + (5, 10, 15, water()) should not be (Empty + (1, 2, 3, water()))

  test("hashCode positive"):
    (Empty + (5, 10, 15, water())).hashCode should be((Empty + (5, 10, 15, water())).hashCode)

  test("hashCode negative"):
    (Empty + (5, 10, 15, water())).hashCode should not be (Empty + (1, 2, 3, water())).hashCode

  test("isPassable positive"):
    Empty.fill(1, 1, 2, 2, 5, grass()).isPassable(Coords(1, 1, 5)) should be(true)

  test("isPassable negative"):
    Empty.fill(1, 1, 2, 2, 5, grass()).fill(1, 1, 2, 2, 6, grass()).isPassable(Coords(1, 1, 5)) should be(false)
