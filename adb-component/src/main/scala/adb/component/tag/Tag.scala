package adb.component.tag

import com.thoughtworks.binding.{dom, Binding}
import com.thoughtworks.binding.bindable._
import org.scalajs.dom.raw._

object Tag {

  sealed trait TagColor {
    private[tag] def colorClass: String
  }

  object TagColor {

    object Magenta extends TagColor {
      override private[tag] def colorClass = "ant-tag-magenta"
    }

    object Red extends TagColor {
      override private[tag] def colorClass = "ant-tag-red"
    }

    object Volcano extends TagColor {
      override private[tag] def colorClass = "ant-tag-volcano"
    }

    object Orange extends TagColor {
      override private[tag] def colorClass = "ant-tag-orange"
    }

    object Gold extends TagColor {
      override private[tag] def colorClass = "ant-tag-gold"
    }

    object Lime extends TagColor {
      override private[tag] def colorClass = "ant-tag-lime"
    }

    object Green extends TagColor {
      override private[tag] def colorClass = "ant-tag-green"
    }

    object Cyan extends TagColor {
      override private[tag] def colorClass = "ant-tag-cyan"
    }

    object Blue extends TagColor {
      override private[tag] def colorClass = "ant-tag-blue"
    }

    object GeekBlue extends TagColor {
      override private[tag] def colorClass = "ant-tag-geekblue"
    }

    object Purple extends TagColor {
      override private[tag] def colorClass = "ant-tag-purple"
    }

  }

  @dom
  def tag[Text: Bindable.Lt[?, String], TagColorT: Bindable.Lt[?, TagColor]](text: Text, tagColor: TagColorT): Binding[Node] = {
    <div class={"ant-tag " + tagColor.bind.colorClass}>
      {text.bind}
    </div>
  }

}
