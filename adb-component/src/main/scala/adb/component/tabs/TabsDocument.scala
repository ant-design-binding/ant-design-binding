package adb.component.tabs

import scala.collection.mutable

import adb.component.tabs.Tabs.TabContent
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

  @dom
  def page(): Binding[Node] = {
    <div>
      <div class="markdown">
        <h1>Tabs</h1>
        <p>Tabs make it easy to switch between different views.</p>
        <h2>When To Use</h2>
        <ul>
          <li>Card Tabs: for managing too many closeable views.</li>
          <li>Normal Tabs: for functional aspects of a page.</li>
          <li>RadioButton: for secondary tabs.</li>
        </ul>
      </div>
      {Examples.examples(builder, 1).bind}
    </div>
  }

}
