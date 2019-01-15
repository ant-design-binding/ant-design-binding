package adb.component.menu

import com.thoughtworks.binding.{dom, Binding}
import com.thoughtworks.binding.Binding.BindingSeq
import org.scalajs.dom.raw.Node
import org.scalajs.dom.Event

object Menu {

  @dom
  def menu(children: BindingSeq[Node]): Binding[Node] = {
    <ul class="ant-menu ant-menu-dark ant-menu-root ant-menu-inline">
      {children.map(v => v)}
    </ul>
  }

  @dom
  def menuItem(style: String = "", onclick: Event => Unit = _ => {}, children: BindingSeq[Node]): Binding[Node] = {
    <li class="ant-menu-item" style={style} onclick={onclick}>
      {children.map(v => v)}
    </li>
  }

}
