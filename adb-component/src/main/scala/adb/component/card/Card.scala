package adb.component.card

import com.thoughtworks.binding.{dom, Binding}
import com.thoughtworks.binding.Binding.{BindingSeq, Constant, Constants, SingletonBindingSeq, Var}
import org.scalajs.dom.raw.{Event, Node}

object Card {

  @dom
  def card(content: BindingSeq[Node], title: Option[String] = None, bordered: Boolean = false): Binding[Node] =
    <div class={"ant-card " +(if(bordered)"ant-card-bordered"else "")}>
      {
        title match {
          case Some(v) =>
            <div class="ant-card-head">
              <div class="ant-card-head-wrapper">
                <div class="ant-card-head-title">{v}</div>
              </div>
            </div>
          case None => <div></div>
        }
      }
      <div class="ant-card-body">{content.map(e => e)}</div>
    </div>

}
