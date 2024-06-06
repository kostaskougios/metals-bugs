package org.easygame.view

import org.easygame.maths.MathExt.Pi
import org.easygame.model.ColorRGBA
import org.easygame.model.Vector3f

class Font3D(
    spatialNameToDimensions: String => Vector3f, // use gameController.dimensionsOf
    extraDx: Float = 0.1f,
    extraDy: Float = 0.2f,
    fontPrefix: String = "font",
    defaultRotation: Vector3f = Vector3f(Pi / 2, 0f, 0f),
    defaultScale: Vector3f = Vector3f.One
):
  private val fp = fontPrefix + "."
  private case class Prop(dx: Float = 0f, dy: Float = 0f, paint: Option[Paint] = None)

  private val FontMaterial = Set("font-material")
  private val RedPaint     = Some(Paint(ColorRGBA.Red, FontMaterial))
  private val GreenPaint   = Some(Paint(ColorRGBA.Green, FontMaterial))
  private val BluePaint    = Some(Paint(ColorRGBA.Blue, FontMaterial))
  def apply(
      id: String,
      s: String,
      localTranslation: Vector3f = Vector3f.Zero,
      rotation: Vector3f = defaultRotation,
      scale: Vector3f = defaultScale,
      parents: Seq[String] = Nil
  ): Seq[MV] =
    val split = splitString(s)
    val props = split
      .scanLeft(Prop()): (p, c) =>
        if c.startsWith("#") then
          c match
            case "#none"  => p.copy(paint = None)
            case "#red"   => p.copy(paint = RedPaint)
            case "#green" => p.copy(paint = GreenPaint)
            case "#blue"  => p.copy(paint = BluePaint)
            case x        => throw new IllegalStateException(s"Unknown directive $x")
        else if c == "\n" then p.copy(dx = 0, dy = p.dy - (spatialNameToDimensions(fp + "A").z + extraDy) * scale.y)
        else
          val spatialName = fp + (if c == " " then "a" else c)
          val d           = (spatialNameToDimensions(spatialName).x + extraDx) * scale.x
          p.copy(dx = p.dx + d)
      .toArray
    split.zipWithIndex
      .filter: (c, _) =>
        c != " " && !c.startsWith("#") && c != "\n"
      .map: (c, i) =>
        val spatialName = fp + c
        val p           = props(i)
        MV(
          id + "-" + i,
          spatialName,
          onRenderLocalTranslation = localTranslation.addXY(p.dx, p.dy),
          onRenderLocalRotation = rotation,
          onRenderLocalScale = scale,
          onRenderPaint = p.paint,
          parents = parents
        )

  private val regex                                    = """\{([^}]*)\}""".r
  private def splitString(input: String): List[String] =
    val matches = regex.findAllMatchIn(input).toList

    var result  = List[String]()
    var lastEnd = 0

    for m <- matches do
      val start = m.start
      val end   = m.end
      if lastEnd < start then result ++= input.substring(lastEnd, start).map(_.toString)
      result :+= m.group(1)
      lastEnd = end

    if lastEnd < input.length then result ++= input.substring(lastEnd).map(_.toString)
    result
