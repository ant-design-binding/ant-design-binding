package adb.component.button

import scala.collection.mutable

import adb.component.button.Button.{button, ButtonType}
import adb.web.document.component._
import com.thoughtworks.binding.{dom, Binding}
import com.thoughtworks.binding.Binding.Constant
import org.scalajs.dom.raw.Node

object ButtonDocument {

  private val builder: mutable.Builder[CodeDemoComponent, Seq[CodeDemoComponent]] = Seq.newBuilder[CodeDemoComponent]

  @codeDemo(builder, "Type", "There are Primary button, Default button, Dashed button and Danger button in antd.")
  @dom
  private def test() = {
    // DEMO CODE
    <div>
      {button("Primary", ButtonType.Primary).bind}
      {button("Default", ButtonType.Default).bind}
      {button(Constant("Dashed"), ButtonType.Dashed).bind}
      {button("Danger", Constant(ButtonType.Danger)).bind}
    </div>
    // DEMO CODE
  }

  val page: Binding[Node] = Examples.examples(builder)(
    """
      |# Button
      |
      |To trigger an operation.
      |
      |## When To Use
      |
      |A button means an operation (or a series of operations). Clicking a button will trigger corresponding business logic.
    """.stripMargin
  )

}
