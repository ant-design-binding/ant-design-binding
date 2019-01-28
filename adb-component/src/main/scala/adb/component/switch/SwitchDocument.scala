package adb.component.switch

import scala.collection.mutable

import adb.component.table.Table.TableCellContent
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

  @dom
  def page(): Binding[Node] = {
    <div>
      <h1>Switch</h1>
      <p>Switching Selector.</p>
      <h2>When To Use</h2>
      <ul>
        <li>If you need to represent the switching between two states or on-off state.</li>
        <li>The difference between Switch and Checkbox is that Switch will trigger a state change directly when you toggle it, while Checkbox is generally used for state marking, which should work in conjunction with submit operation.</li>
      </ul>
      {Examples.examples(builder).bind}
    </div>
  }

}
