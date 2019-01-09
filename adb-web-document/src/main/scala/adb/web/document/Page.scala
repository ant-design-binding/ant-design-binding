package adb.web.document

import com.thoughtworks.binding.{dom, Binding}
import org.scalajs.dom.Node

object Page {
  @dom
  def apply(): Binding[Node] = {
    <div>hello world!</div>
  }
}
