package adb.component.input

import com.thoughtworks.binding.{dom, Binding}
import com.thoughtworks.binding.bindable._
import org.scalajs.dom.raw.HTMLInputElement

object Input {

  @dom
  def input[Text: Bindable.Lt[?, String]](placeHolder: Text = ""): Binding[HTMLInputElement] =
    <input placeholder={placeHolder.bind} type="text" class="ant-input" value=""></input>

}
