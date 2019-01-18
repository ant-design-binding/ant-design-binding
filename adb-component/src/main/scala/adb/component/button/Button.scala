package adb.component.button

import scala.collection.immutable

import com.thoughtworks.binding.{dom, Binding}
import com.thoughtworks.binding.Binding.Constant
import enumeratum._
import org.scalajs.dom.html.Button

object Button {

  sealed abstract class ButtonType(private[button] val className: String) extends EnumEntry

  case object ButtonType extends Enum[ButtonType] {

    val values: immutable.IndexedSeq[ButtonType] = findValues

    case object Default extends ButtonType("")

    case object Primary extends ButtonType("ant-btn-primary")

    case object Dashed extends ButtonType("ant-btn-dashed")

    case object Danger extends ButtonType("ant-btn-danger")

  }

  @dom
  def button(text: Binding[String], buttonType: Binding[ButtonType] = Constant(ButtonType.Default)): Binding[Button] = {
    <button type="button" class={"ant-btn " + buttonType.bind.className}>
      <span>{text.bind}</span>
    </button>
  }

}
