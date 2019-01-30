package adb.component.tabs

import scala.collection.mutable

import adb.component.table.TableDocument.builder
import adb.component.tabs.Tabs.TabContent
import adb.util.HtmlUtil
import adb.web.document.component._
import com.thoughtworks.binding.{dom, Binding}
import com.thoughtworks.binding.Binding.Constant
import org.scalajs.dom.raw.Node

object TabsDocument {

  private val builder: mutable.Builder[CodeDemoComponent, Seq[CodeDemoComponent]] = Seq.newBuilder[CodeDemoComponent]

  @codeDemo(builder, "Basic", "Default activate first tab.")
  @dom
  private def test() = {
    // DEMO CODE
    <div>
      {
        Tabs.tabs(Seq(
          TabContent(1, "Tab 1", Constant(<span>Content of Tab Pane 1</span>)),
          TabContent(2, "Tab 2", Constant(<span>Content of Tab Pane 2</span>)),
          TabContent(3, "Tab 3", Constant(<span>Content of Tab Pane 3</span>))
        )).bind
      }
    </div>
    // DEMO CODE
  }

  val page: Binding[Node] = Examples.examples(builder, 1)(
    """
      |# Tabs
      |
      |Tabs make it easy to switch between different views.
      |
      |## When To Use
      |
      |- Card Tabs: for managing too many closeable views.
      |- Normal Tabs: for functional aspects of a page.
      |- RadioButton: for secondary tabs.
    """.stripMargin
  )

}
