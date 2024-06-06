package world.generator

import org.scalatest.funsuite.AnyFunSuiteLike
import org.scalatest.matchers.should.Matchers._
import world.model.WorldBuilders.mud
import world.model.WorldBuilders.water
import world.model._

class MapGeneratorTest extends AnyFunSuiteLike:

  def randomZStep(x: Int, y: Int, zStep: Int): Int = x + y + 1
  val mg                                           = new MapGenerator(10, 10, 10, 20, 4, randomZStep)
  val grid                                         = new Grid

  test("interpolate"):
    val interpolated = mg.interpolate(Seq((1, 5), (3, 8), (5, 6)))
    interpolated should be(Seq((1, 5), (2, 6), (3, 8), (4, 7), (5, 6)))

  test("interpolateOnX"):
    val interpolated = mg.interpolateOnX(
      Seq(
        Coords(1, 0, 5),
        Coords(1, 3, 8),
        Coords(1, 5, 10)
      )
    )
    interpolated should be(
      Seq(
        Coords(1, 0, 5),
        Coords(1, 1, 6),
        Coords(1, 2, 7),
        Coords(1, 3, 8),
        Coords(1, 4, 9),
        Coords(1, 5, 10)
      )
    )

  test("interpolateOnY"):
    val interpolated = mg.interpolateOnY(
      Seq(
        Coords(0, 1, 5),
        Coords(3, 1, 8),
        Coords(5, 1, 10)
      )
    )
    interpolated should be(
      Seq(
        Coords(0, 1, 5),
        Coords(1, 1, 6),
        Coords(2, 1, 7),
        Coords(3, 1, 8),
        Coords(4, 1, 9),
        Coords(5, 1, 10)
      )
    )

  test("createSeed"):
    val mg = new MapGenerator(6, 3, 3, 10, 4, randomZStep)
    mg.createSeed(mg.createMapSeed) should be(
      Seq(
        Coords(0, 0, 1),
        Coords(0, 3, 2),
        Coords(3, 0, 4),
        Coords(3, 3, 8),
        Coords(6, 0, 7),
        Coords(6, 3, 14)
      )
    )

  test("createSeas"):
    val around1 = CoordsXY(2, 2).nearby.toSeq.map(_.andZ(1))
    val m       = Map3D.empty(5, 5) + (Coords(2, 2, 0), mud()) ++ around1.map(c => (c, mud()))
    val nm      = mg.dropSeaAt(Seq(Coords(2, 2, 1)), m)
    nm(2, 2, 1) should be(water())
