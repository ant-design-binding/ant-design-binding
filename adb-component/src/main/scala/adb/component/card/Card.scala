package adb.component.card

import com.thoughtworks.binding.{dom, Binding}
import com.thoughtworks.binding.bindable._
import org.scalajs.dom.raw.Node

object Card {

  @dom
  def card[Content: BindableSeq.Lt[?, Node], Text: Bindable.Lt[?, String], Bordered: Bindable.Lt[?, Boolean]](content: Content, title: Option[Text] = None, bordered: Bordered = false): Binding[Node] =
    <div class={"ant-card " + (if(bordered.bind) "ant-card-bordered" else "")}>
      {
        title match {
          case Some(v) =>
            <div class="ant-card-head">
              <div class="ant-card-head-wrapper">
                <div class="ant-card-head-title">{v.bind}</div>
              </div>
            </div>
          case None => <div></div>
        }
      }
      <div class="ant-card-body">{content.bindSeq}</div>
    </div>

}
