package adb.component.input

import scala.collection.mutable

import adb.web.document.component._
import com.thoughtworks.binding.{dom, Binding}
import com.thoughtworks.binding.Binding.Var
import org.scalajs.dom.raw.Node

object InputDocument {

  private val builder: mutable.Builder[CodeDemoComponent, Seq[CodeDemoComponent]] = Seq.newBuilder[CodeDemoComponent]

  @codeDemo(builder, "Basic usage", "Basic usage example.")
  @dom
  private def test() = {
    // DEMO CODE
    <div>
      {
        Input.input("Basic usage").bind
      }
    </div>
    // DEMO CODE
  }

  val page: Binding[Node] = Examples.examples(builder)(
    """
      |# Input
      |
      |A basic widget for getting the user input is a text field.
      |Keyboard and mouse can be used for providing or changing data.
      |
      |## When To Use
      |
      |- A user input in a form field is needed.
      |- A search input is required.
    """.stripMargin
  )

}
