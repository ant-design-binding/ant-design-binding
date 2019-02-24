package adb.component.form

import com.thoughtworks.binding.{dom, Binding}
import com.thoughtworks.binding.Binding.{BindingSeq, Constant, Constants, SingletonBindingSeq, Var}
import org.scalajs.dom.raw.{Event, Node}

object Form {

  @dom
  def form(children: BindingSeq[Node]): Binding[Node] =
    <form class="ant-form ant-form-horizontal">
      {children.map(e => e)}
    </form>

  @dom
  def formItem(label: Option[Binding[String]], isRequired: Boolean, children: Binding[Node]): Binding[Node] =
    <div class="ant-row ant-form-item">
      {
        label match {
          case Some(v) =>
            <div class="ant-form-item-label ant-col-xs-24 ant-col-sm-8">
              <label class={if(isRequired) "ant-form-item-required" else ""}>{v.bind}</label>
            </div>
          case None =>
            <div></div>
        }
      }
      <div class={"ant-form-item-control-wrapper ant-col-xs-24 ant-col-sm-16 " + (if(label.isEmpty) "ant-col-sm-offset-8" else "")}>
        <div class="ant-form-item-control"><span class="ant-form-item-children">
          {children.bind}
        </span>
        </div>
      </div>
    </div>

}
