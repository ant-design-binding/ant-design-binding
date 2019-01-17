import java.io.File
import java.nio.file.Paths

name := "adb"
description := "Amazing components & design language in Scala.js"

val targetProjects = Seq(`adb-web-document`)
compile := (compile in Compile).dependsOn(targetProjects.map(p => compile in Compile in p): _*).value

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

lazy val `adb-component-document-util` = (project in file("adb-component-document-util"))
  .configure(_.enablePlugins(ScalaJSPlugin))
  .settings(commonSettings: _*)
  .settings(
    libraryDependencies ++= Seq(
      "com.thoughtworks.binding" %%% "dom" % "11.6.0",
      "com.thoughtworks.binding" %%% "futurebinding" % "11.6.0",
      "com.beachape" %%% "enumeratum" % "1.5.13"
    )
  )

lazy val `adb-component` = (project in file("adb-component"))
  .dependsOn(`adb-component-document-util`)
  .configure(_.enablePlugins(ScalaJSPlugin))
  .settings(commonSettings: _*)

lazy val `adb-web-document` = (project in file("adb-web-document"))
  .dependsOn(`adb-component`)
  .configure(_.enablePlugins(ScalaJSPlugin))
  .settings(commonSettings: _*)
  .settings(
    `copy-web-document-assets` := {
      val resourceFiles = (resources in Compile).value.filter(!_.isDirectory)
      val fullOptJsFile = (fullOptJS in Compile).value.data
      for {
        file <- fullOptJsFile +: resourceFiles
      } {
        IO.copyFile(file, assetsFile.resolve(file.getName).toFile)
      }
    },
    `copy-web-document-assets` := (`copy-web-document-assets` dependsOn (fullOptJS in Compile)).value
  )

lazy val assetsFile = Paths.get("target", "web-document-assets")
lazy val `copy-web-document-assets` = taskKey[Unit]("copy files in resources & fullOptJs result together into $assetsFile")
