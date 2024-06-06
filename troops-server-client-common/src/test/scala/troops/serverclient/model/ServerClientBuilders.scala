package troops.serverclient.model

import world.model.Player
import world.model.WorldBuilders._

object ServerClientBuilders:
  def gameDetails(
      id: String = "game-1-id",
      name: String = "game-name",
      players: Seq[Player] = Nil,
      maxX: Int = 10,
      maxY: Int = 10,
      step: Int = 4,
      zStep: Int = 8,
      waterBodies: Int = 1
  ) = GameDetails(
    id,
    name,
    players,
    maxX,
    maxY,
    step,
    zStep,
    waterBodies
  )

  def createdGame(id: String = "game-1-id")                      = CreatedGame(id)
  def createGame(details: GameDetails = gameDetails())           = CreateGame(details)
  def joinGame(id: String = "game-1", player: Player = player()) = JoinGame(id, player)
