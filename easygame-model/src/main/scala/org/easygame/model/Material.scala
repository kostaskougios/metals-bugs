package org.easygame.model

case class Material(
    path: String,
    colors: Seq[NamedColor] = Nil,
    texture: Seq[Texture] = Nil,
    additionalRenderState: Option[AdditionalRenderState] = None,
    booleans: Seq[(String, Boolean)] = Nil, // used by jmonkey , i.e. ("UseMaterialColors",true)
    floats: Seq[(String, Float)] = Nil      // used by jmonkey , i.e. ("Shininess", 64f)
)

// see com.jme3.material.RenderState.BlendMode
enum BlendMode:
  case
    /** No blending mode is used.
      */
    Off,

    /** Additive blending. For use with glows and particle emitters. <p> Result = Source Color + Destination Color -&gt; (GL_ONE, GL_ONE)
      */
    Additive,

    /** Premultiplied alpha blending, for use with premult alpha textures. <p> Result = Source Color + (Dest Color * (1 - Source Alpha) ) -&gt; (GL_ONE,
      * GL_ONE_MINUS_SRC_ALPHA)
      */
    PremultAlpha,

    /** Additive blending that is multiplied with source alpha. For use with glows and particle emitters. <p> Result = (Source Alpha * Source Color) + Dest
      * Color -&gt; (GL_SRC_ALPHA, GL_ONE)
      */
    AlphaAdditive,

    /** Color blending, blends in color from dest color using source color. <p> Result = Source Color + (1 - Source Color) * Dest Color -&gt; (GL_ONE,
      * GL_ONE_MINUS_SRC_COLOR)
      */
    Color,

    /** Alpha blending, interpolates to source color from dest color using source alpha. <p> Result = Source Alpha * Source Color + (1 - Source Alpha) * Dest
      * Color -&gt; (GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA)
      */
    Alpha,

    /** Alpha blending, interpolates to source color from dest color using source alpha. The resulting alpha is the sum between the source alpha and the
      * destination alpha. <p> Result.rgb = Source Alpha * Source Color + (1 - Source Alpha) * Dest Color -&gt; (GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA) Result.a
      * \= 1 * Source Alpha + 1 * Dest Alpha -&gt; (GL_ONE, GL_ONE)
      */
    AlphaSumA,

    /** Multiplies the source and dest colors. <p> Result = Source Color * Dest Color -&gt; (GL_DST_COLOR, GL_ZERO)
      */
    Modulate,

    /** Multiplies the source and dest colors then doubles the result. <p> Result = 2 * Source Color * Dest Color -&gt; (GL_DST_COLOR, GL_SRC_COLOR)
      */
    ModulateX2,

    /** Opposite effect of Modulate/Multiply. Invert both colors, multiply and then invert the result. <p> Result = 1 - (1 - Source Color) * (1 - Dest Color)
      * -&gt; (GL_ONE, GL_ONE_MINUS_SRC_COLOR)
      */
    Screen,

    /** Mixes the destination and source colors similar to a color-based XOR operation. This is directly equivalent to Photoshop's "Exclusion" blend. <p> Result
      * \= (Source Color * (1 - Dest Color)) + (Dest Color * (1 - Source Color)) -&gt; (GL_ONE_MINUS_DST_COLOR, GL_ONE_MINUS_SRC_COLOR)
      */
    Exclusion,

    /** Uses the blend equations and blend factors defined by the render state. <p> These attributes can be set by using the following methods: <ul> <li>{@link
      * RenderState# setBlendEquation ( BlendEquation )} <li>{@link RenderState# setBlendEquationAlpha ( BlendEquationAlpha )} <li>{@link RenderState#
      * setCustomBlendFactors ( BlendFunc, BlendFunc, BlendFunc, BlendFunc)} </ul> <p> Result.RGB = BlendEquation( sfactorRGB * Source.RGB , dfactorRGB *
      * Destination.RGB )<br> Result.A = BlendEquationAlpha( sfactorAlpha * Source.A , dfactorAlpha * Destination.A )
      */
    Custom

case class AdditionalRenderState(blendMode: BlendMode = BlendMode.Off)

object AdditionalRenderState:
  val Default = AdditionalRenderState(BlendMode.Off)

object Material:
  val UnshadedWhite = unshaded(ColorRGBA.White)

  def unshaded(color: ColorRGBA) = Material(
    "Common/MatDefs/Misc/Unshaded.j3md",
    Seq(NamedColor("Color", color))
  )
