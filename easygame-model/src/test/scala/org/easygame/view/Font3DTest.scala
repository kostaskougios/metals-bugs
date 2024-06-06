package org.easygame.view

import game.AbstractFunSuite
import org.easygame.model.ColorRGBA
import org.easygame.model.Vector3f

class Font3DTest extends AbstractFunSuite:
  val font = new Font3D(_ => Vector3f.UnitXYZ)

  test("draws font"):
    font("id", "hello").map(_.toIdAndModelNameTuple) should be(
      Seq(
        ("id-0", "font.h"),
        ("id-1", "font.e"),
        ("id-2", "font.l"),
        ("id-3", "font.l"),
        ("id-4", "font.o")
      )
    )

  test("keeps {tokens}"):
    font("id", "hi {mouse} {x}").map(_.toIdAndModelNameTuple) should be(
      Seq(
        ("id-0", "font.h"),
        ("id-1", "font.i"),
        ("id-3", "font.mouse"),
        ("id-5", "font.x")
      )
    )

  test("color directives"):
    font("id", "hi {#red}x").map(_.onRenderPaint) should be(
      Seq(
        None,
        None,
        Some(Paint(ColorRGBA.Red, Set("font-material")))
      )
    )
  test("newline"):
    font("id", "hi\nx").map(_.onRenderLocalTranslation.y) should be(
      Seq(
        0f,
        0f,
        -1.2f
      )
    )
