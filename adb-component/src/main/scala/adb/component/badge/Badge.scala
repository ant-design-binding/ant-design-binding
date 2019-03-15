package adb.component.badge

import com.thoughtworks.binding.{dom, Binding}
import com.thoughtworks.binding.bindable._
import org.scalajs.dom.raw._

object Badge {

  sealed trait BadgeStatus {
    private[badge] def statusClass: String
  }

  object BadgeStatus {

    object Success extends BadgeStatus {
      override private[badge] def statusClass = "ant-badge-status-success"
    }

    object Error extends BadgeStatus {
      override private[badge] def statusClass = "ant-badge-status-error"
    }

    object Default extends BadgeStatus {
      override private[badge] def statusClass = "ant-badge-status-default"
    }

    object Processing extends BadgeStatus {
      override private[badge] def statusClass = "ant-badge-status-processing"
    }

    object Warning extends BadgeStatus {
      override private[badge] def statusClass = "ant-badge-status-warning"
    }

  }

  @dom
  def badge[BadgeStatusT: Bindable.Lt[?, BadgeStatus], Text: Bindable.Lt[?, String]](status: BadgeStatusT, text: Text = ""): Binding[Node] = {
    <span class="ant-badge ant-badge-status ant-badge-not-a-wrapper"><span class={"ant-badge-status-dot " + status.bind.statusClass}></span><span class="ant-badge-status-text">{text.bind}</span></span>
  }

}
