package adb.component.tabs

import adb.util.BindingUtil._
import com.thoughtworks.binding.{dom, Binding}
import com.thoughtworks.binding.Binding.{BindingSeq, Constants, SingletonBindingSeq, Var}
import org.scalajs.dom.raw.{Element, Event, Node}
import scalaz.syntax.all._

object Tabs {

  @dom
  def tabContainer(tabList: Binding[Node], content: Binding[Node]): Binding[Node] = {
    <div class="ant-tabs ant-tabs-top ant-tabs-line">
      {tabList.bind}
      <div style="width: 0px; height: 0px; overflow: hidden; position: absolute;"/>
      {content.bind}
      <div style="width: 0px; height: 0px; overflow: hidden; position: absolute;"/>
    </div>
  }

  @dom
  def tabList(children: BindingSeq[Node], selectedNum: Binding[Int]): Binding[Node] = {
    val warpOption: Var[Option[Element]] = Var(None)
    val offsetWidth = Binding {
      warpOption.bind match {
        case Some(element) =>
          val sn = selectedNum.bind
          val warpLeft = element.getBoundingClientRect().left
          val selectedRect = element
            .children(0) // .ant-tabs-nav-scroll
            .children(0) // .ant-tabs-nav
            .children(0) // div
            .children(sn)
            .getBoundingClientRect()
          (selectedRect.left - warpLeft).toInt -> selectedRect.width.toInt
        case None => (0, 0)
      }
    }

    val warp: Binding[Element] = whenRectExists(v => warpOption.value = Some(v)) {
      Binding {
        <div class="ant-tabs-nav-wrap">
          <div class="ant-tabs-nav-scroll">
            <div class="ant-tabs-nav ant-tabs-nav-animated">
              <div>
                {children.map(v => v)}
              </div>
              {inkBar(offsetWidth.map(_._1), offsetWidth.map(_._2)).bind}
            </div>
          </div>
        </div>
      }
    }

    <div class="ant-tabs-bar ant-tabs-top-bar">
      <div class="ant-tabs-nav-container">
        {warp.bind}
      </div>
    </div>
  }

  @dom
  def inkBar(offset: Binding[Int], width: Binding[Int]): Binding[Node] = {
    <div class="ant-tabs-ink-bar ant-tabs-ink-bar-animated"
         style={s"display: block; transform: translate3d(${offset.bind}px, 0px, 0px); width: ${width.bind}px;"}/>
  }

  @dom
  def tab(text: String, isSelected: Binding[Boolean], onclick: Event => Unit = _ => {}): Binding[Node] = {
    <div class={"ant-tabs-tab " + (if (isSelected.bind) "ant-tabs-tab-active" else "")}
         onclick={onclick}>
      {text}
    </div>
  }

  @dom
  def tabContentContainer(children: BindingSeq[Node], showingNum: Binding[Int]): Binding[Node] = {
    <div class="ant-tabs-content ant-tabs-content-animated ant-tabs-top-content"
         style={s"margin-left: ${0 - showingNum.bind * 100}%;"}>
      {children.map(v => v)}
    </div>
  }

  @dom
  def tabContentPanel(children: BindingSeq[Node], isActivated: Binding[Boolean]): Binding[Node] = {
    <div class={"ant-tabs-tabpane " + (if (isActivated.bind) "ant-tabs-tabpane-active" else "ant-tabs-tabpane-inactive")}>
      {children.map(v => v)}
    </div>
  }

  case class TabContent[T](selectedItem: T, tabName: String, content: Binding[Node])

  @dom
  def tabs[T](tabContents: Seq[TabContent[T]], selectedItem: Option[Var[T]] = None): Binding[Node] = {
    val si: Var[T] = selectedItem match {
      case None => Var(tabContents.head.selectedItem)
      case Some(v) => v
    }
    val indexSelected = (si: Binding[T]).map(t => tabContents.indexWhere(_.selectedItem == t))
    tabContainer(
      tabList(
        for {
          tc <- Constants(tabContents: _*)
        } yield {
          val is = (si: Binding[T]).map(_ == tc.selectedItem)
          tab(tc.tabName, is, onclick = _ => si.value = tc.selectedItem).bind
        },
        indexSelected
      ),
      tabContentContainer(
        for {
          tc <- Constants(tabContents: _*)
        } yield {
          val is = (si: Binding[T]).map(_ == tc.selectedItem)
          tabContentPanel(SingletonBindingSeq(tc.content), is).bind
        },
        indexSelected
      )
    ).bind
  }

}
