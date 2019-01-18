package adb.web.document

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

import com.thoughtworks.binding.dom
import org.scalajs.dom.document

@JSExportTopLevel("WebApp")
object WebApp {

  @JSExport
  def start(): Unit = {
    dom.render(document.body, Frame.apply)
  }

}
