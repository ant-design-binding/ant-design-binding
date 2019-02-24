package adb.component.form

import scala.collection.mutable

import adb.component.button.Button
import adb.component.button.Button.ButtonType
import adb.component.input.Input
import adb.web.document.component._
import com.thoughtworks.binding.{dom, Binding}
import com.thoughtworks.binding.Binding.{Constant, Constants, Var}
import org.scalajs.dom.raw.Node

object FormDocument {

  private val builder: mutable.Builder[CodeDemoComponent, Seq[CodeDemoComponent]] = Seq.newBuilder[CodeDemoComponent]

  @codeDemo(builder, "Vertical Form", "Basic usage example.")
  @dom
  private def test() = {
    // DEMO CODE

    <div>
      {
        Form.form(
          Constants(
            Form.formItem(Some(Constant("userId")), true, Input.input()).bind,
            Form.formItem(Some(Constant("userName")), false, Input.input()).bind,
            Form.formItem(None, false, Button.button(Constant("Submit"), Constant(ButtonType.Primary))).bind
          )
        ).bind
      }
      <style>
        <![CDATA[
      .code-box-demo .ant-form:not(.ant-form-inline):not(.ant-form-vertical) {
        max-width: 600px;
      }
      ]]>
      </style>
    </div>
    // DEMO CODE
  }

  val page: Binding[Node] = Examples.examples(builder, 1)(
    """
      |# Form
      |
      |Form is used to collect, validate, and submit the user input, usually contains various form items including checkbox, radio, input, select, and etc.
    """.stripMargin
  )

}
