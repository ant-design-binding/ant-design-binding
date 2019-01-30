package adb.component.pagination

import scala.collection.mutable

import adb.component.table.Table.TableCellContent
import adb.web.document.component._
import com.thoughtworks.binding.{dom, Binding}
import com.thoughtworks.binding.Binding.{Constants, Var}
import org.scalajs.dom.raw.Node

object PaginationDocument {

  private val builder: mutable.Builder[CodeDemoComponent, Seq[CodeDemoComponent]] = Seq.newBuilder[CodeDemoComponent]

  @codeDemo(builder, "Basic", "Simple table.")
  @dom
  private def test() = {
    // DEMO CODE
    <div>
      {
        Pagination.pagination(Var(2),Var(5)).bind
      }
    </div>
    // DEMO CODE
  }

  @dom
  def page(): Binding[Node] = {
    <div>
      <div class="markdown">
        <h1>Pagination</h1>
        <p>A long list can be divided into several pages by Pagination, and only one page will be loaded at a time.</p>
        <h2>When To Use</h2>
        <ul>
          <li>When it will take a long time to load/render all items.</li>
          <li>If you want to browse the data by navigating through pages.</li>
        </ul>
      </div>
      {Examples.examples(builder, 1).bind}
    </div>
  }

}
