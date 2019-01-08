import mill._, scalalib._
import mill.scalajslib._

trait SbtLayoutScalaJSModule extends ScalaJSModule {
  override def sources = T.sources { millSourcePath / 'src / 'main / 'scala }
  override def resources = T.sources { millSourcePath / 'src / 'main / 'resources }
}

trait CommonScalaJsModule extends SbtLayoutScalaJSModule {
  override def scalaVersion = "2.11.12"
  override def scalaJSVersion = "0.6.26"
  override def scalacOptions = Seq("-deprecation", "-feature")
  override def scalacPluginIvyDeps = Agg(
    ivy"org.scalamacros:::paradise:2.1.1"
  )
}

object `adb-component` extends CommonScalaJsModule {
  override def ivyDeps = Agg(
    ivy"com.thoughtworks.binding::dom::11.6.0",
    ivy"com.thoughtworks.binding::futurebinding::11.6.0"
  )
}

object `adb-web-document` extends CommonScalaJsModule {
  override def moduleDeps = Seq(`adb-component`)
}
