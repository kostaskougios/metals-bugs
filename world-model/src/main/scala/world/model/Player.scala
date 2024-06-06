package world.model

import org.easygame.model.ColorRGBA

final case class Player(
    id: String,
    name: String,
    color: ColorRGBA,
    troops: Seq[Troop]
):
  /** replaces a troop with a new version of itself (if the troop exists)
    */
  def modifyTroop(troop: Troop, newTroop: Troop): Player =
    if troop `!=id` newTroop then throw new IllegalArgumentException
    copy(
      troops = troops.map: t =>
        if t `=id=` troop then newTroop else t
    )
