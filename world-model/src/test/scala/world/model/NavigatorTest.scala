package world.model

import org.scalatest.funsuite.AnyFunSuiteLike
import org.scalatest.matchers.should.Matchers._
import world.model.WorldBuilders._

class NavigatorTest extends AnyFunSuiteLike:
  test("goes around high obstacle"):
    val map = Map3D.empty(3, 3).fill(0, 0, 3, 3, 1, grass()) + (1, 1, 2, mud()) + (1, 1, 3, mud())
    val n   = navigator(map = map)
    val nr  = n.navigate(coords(1, 2, 1))
    nr.navigateTo(coords(1, 0, 1)) should not be Nil

  test("goes around water"):
    val map  = Map3D.empty(3, 3).fill(0, 0, 3, 3, 1, grass()) + (1, 1, 1, water())
    val n    = navigator(map = map)
    val nr   = n.navigate(coords(1, 2, 1))
    val path = nr.navigateTo(coords(1, 0, 1))
    path.find(_.to == coords(1, 1, 1)) should be(None)

  test("goes around large obstacle"):
    val map = Map3D.empty(10, 10).fill(0, 0, 9, 9, 1, grass()).fill(2, 5, 8, 5, 1, water())
    val n   = navigator(maxDistance = 100, map = map)
    val nr  = n.navigate(coords(5, 1, 1))
    nr.navigateTo(coords(5, 9, 1)) should not be Nil

  test("reachable"):
    val map      = Map3D.empty(3, 3).fill(0, 0, 3, 3, 1, grass()) + (1, 1, 2, mud()) + (1, 1, 3, mud())
    val n        = navigator(map = map)
    val nr       = n.navigate(coords(1, 2, 1))
    val expected =
      (for
        x <- 0 to 3
        y <- 0 to 3
      yield coords(x, y, 1)).toSet - coords(1, 1, 1)
    nr.reachable should be(expected)
