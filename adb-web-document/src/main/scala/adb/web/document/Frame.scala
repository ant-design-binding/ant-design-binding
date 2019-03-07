package adb.web.document

import adb.component.badge.BadgeDocument
import adb.component.button.ButtonDocument
import adb.component.card.CardDocument
import adb.component.form.FormDocument
import adb.component.input.InputDocument
import adb.component.menu.Menu
import adb.component.menu.Menu.NavigationItem
import adb.component.pagination.PaginationDocument
import adb.component.switch.SwitchDocument
import adb.component.table.TableDocument
import adb.component.tabs.TabsDocument
import adb.util.{BindingUtil, HtmlUtil}
import adb.web.document.page.AntDesignBinding
import com.thoughtworks.binding.{dom, Binding}
import com.thoughtworks.binding.Binding.{Constant, Constants, Var}
import org.scalajs.dom.Node

object Frame {

  @dom
  def apply(): Binding[Node] = {
    <div>
      {header.bind}
      {mainWrapper.bind}
    </div>
  }

  @dom
  private def header: Binding[Node] = {
    val gitHubLogo = HtmlUtil.toNode(""" <svg height="16" version="1.1" width="16" style="display: inline; margin: auto; padding-top: 1px"><path fill-rule="evenodd" d="M8 0C3.58 0 0 3.58 0 8c0 3.54 2.29 6.53 5.47 7.59.4.07.55-.17.55-.38 0-.19-.01-.82-.01-1.49-2.01.37-2.53-.49-2.69-.94-.09-.23-.48-.94-.82-1.13-.28-.15-.68-.52-.01-.53.63-.01 1.08.58 1.23.82.72 1.21 1.87.87 2.33.66.07-.52.28-.87.51-1.07-1.78-.2-3.64-.89-3.64-3.95 0-.87.31-1.59.82-2.15-.08-.2-.36-1.02.08-2.12 0 0 .67-.21 2.2.82.64-.18 1.32-.27 2-.27.68 0 1.36.09 2 .27 1.53-1.04 2.2-.82 2.2-.82.44 1.1.16 1.92.08 2.12.51.56.82 1.27.82 2.15 0 3.07-1.87 3.75-3.65 3.95.29.25.54.73.54 1.48 0 1.07-.01 1.93-.01 2.2 0 .21.15.46.55.38A8.013 8.013 0 0 0 16 8c0-4.42-3.58-8-8-8z"></path></svg> """)

    <header id="header" class="clearfix">
      <div class="ant-row">
        <div class="ant-col-xs-24 ant-col-sm-24 ant-col-md-5 ant-col-lg-5 ant-col-xl-5 ant-col-xxl-4">
          <a id="logo" href="/">
            <img alt="logo" src="adb_logo_128x128.png" style="margin-right: 9px;"/>
            <h3 style="display:inline; vertical-align: middle;">Ant Design Binding</h3>
          </a>
        </div>
        <div class="ant-col-xs-0 ant-col-sm-0 ant-col-md-19 ant-col-lg-19 ant-col-xl-19 ant-col-xxl-20">
          <ul class="ant-menu menu-site ant-menu-light ant-menu-root ant-menu-horizontal" id="nav">
            <li class="ant-menu-submenu ant-menu-submenu-horizontal ant-menu-overflowed-submenu" style="display: none;">
              <div class="ant-menu-submenu-title">
                <span>···</span><i class="ant-menu-submenu-arrow"></i>
              </div>
            </li>
            <li class="ant-menu-item ant-menu-item-selected"><a href="/"><span>Components</span></a></li>
            <li class="ant-menu-submenu ant-menu-submenu-horizontal ant-menu-overflowed-submenu" style="display: none;">
              <div class="ant-menu-submenu-title">
                <span>···</span> <i class="ant-menu-submenu-arrow"></i>
              </div>
            </li>
            <li class="ant-menu-item"><a href="https://ant.design/docs/spec/introduce"><span>Guidelines</span></a></li>
            <li class="ant-menu-submenu ant-menu-submenu-horizontal ant-menu-overflowed-submenu" style="display: none;">
              <div class="ant-menu-submenu-title">
                <span>···</span><i class="ant-menu-submenu-arrow"></i>
              </div>
            </li>
            <li class="ant-menu-item hide-in-home-page">
              <a href="https://github.com/ant-design-binding/ant-design-binding" class="header-link" target="_blank" rel="noopener noreferrer">
                <span>GitHub</span>
                {gitHubLogo}
              </a>
            </li>
            <li class="ant-menu-submenu ant-menu-submenu-horizontal ant-menu-overflowed-submenu" style="visibility: hidden; position: absolute; display: none;">
              <div class="ant-menu-submenu-title">
                <span>···</span><i class="ant-menu-submenu-arrow"></i>
              </div>
            </li>
          </ul>
        </div>
      </div>
    </header>
  }

  @dom
  private def mainWrapper: Binding[Node] = {
    val pages = Seq(
      "Ant Design Binding" -> AntDesignBinding.page,
      "Button" -> ButtonDocument.page,
      "Badge" -> BadgeDocument.page,
      "Card" -> CardDocument.page,
      "Form" -> FormDocument.page,
      "Input" -> InputDocument.page,
      "Pagination" -> PaginationDocument.page,
      "Switch" -> SwitchDocument.page,
      "Table" -> TableDocument.page,
      "Tabs" -> TabsDocument.page
    )
    val ni = for {
      (k, v) <- Constants(pages: _*)
    } yield {
      NavigationItem[Binding[Node]](v, Constant { <span>{k}</span> })
    }
    val si = Var(Option(pages.head._2))

    <div class="main-wrapper">
      <div class="ant-row">
        <div class="main-menu ant-col-xs-24 ant-col-sm-24 ant-col-md-24 ant-col-lg-6 ant-col-xl-5 ant-col-xxl-4">
          <div>
            <div class="">
              <section class="main-menu-inner">
                <ul class="ant-menu aside-container menu-site ant-menu-light ant-menu-root ant-menu-inline">
                  {Menu.navigation(ni.all.bind, si).bind}
                </ul>
              </section>
            </div>
          </div>
        </div>
        <div class="ant-col-xs-24 ant-col-sm-24 ant-col-md-24 ant-col-lg-18 ant-col-xl-19 ant-col-xxl-20">
          <section class="main-container">
            <article>
              <div>
                {si.bind.get.bind}
              </div>
            </article>
          </section>
        </div>
      </div>
    </div>
  }

}
