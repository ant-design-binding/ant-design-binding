package adb.util

import org.scalajs.dom.raw.{DOMParser, Node}

object HtmlUtil {
  def toNode(html: String): Node = {
    (new DOMParser)
      .parseFromString(html, "text/html")
      .children(0) // generated <html>
      .children(2) // generated <body>
      .children(0) // parsed HTML Node
      .cloneNode(true)
  }
}
