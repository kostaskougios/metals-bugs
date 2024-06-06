package world.model

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers._
import world.model.WorldBuilders.coordsXY

class CoordsXYTest extends AnyFunSuite:
  val cxy = coordsXY(5, 10)

  test("facingFor NE"):
    cxy.facingFor(cxy.nearby.ne) should be(Facing.NE)
  test("facingFor NW"):
    cxy.facingFor(cxy.nearby.nw) should be(Facing.NW)
  test("facingFor SE"):
    cxy.facingFor(cxy.nearby.se) should be(Facing.SE)
  test("facingFor SW"):
    cxy.facingFor(cxy.nearby.sw) should be(Facing.SW)
  test("facingFor E"):
    cxy.facingFor(cxy.nearby.e) should be(Facing.E)
  test("facingFor W"):
    cxy.facingFor(cxy.nearby.w) should be(Facing.W)
