package adb.component.input

import com.thoughtworks.binding.{dom, Binding}
import com.thoughtworks.binding.Binding.{BindingSeq, Constant, Constants, SingletonBindingSeq, Var}
import org.scalajs.dom.raw.{Event, Node}

object Input {

  @dom
  def input(placeHolder: String = ""): Binding[Node] =
    <input placeholder={placeHolder} type="text" class="ant-input" value=""></input>

}
