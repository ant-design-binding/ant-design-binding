package adb.component.pagination

import adb.component.icon.Icon
import adb.util.BindingUtil._
import com.thoughtworks.binding.{dom, Binding}
import com.thoughtworks.binding.Binding.{BindingSeq, Constant, Constants, SingletonBindingSeq, Var}
import org.scalajs.dom.raw.{Element, Event, Node}
import scalaz.syntax.all._

object Pagination {

  @dom
  def pagination(currentPage: Var[Int], totalPage: Var[Int]): Binding[Node] = {
    <ul class="ant-pagination ">
      <li title="Previous Page" class={"ant-pagination-prev " + (if (currentPage.bind == 1) "ant-pagination-disabled" else "")}>
        <a class="ant-pagination-item-link">
          {Icon.anticonLeft}
        </a>
      </li>
      {
        val v: BindingSeq[Node] = totalPage.bind match {
          case v if v < 9 =>
            val vv: BindingSeq[Node] = for {
              i <- Constants(1 to v: _*)
            } yield {
              <li title={s"$i"} class={s"ant-pagination-item ant-pagination-item-$i " + (if (currentPage.bind == i) "ant-pagination-item-active" else "")}>
                <a>{i.toString}</a>
              </li>
            }
            vv
          case v =>
            Constants(
              item(Constant(1), currentPage).bind,
              <li title="Previous 5 Pages" class="ant-pagination-jump-prev ant-pagination-jump-prev-custom-icon">
                <a class="ant-pagination-item-link">
                  <div class="ant-pagination-item-container">
                    <span class="ant-pagination-item-ellipsis">•••</span>
                  </div>
                </a>
              </li>,
              item((currentPage:Binding[Int]).map(i => i - 2), currentPage).bind,
              item((currentPage:Binding[Int]).map(i => i - 1), currentPage).bind,
              item(currentPage, currentPage).bind,
              item((currentPage:Binding[Int]).map(i => i + 1), currentPage).bind,
              item((currentPage:Binding[Int]).map(i => i + 2), currentPage).bind,
              <li title="Next 5 Pages" class="ant-pagination-jump-prev ant-pagination-jump-prev-custom-icon">
                <a class="ant-pagination-item-link">
                  <div class="ant-pagination-item-container">
                    <span class="ant-pagination-item-ellipsis">•••</span>
                  </div>
                </a>
              </li>,
              item(Constant(v), currentPage).bind
            )
        }
        v.bind
      }
      <li title="Next Page" class={" ant-pagination-next " + (if (currentPage.bind == totalPage.bind) "ant-pagination-disabled" else "")}>
        <a class="ant-pagination-item-link">
          {Icon.anticonRight}
        </a>
      </li>
    </ul>
  }

  @dom
  def item(index: Binding[Int], selectedIndex: Var[Int]): Binding[Node] = {
    <li title={s"${index.bind}"} class={s"ant-pagination-item ant-pagination-item-${index.bind} " + (if (selectedIndex.bind == index.bind) "ant-pagination-item-active" else "")}>
      <a>{index.bind.toString}</a>
    </li>
  }

}
