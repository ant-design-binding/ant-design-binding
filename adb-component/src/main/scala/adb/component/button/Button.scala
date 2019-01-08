package adb.component.button

import com.thoughtworks.binding.{dom, Binding}
import org.scalajs.dom.html.Button

object Button {

  @dom
  def button(text: String): Binding[Button] = {
    <button>
      {text}
    </button>
  }

}
