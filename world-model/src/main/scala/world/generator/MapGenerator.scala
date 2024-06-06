package world.generator

import game.utils.LoggerFactory.loggerFor
import org.apache.commons.math3.analysis.interpolation.SplineInterpolator
import world.model._

import scala.annotation.tailrec
import scala.util.Random

class MapGenerator(
    maxX: Int,
    maxY: Int,
    step: Int = 10,
    zStep: Int = 20,
    waterBodies: Int = 4,
    randomZStep: (Int, Int, Int) => Int = MapGenerator.randomZStep,
    mapSeed: Option[Seq[Seq[Int]]] = None,
    waterCoordsSeed: Option[Seq[Coords]] = None
):
  private val logger = loggerFor(this)

  def generateMap: Map3D =
    logger.info("Creating seed terrain")
    val seed     = createSeed(mapSeed.getOrElse(createMapSeed))
    logger.info("interpolateOn X & Y")
    val filledIn = interpolateOnY(interpolateOnX(seed))
    logger.info("fillin gaps")
    val noGaps   = fillInGaps(filledIn)
    if noGaps.distinct.size != noGaps.size then throw new IllegalStateException("duplicate coords")
    val tm       = coordsToMap3D(noGaps)
    logger.info("creating seas")
    val m        = createSeas(tm, waterBodies)
    logger.info("map ready")
    m

  def createSeasSeed(m: Map3D, waterBodies: Int): Seq[Coords] =
    val minZ = m.minZ
    (1 to waterBodies).map: _ =>
      @tailrec def findXYZ: Coords =
        val x = Random.nextInt(maxX)
        val y = Random.nextInt(maxY)

        val cxy = CoordsXY(x, y)
        val z   = m.maxZAt(cxy)
        if z > 0 || z < minZ + 1 then findXYZ else cxy.andZ(z + 1)
      findXYZ

  def createSeas(m: Map3D, waterBodies: Int): Map3D =
    val waterCoords = waterCoordsSeed.getOrElse(createSeasSeed(m, waterBodies))
    logger.info(s"watercoords seed:\n$waterCoords")
    dropSeaAt(waterCoords, m)

  @tailrec final def dropSeaAt(cs: Seq[Coords], map: Map3D): Map3D =
    val eligCoords = cs.filterNot(c => map(c) != Undefined || c.x < 0 || c.y < 0 || c.x > maxX || c.y > maxY).distinct

    if eligCoords.isEmpty then map
    else
      val nm = eligCoords.foldLeft(map): (nm, c) =>
        nm + (c, Water())

      val ncs = eligCoords.flatMap(c => c.toXY.nearby.toSeq.map(_.andZ(c.z)) :+ c.downZ)
      dropSeaAt(ncs, nm)

  private def coordsToMap3D(coords: Seq[Coords]) =
    val maxMap = coords
      .groupBy(_.toXY)
      .map:
        case (xy, coords) => (xy, coords.maxBy(_.z))
    Map3D(
      maxX,
      maxY,
      coords.map: c =>
        val h = if maxMap(c.toXY) == c then Grass() else Mud()
        (c, h)
    )

  def fillInGaps(coords: Seq[Coords]): Seq[Coords] =
    val m = coordsToMap3D(coords)
    coords.flatMap: coord =>
      val nearby = coord.toXY.nearby
      val minZ   = (m(nearby.ne).keys ++
        m(nearby.nw).keys ++
        m(nearby.w).keys ++
        m(nearby.e).keys ++
        m(nearby.sw).keys ++
        m(nearby.se).keys).minOption.getOrElse(coord.z)
      if minZ <= coord.z - 1 then (minZ + 1 until coord.z).map(z => coord.copy(z = z)) :+ coord
      else Seq(coord)

  def createMapSeed: Seq[Seq[Int]] =
    (0 to maxX by step).map: x =>
      (0 to maxY by step).scanLeft(randomZStep(x, 0, zStep)): (z, y) =>
        z + randomZStep(x, y, zStep)

  def createSeed(mapSeed: Seq[Seq[Int]]): Seq[Coords] =
    logger.info(s"Map seed:\n$mapSeed")
    (0 to maxX by step)
      .zip(mapSeed)
      .flatMap: (x, ySeed) =>
        (0 to maxY by step)
          .zip(ySeed)
          .map: (y, z) =>
            Coords(x, y, z)

  def interpolateOnX(coords: Seq[Coords]): Seq[Coords] =
    coords
      .groupBy(_.x)
      .map: (x, coords) =>
        interpolate(coords.map(c => (c.y, c.z))).map: (y, z) =>
          Coords(x, y, z)
      .flatten
      .toSeq

  def interpolateOnY(coords: Seq[Coords]): Seq[Coords] =
    coords
      .groupBy(_.y)
      .map: (y, coords) =>
        interpolate(coords.sortBy(_.x).map(c => (c.x, c.z))).map: (x, z) =>
          Coords(x, y, z)
      .flatten
      .toSeq

  def interpolate(s: Seq[(Int, Int)]): Array[(Int, Int)] =
    val interpolator = new SplineInterpolator
    val xs           = s.map(_._1.toDouble)
    val xa           = xs.toArray
    val ya           = s.map(_._2.toDouble).toArray
    val spline       = interpolator.interpolate(xa, ya)
    (xs.min.toInt to xs.max.toInt).toArray.map(_.toDouble).map(x => (x.toInt, spline.value(x).toInt))

object MapGenerator:
  def randomZStep(x: Int, y: Int, zStep: Int): Int = Random.nextInt(zStep + 1) - zStep / 2
