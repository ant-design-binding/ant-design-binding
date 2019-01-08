import mill._, scalalib._
import mill.scalajslib._

object `adb-component` extends ScalaJSModule {
  def sources = T.sources{ millSourcePath / 'src / 'main / 'scala }
  def resources = T.sources{ millSourcePath / 'src / 'main / 'resources }

  def scalaVersion = "2.11.12"
  def scalaJSVersion = "0.6.26"
  def scalacOptions = Seq("-deprecation", "-feature")

  def scalacPluginIvyDeps = Agg(
    ivy"org.scalamacros:::paradise:2.1.1"
  )

  def ivyDeps = Agg(
    ivy"com.thoughtworks.binding::dom::11.6.0",
    ivy"com.thoughtworks.binding::futurebinding::11.6.0"
  )
}
