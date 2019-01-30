package adb.component.button

import scala.collection.mutable

import adb.web.document.component._
import com.thoughtworks.binding.{dom, Binding}
import org.scalajs.dom.raw.Node
import Button.{button, ButtonType}
import adb.component.pagination.PaginationDocument.builder
import adb.util.HtmlUtil
import com.thoughtworks.binding.Binding.{BindingSeq, Constant, Constants}

object ButtonDocument {

  private val builder: mutable.Builder[CodeDemoComponent, Seq[CodeDemoComponent]] = Seq.newBuilder[CodeDemoComponent]

  @codeDemo(builder, "Type", "There are Primary button, Default button, Dashed button and Danger button in antd.")
  @dom
  private def test() = {
    // DEMO CODE
    <div>
      {button(Constant("Primary"), Constant(ButtonType.Primary)).bind}
      {button(Constant("Default"), Constant(ButtonType.Default)).bind}
      {button(Constant("Dashed"), Constant(ButtonType.Dashed)).bind}
      {button(Constant("Danger"), Constant(ButtonType.Danger)).bind}
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
