package world.model

import scala.annotation.tailrec

class Navigator(maxDistance: Int, maxPassableHeight: Int, costCalc: Hex => Int, map: Map3D):
  def navigate(from: Coords): NavigateResults =
    @tailrec def nav(toVisit: Seq[Coords], visitedSoFar: NavigateResults): NavigateResults =
      val (newToVisit, newVisitedSoFar) = toVisit.foldLeft((Set.empty[Coords], visitedSoFar)):
        case ((path, visited), at) =>
          val pathSoFar = visited.navigateTo(at)
          val cost      = costCalc(map(at)) + pathSoFar.head.cost
          if cost <= maxDistance then
            val nearBy     = at.toXY.nearby.toSeq
            val nearByAndZ = nearBy.flatMap: c =>
              for z <- at.z - maxPassableHeight to at.z + maxPassableHeight yield c.andZ(z)
            val nonVisited = nearByAndZ.filter(map.isPassable).filterNot(visited.pathExists)
            val newPaths   = nonVisited.map: c =>
              (c, Movement(c, cost) :: pathSoFar)
            (path ++ nonVisited, visited ++ newPaths)
          else (path, visited)
      if newToVisit.isEmpty then newVisitedSoFar
      else nav(newToVisit.toSeq, newVisitedSoFar)

    nav(Seq(from), new NavigateResults(Map(from -> List(Movement(from, 0)))))

class NavigateResults(m: Map[Coords, List[Movement]]):
  def pathExists(to: Coords): Boolean = m.contains(to)

  /** head of the list is the target coordinate, tail of the list is the starting coord
    */
  def navigateTo(c: Coords): List[Movement]                       = m(c)
  def ++(targets: Seq[(Coords, List[Movement])]): NavigateResults = new NavigateResults(m ++ targets)
  def reachable: Set[Coords]                                      = m.keySet
  def toHumanReadable: String                                     =
    m.map: (c, path) =>
      s"${c.toHumanReadable} : ${path.map(_.toHumanReadable).mkString(" <- ")}"
    .mkString("\n")
  def distanceTo(c: Coords): Int                                  = m(c).map(_.cost).sum
