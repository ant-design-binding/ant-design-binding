package adb.web.document

import com.thoughtworks.binding.{dom, Binding}
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
    <header id="header" class="clearfix">
      <div class="ant-row">
        <div class="ant-col-xs-24 ant-col-sm-24 ant-col-md-5 ant-col-lg-5 ant-col-xl-5 ant-col-xxl-4">
          <a id="logo" href="/">
            <img alt="logo" src="https://gw.alipayobjects.com/zos/rmsportal/KDpgvguMpGfqaHPjicRK.svg"/>
            <img alt="Ant Design" src="https://gw.alipayobjects.com/zos/rmsportal/DkKNubTaaVsKURhcVGkh.svg"/>
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
              <a href="https://pro.ant.design" class="header-link" target="_blank" rel="noopener noreferrer"><span>PRO</span></a>
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
    <div class="main-wrapper">
      <div class="ant-row">
        <div class="main-menu ant-col-xs-24 ant-col-sm-24 ant-col-md-24 ant-col-lg-6 ant-col-xl-5 ant-col-xxl-4">
          <div>
            <div class="">
              <section class="main-menu-inner">
                <ul class="ant-menu aside-container menu-site ant-menu-light ant-menu-root ant-menu-inline">
                  <!-- TODO swapped with menu component -->
                </ul>
              </section>
            </div>
          </div>
        </div>
        <div class="ant-col-xs-24 ant-col-sm-24 ant-col-md-24 ant-col-lg-18 ant-col-xl-19 ant-col-xxl-20">
          <section class="main-container main-container-component">
            <article>

            </article>
          </section>
        </div>
      </div>
    </div>
  }

}
