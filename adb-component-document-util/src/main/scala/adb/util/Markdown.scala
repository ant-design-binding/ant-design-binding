package adb.util

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobalScope

import org.scalajs.dom.document
import org.scalajs.dom.raw.Element

object Markdown {

  @js.native
  @JSGlobalScope
  object Window extends js.Object {
    def markdownit(): Markdown = js.native
  }

  @js.native
  trait Markdown extends js.Object {
    def render(text: String): String = js.native
  }

  def apply(text: String): Element = {
    val md = Window.markdownit()
    val html = md.render(text)
    val elem = document.createElement("div")
    elem.setAttribute("class", "markdown")
    elem.innerHTML = html
    elem
  }
}
