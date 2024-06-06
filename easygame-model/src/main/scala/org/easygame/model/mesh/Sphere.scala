package org.easygame.model.mesh

case class Sphere(
    zSamples: Int,
    radialSamples: Int,
    radius: Float,
    textureMode: TextureMode = TextureMode.Projected,
    useEvenSlices: Boolean = false,
    interior: Boolean = false,
    tangentBinormalLighting: Boolean = false
) extends Mesh

// see com.jme3.scene.shape.Sphere.TextureMode
enum TextureMode:
  case
    /** Wrap texture radially and along z-axis
      */
    Original,

    /** Wrap texture radially, but spherically project along z-axis
      */
    Projected,

    /** Apply texture to each pole. Eliminates polar distortion, but mirrors the texture across the equator
      */
    Polar
