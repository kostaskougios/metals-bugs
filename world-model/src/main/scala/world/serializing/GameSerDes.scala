package world.serializing

import world.model.Map3D
import world.model.Move
import world.model.Player

final case class GameSerDes(
    id: String,
    map: Map3D,
    players: Seq[Player],
    moves: Seq[Move]
)
