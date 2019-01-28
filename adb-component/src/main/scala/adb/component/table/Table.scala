package adb.component.table

import adb.util.BindingUtil._
import com.thoughtworks.binding.{dom, Binding}
import com.thoughtworks.binding.Binding.{BindingSeq, Constants, SingletonBindingSeq, Var}
import org.scalajs.dom.raw.{Element, Event, Node}
import scalaz.syntax.all._

object Table {

  case class TableCellContent(content: Binding[Node])

  type TableRow = BindingSeq[TableCellContent]

  @dom
  def table(title: TableRow, rows: BindingSeq[TableRow]): Binding[Node] = {
    <div class="ant-table-wrapper">
      <div class="ant-spin-nested-loading">
        <div class="ant-spin-container">
          <div class="ant-table ant-table-default ant-table-scroll-position-left">
            <div class="ant-table-content">
              <div class="ant-table-body">
                <table class="">
                  <thead class="ant-table-thead">
                    <tr>
                      {
                        for {
                          cell <- title
                        } yield {
                          <th>{cell.content.bind}</th>
                        }
                      }
                    </tr>
                  </thead>
                  <tbody class="ant-table-tbody">
                    {
                      for {
                        row <- rows
                      } yield {
                        <tr class="ant-table-row ant-table-row-level-0">
                          {
                            for {
                              cell <- row
                            } yield {
                              <td class="">{cell.content.bind}</td>
                            }
                          }
                        </tr>
                      }
                    }
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  }

}
