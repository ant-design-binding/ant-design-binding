package adb.component.input

import com.thoughtworks.binding.{dom, Binding}
import org.scalajs.dom.raw.HTMLInputElement

object Input {

  @dom
  def input(placeHolder: String = ""): Binding[HTMLInputElement] =
    <input placeholder={placeHolder} type="text" class="ant-input" value=""></input>

}
