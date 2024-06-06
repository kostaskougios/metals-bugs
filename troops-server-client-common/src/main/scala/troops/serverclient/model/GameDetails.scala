package troops.serverclient.model

import world.model.Player

final case class GameDetails(
    id: String,
    name: String,
    players: Seq[Player] = Nil,
    maxX: Int,
    maxY: Int,
    step: Int = 10,
    zStep: Int = 10,
    waterBodies: Int = 4
)
