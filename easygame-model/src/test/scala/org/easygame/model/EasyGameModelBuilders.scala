package org.easygame.model

import org.easygame.model.animations.SpatialAnimations
import org.easygame.model.fonts.FontDef
import org.easygame.model.light._
import org.easygame.view.Scene
import org.easygame.view.ViewPort
import org.easygame.view._

object EasyGameModelBuilders:
  def displayMode(width: Int = 1920, height: Int = 1024, bitDepth: Int = 32, refreshRate: Int = 60, isRetina: Boolean = false) =
    DisplayMode(width, height, bitDepth, refreshRate, isRetina)

  def viewPort(
      id: String = "view-port-id",
      camera: Camera = normalCamera(),
      scene: Scene = scene(),
      color: Boolean = true,
      depth: Boolean = true,
      stencil: Boolean = true
  ) = ViewPort(
    id,
    camera,
    scene,
    color,
    depth,
    stencil
  )

  def scene(
      id: String = "scene-id",
      lights: Seq[Light] = Nil,
      objects: Seq[ModelIdentifiable] = Nil,
      animations: SpatialAnimations = SpatialAnimations.Empty
  ) = Scene(
    id,
    lights,
    objects,
    animations
  )

  def normalCamera(
      name: String = "cam-name",
      width: Int = 1024,
      height: Int = 768,
      frustumPerspective: FrustumPerspective = frustumPerspective(),
      location: Vector3f = vector3f(),
      lookAt: LookAt = lookAt()
  ) = NormalCamera(
    name,
    width,
    height,
    frustumPerspective,
    location,
    lookAt
  )

  def frustumPerspective(
      fovY: Float = 1,
      aspect: Float = 2,
      near: Float = 3,
      far: Float = 4
  ) = FrustumPerspective(
    fovY,
    aspect,
    near,
    far
  )

  def vector3f(x: Float = 1, y: Float = 2, z: Float = 3)                       = Vector3f(x, y, z)
  def lookAt(pos: Vector3f = vector3f(), worldUpVector: Vector3f = vector3f()) = LookAt(pos, worldUpVector)

  def directionalLight(
      id: String = "light-id",
      color: ColorRGBA = colorRGBA(),
      direction: Vector3f = vector3f()
  ) = DirectionalLight(
    id,
    color,
    direction
  )
  def colorRGBA(r: Float = 1, g: Float = 1, b: Float = 1, a: Float = 1) = ColorRGBA(r, g, b, a)

  def mv(
      id: String = "spatial-id",
      modelName: String = "model-name",
      parents: Seq[String] = Nil,
      onRenderLocalTranslation: Vector3f = Vector3f.Zero,
      localTranslation: Option[Vector3f] = None,
      onRenderLocalRotation: Vector3f = Vector3f.Zero,
      localRotation: Option[Vector3f] = None,
      onRenderLocalScale: Vector3f = Vector3f.One,
      localScale: Option[Vector3f] = None,
      onRenderPaint: Option[Paint] = None,
      animationName: Option[String] = None,
      rotate: Option[Vector3f] = None,
      userData: Seq[UserData[?]] = Nil
  ) = MV(
    id,
    modelName,
    onRenderLocalTranslation,
    localTranslation,
    onRenderLocalRotation,
    localRotation,
    onRenderLocalScale,
    localScale,
    parents,
    onRenderPaint,
    animationName,
    rotate,
    userData
  )
  def textMV(
      id: String = "text-id",
      text: String = "test-text",
      fontName: String = FontDef.Default.name,
      size: Float = 1,
      parents: Seq[String] = Nil,
      localTranslation: Option[Vector3f] = None,
      localRotation: Option[Vector3f] = None,
      localScale: Option[Vector3f] = None
  ) = TextMV(
    id,
    text,
    fontName,
    size,
    parents,
    localTranslation,
    localRotation,
    localScale
  )

  def gmv(
      id: String = "gmv-1",
      mesh: Mesh = quad(),
      material: Material = material(),
      onRenderLocalTranslation: Vector3f = Vector3f.Zero,
      localTranslation: Option[Vector3f] = None,
      onRenderLocalRotation: Vector3f = Vector3f.Zero,
      localRotation: Option[Vector3f] = None,
      onRenderLocalScale: Vector3f = Vector3f.Zero,
      localScale: Option[Vector3f] = None,
      rotate: Option[Vector3f] = None,
      parents: Seq[String] = Nil,
      userData: Seq[UserData[?]] = Nil
  ) = GMV(
    id,
    mesh,
    material,
    onRenderLocalTranslation,
    localTranslation,
    onRenderLocalRotation,
    localRotation,
    onRenderLocalScale,
    localScale,
    rotate,
    parents,
    userData
  )
  def quad(width: Float = 1f, height: Float = 2f) = Quad(width, height)

  def material(
      path: String = "Common/MatDefs/Light/PBRLighting.j3md",
      colors: Seq[NamedColor] = Nil,
      texture: Seq[Texture] = Nil,
      additionalRenderState: Option[AdditionalRenderState] = None,
      booleans: Seq[(String, Boolean)] = Nil, // used by jmonkey , i.e. ("UseMaterialColors",true)
      floats: Seq[(String, Float)] = Nil      // used by jmonkey , i.e. ("Shininess", 64f)
  ) = Material(
    path,
    colors,
    texture,
    additionalRenderState,
    booleans,
    floats
  )

  def node(
      id: String = "node-1",
      localTranslation: Option[Vector3f] = None,
      localRotation: Option[Vector3f] = None,
      localScale: Option[Vector3f] = None,
      parents: Seq[String] = Nil,
      onRenderPaint: Option[Paint] = None,
      rotate: Option[Vector3f] = None,
      children: Seq[ModelIdentifiable] = Nil,
      userData: Seq[UserData[?]] = Nil
  ) = Node(
    id,
    localTranslation,
    localRotation,
    localScale,
    parents,
    onRenderPaint,
    rotate,
    children,
    userData
  )
