package adb.component.badge

import com.thoughtworks.binding.{dom, Binding}
import org.scalajs.dom.raw.Node

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
  def badge(status: BadgeStatus, text: String = ""): Binding[Node] = {
    <span class="ant-badge ant-badge-status ant-badge-not-a-wrapper">
      <span class="ant-badge-status-dot ant-badge-status-processing"></span>
      <span class="ant-badge-status-text">
        {text}
      </span>
    </span>
  }

}
