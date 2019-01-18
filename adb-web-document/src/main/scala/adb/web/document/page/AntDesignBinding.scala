package adb.web.document.page

import com.thoughtworks.binding.{dom, Binding}
import org.scalajs.dom.raw.Node

object AntDesignBinding {

  @dom
  def page(): Binding[Node] = {
    <div>
      <h1>Ant Design Binding</h1>
    </div>
  }

}
