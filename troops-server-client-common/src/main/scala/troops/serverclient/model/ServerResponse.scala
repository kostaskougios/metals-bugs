package troops.serverclient.model

import world.model.Map3D
import world.model.Movement
import world.model.Player

sealed trait ServerResponse:
  def as[A <: ServerResponse] = asInstanceOf[A]

case class CreatedGame(id: String) extends ServerResponse:
  def join(player: Player) = JoinGame(id, player)

  // map
case class FullMap(map: Map3D)     extends ServerResponse

// players
case class FullPlayer(player: Player) extends ServerResponse

// in-game

case class TroopMoved(troopId: Int, movements: Seq[Movement]) extends ServerResponse
