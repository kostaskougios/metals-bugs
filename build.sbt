val scala3Version = "3.4.2"

ThisBuild / version := "1.0"

ThisBuild / scalaVersion := scala3Version

ThisBuild / scalacOptions ++= Seq("-unchecked", "-feature", "-deprecation", "-Wunused:all", "-Xmax-inlines", "128")

ThisBuild / semanticdbEnabled := true
ThisBuild / semanticdbVersion := scalafixSemanticdb.revision

val ScalaTest    = "org.scalatest"     %% "scalatest"       % "3.2.18" % Test
val ScalaMock    = "org.scalamock"     %% "scalamock"       % "6.0.0"  % Test
val Logback      = "ch.qos.logback"     % "logback-classic" % "1.4.14"
val Slf4j        = "org.slf4j"          % "slf4j-api"       % "2.0.13"
val CommonsIO    = "commons-io"         % "commons-io"      % "2.16.0"
val Monocle      = Seq(
  "dev.optics" %% "monocle-core"  % "3.1.0",
  "dev.optics" %% "monocle-macro" % "3.1.0"
)
val CommonsMath3 = "org.apache.commons" % "commons-math3"   % "3.6.1"

val Avro4s = "com.sksamuel.avro4s" %% "avro4s-core" % "5.0.9"
val Snappy = "org.xerial.snappy"    % "snappy-java" % "1.1.10.5"

val commonSettings = Seq(
  version     := "1.0",
  run / fork  := true,
  Test / fork := true
)

lazy val lib              = project
  .settings(
    commonSettings,
    libraryDependencies ++= Seq(ScalaTest, Logback, CommonsIO, Slf4j) ++ Monocle
  )
lazy val `easygame-model` = project
  .settings(
    commonSettings,
    libraryDependencies ++= Seq(ScalaTest)
  )
  .dependsOn(lib % "compile->compile; test->test")

lazy val `troops-server-client-common` = project
  .settings(
    commonSettings,
    libraryDependencies ++= Seq(ScalaTest)
  )
  .dependsOn(
    `world-model` % "compile->compile; test->test",
    `easygame-model`,
    lib           % "compile->compile; test->test"
  )

lazy val `world-model` = project
  .settings(
    commonSettings,
    libraryDependencies ++= Seq(CommonsMath3, ScalaTest)
  )
  .dependsOn(lib, `easygame-model`)

lazy val `troops-serialization` = project
  .settings(
    commonSettings,
    libraryDependencies ++= Seq(Avro4s, Snappy, ScalaTest)
  )
  .dependsOn(
    lib                           % "compile->compile; test->test",
    `world-model`                 % "compile->compile; test->test",
    `troops-server-client-common` % "compile->compile; test->test"
  )
