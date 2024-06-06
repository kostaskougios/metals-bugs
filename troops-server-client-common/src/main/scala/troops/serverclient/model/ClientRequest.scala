package troops.serverclient.model

import world.model.Player
import world.model.Movement

sealed trait ClientRequest
// ------------------------------------------------------------------------
// Game creation
// ------------------------------------------------------------------------
case class CreateGame(details: GameDetails)     extends ClientRequest
case class JoinGame(id: String, player: Player) extends ClientRequest

case class CloseSession() extends ClientRequest

// ------------------------------------------------------------------------
// In game
// ------------------------------------------------------------------------

case class MoveTroop(gameId: String, playerId: String, troopId: Int, movements: Seq[Movement]) extends ClientRequest
