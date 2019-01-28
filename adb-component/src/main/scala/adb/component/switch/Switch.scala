package adb.component.switch

import adb.util.BindingUtil._
import com.thoughtworks.binding.{dom, Binding}
import com.thoughtworks.binding.Binding.{BindingSeq, Constants, SingletonBindingSeq, Var}
import org.scalajs.dom.raw.{Element, Event, Node}
import scalaz.syntax.all._

object Switch {

  @dom
  def switch(checked: Var[Boolean]): Binding[Node] = {
    val handler = (e: Event) => {
      checked.value = !checked.value
    }

    <button type="button" class={"ant-switch " + (if (checked.bind) "ant-switch-checked" else "")} onclick={handler}>
      <span class="ant-switch-inner"></span>
      <div class="ant-click-animating-node"></div>
    </button>
  }

}
