package adb.component.switch

import scala.collection.mutable

import adb.component.button.ButtonDocument.builder
import adb.component.table.Table.TableCellContent
import adb.util.HtmlUtil
import adb.web.document.component._
import com.thoughtworks.binding.{dom, Binding}
import com.thoughtworks.binding.Binding.{Constants, Var}
import org.scalajs.dom.raw.Node

object SwitchDocument {

  private val builder: mutable.Builder[CodeDemoComponent, Seq[CodeDemoComponent]] = Seq.newBuilder[CodeDemoComponent]

  @codeDemo(builder, "Basic", "Simple table.")
  @dom
  private def test() = {
    // DEMO CODE
    <div>
      {
        Switch.switch(Var(true)).bind
      }
    </div>
    // DEMO CODE
  }

  val page: Binding[Node] = Examples.examples(builder)(
    """
      |# Switch
      |
      |Switching Selector.
      |
      |## When To Use
      |
      |- If you need to represent the switching between two states or on-off state.
      |- The difference between Switch and Checkbox is that Switch will trigger a state change directly when you toggle it, while Checkbox is generally used for state marking, which should work in conjunction with submit operation.
    """.stripMargin
  )

}
