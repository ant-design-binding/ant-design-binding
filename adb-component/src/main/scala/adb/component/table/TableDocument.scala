package adb.component.table

import scala.collection.mutable

import adb.component.button.ButtonDocument.builder
import adb.component.table.Table.TableCellContent
import adb.util.HtmlUtil
import adb.web.document.component._
import com.thoughtworks.binding.{dom, Binding}
import com.thoughtworks.binding.Binding.Constants
import org.scalajs.dom.raw.Node

object TableDocument {

  private val builder: mutable.Builder[CodeDemoComponent, Seq[CodeDemoComponent]] = Seq.newBuilder[CodeDemoComponent]

  @codeDemo(builder, "Basic", "Simple table.")
  @dom
  private def test() = {
    // DEMO CODE
    <div>
      {
        Table.table(
          Constants(
            TableCellContent(Binding { <div>Name</div> }),
            TableCellContent(Binding { <div>Age</div> }),
            TableCellContent(Binding { <div>Address</div> })
          ),
          Constants(
            Constants(
              TableCellContent(Binding { <span>John Brown</span> }),
              TableCellContent(Binding { <span>32</span> }),
              TableCellContent(Binding { <span>New York No. 1 Lake Park</span> })
            ),
            Constants(
              TableCellContent(Binding { <span>Jim Green</span> }),
              TableCellContent(Binding { <span>42</span> }),
              TableCellContent(Binding { <span>London No. 1 Lake Park</span> })
            ),
            Constants(
              TableCellContent(Binding { <span>Joe Black</span> }),
              TableCellContent(Binding { <span>22</span> }),
              TableCellContent(Binding { <span>Sidney No. 1 Lake Park</span> })
            )
          )
        ).bind
      }
    </div>
    // DEMO CODE
  }

  val page: Binding[Node] = Examples.examples(builder, 1)(
    """
      |# Table
      |
      |A table displays rows of data.
      |
      |## When To Use
      |
      |- To display a collection of structured data.
      |- To sort, search, paginate, filter data.
    """.stripMargin
  )

}
