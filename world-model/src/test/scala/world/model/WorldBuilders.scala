package world.model

import org.easygame.model.ColorRGBA
import world.model.tanks._

object WorldBuilders:
  def water(): Water              = Water()
  def mud(): Mud                  = Mud()
  def grass(): Grass              = Grass()
  def undefined(): Undefined.type = Undefined

  def coords(x: Int = 1, y: Int = 1, z: Int = 1): Coords = Coords(x, y, z)
  def coordsXY(x: Int = 1, y: Int = 2)                   = CoordsXY(x, y)

  def map3D(
      maxX: Int = 10,
      maxY: Int = 10,
      hexes: (Coords, Hex)*
  ): Map3D = Map3D(maxX, maxY, hexes)

  def player(
      id: String = "player-1-id",
      name: String = "player-1",
      color: ColorRGBA = ColorRGBA.Blue,
      troops: Seq[Troop] = Nil
  ) = Player(
    id,
    name,
    color,
    troops
  )

  def attributes(
      maxDistance: Int = 10,
      maxPassableHeight: Int = 1
  ) = Attributes(
    maxDistance,
    maxPassableHeight
  )
  def tank(
      id: Int = 1,
      definition: TankDef = Type80,
      coordinates: Coords = coords(),
      facing: Facing = Facing.NE,
      turrets: Map[TurretSlotDef, Turret] = Map.empty,
      color: ColorRGBA = ColorRGBA(0.0662331, 0.13361, 0.0760641, 1),
      attributes: Attributes = attributes()
  ) = Tank(
    id,
    definition,
    coordinates,
    facing,
    turrets,
    color,
    attributes
  )

  def hexCost(h: Hex) = h match
    case _: Grass => 1
    case _: Mud   => 2
    case _        => 1000000

  def navigator(maxDistance: Int = 10, maxPassableHeight: Int = 1, costCalc: Hex => Int = hexCost, map: Map3D = map3D()) =
    Navigator(maxDistance, maxPassableHeight, costCalc, map)

  def navigateResults(m: Map[Coords, List[Movement]] = Map.empty) = NavigateResults(m)

  def movement(to: Coords = coords(), cost: Int = 1)          = Movement(to, cost)
  def move(troop: Troop = tank(), path: List[Movement] = Nil) = Move(troop, path)
