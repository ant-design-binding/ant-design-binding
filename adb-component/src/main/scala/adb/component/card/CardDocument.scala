package adb.component.card

import scala.collection.mutable

import adb.web.document.component._
import com.thoughtworks.binding.{dom, Binding}
import com.thoughtworks.binding.Binding.Var
import org.scalajs.dom.raw.Node

object CardDocument {

  private val builder: mutable.Builder[CodeDemoComponent, Seq[CodeDemoComponent]] = Seq.newBuilder[CodeDemoComponent]

  @codeDemo(builder, "Simple card", "A simple card only containing a content area.")
  @dom
  private def test() = {
    // DEMO CODE
    <div style="width: 300px;">
      {
        Card.card(
          Binding {
            <p>Card content</p>
            <p>Card content</p>
            <p>Card content</p>
          }.bind,
          bordered = true
        ).bind
      }
    </div>
    // DEMO CODE
  }

  val page: Binding[Node] = Examples.examples(builder)(
    """
      |# Card
      |
      |Simple rectangular container.
      |
      |## When To Use
      |
      |A card can be used to display content related to a single subject. The content can consist of multiple elements of varying types and sizes.
    """.stripMargin
  )

}
