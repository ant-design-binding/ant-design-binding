package adb.component.pagination

import scala.collection.mutable
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobal, JSGlobalScope, JSImport}

import adb.component.table.Table.TableCellContent
import adb.util.HtmlUtil
import adb.web.document.component._
import com.thoughtworks.binding.{dom, Binding}
import com.thoughtworks.binding.Binding.{Constants, Var}
import org.scalajs.dom.raw.{Element, Node}
import org.scalajs.dom.document

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

  val page: Binding[Node] = Examples.examples(builder, 1)(
    """
      |# Pagination
      |
      |A long list can be divided into several pages by Pagination, and only one page will be loaded at a time.
      |
      |## When To Use
      |
      |- When it will take a long time to load/render all items.
      |- If you want to browse the data by navigating through pages.
    """.stripMargin
  )

}
