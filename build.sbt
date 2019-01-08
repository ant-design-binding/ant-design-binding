name := "adb"
description := "Amazing components & design language in Scala.js"

val targetProjects = Seq(`adb-web-document`)
compile := ((compile in Compile).dependsOn(targetProjects.map(p => compile in Compile in p): _*)).value

lazy val commonSettings = Seq(
  organization := "ant-design-binding",
  version := "0.1.0",
  scalaVersion := "2.11.12",
  javacOptions ++= Seq("-encoding", "UTF-8", "-source", "1.8"),
  scalacOptions ++= Seq("-deprecation", "-feature"),
  addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full)
)

lazy val adb = (project in file("."))
  .settings(commonSettings: _*)
  .settings(
    (resources in Compile) += {
      (fullOptJS in(`adb-component`, Compile)).value
      (artifactPath in(`adb-component`, Compile, fullOptJS)).value
    }
  )


lazy val `adb-component` = (project in file("adb-component"))
  .configure(_.enablePlugins(ScalaJSPlugin))
  .settings(commonSettings: _*)
  .settings(
    libraryDependencies ++= Seq(
      "com.thoughtworks.binding" %%% "dom" % "11.6.0",
      "com.thoughtworks.binding" %%% "futurebinding" % "11.6.0"
    )
  )

lazy val `adb-web-document` = (project in file("adb-web-document"))
  .dependsOn(`adb-component`)
  .configure(_.enablePlugins(ScalaJSPlugin))
  .settings(commonSettings: _*)

