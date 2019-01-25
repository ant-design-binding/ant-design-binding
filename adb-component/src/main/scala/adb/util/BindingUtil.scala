package adb.util

import com.thoughtworks.binding.Binding
import com.thoughtworks.binding.Binding.{SingleMountPoint, Var}
import org.scalajs.dom.raw.Element
import org.scalajs.dom.window

object BindingUtil {
  def whenRectExists(op: Element => Unit)(b: Binding[Element]): Binding[Element] = {
    val smp = new SingleMountPoint[Element](b) {
      override protected def set(value: Element): Unit = {
        def register() = window.requestAnimationFrame((_: Double) => initUpdate())

        def initUpdate(): Unit = {
          val rect = value.getBoundingClientRect()
          if (rect.width.toInt == 0 && rect.height.toInt == 0) {
            register()
          } else {
            op(value)
          }
        }

        register()
      }
    }

    Binding {
      smp.bind
      b.bind
    }
  }

  implicit class BindingElementWrapper(binding: Binding[Element]) {
    def rectExistsOption: Binding[Option[Element]] = Binding {
      val ret: Var[Option[Element]] = Var(None)
      whenRectExists(v => ret.value = Some(v))(binding).bind
      ret.bind
    }
  }

}
