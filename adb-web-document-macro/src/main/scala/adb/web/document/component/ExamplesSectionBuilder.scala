package adb.web.document.component

import scala.language.experimental.macros
import scala.language.implicitConversions

import com.thoughtworks.binding.{dom, Binding}
import org.scalajs.dom.raw.Node

case class DemoCode(component: Binding[Node], sourceCode: String)

object DemoCode {
  implicit def bindingNodeToDemoCode(bindingNode: Binding[Node]): DemoCode = macro DemoCodeMacros.bindingNodeToDemoCode
}

private class DemoCodeMacros(val c: scala.reflect.macros.blackbox.Context) {

  import c.universe._

  def bindingNodeToDemoCode(bindingNode: Tree): Tree = {
    q"""
       DemoCode($bindingNode, ${new String(bindingNode.pos.source.content)})
     """
  }

}

case class ExamplesSectionBuilder() {
  private val sections = List.newBuilder[(String, String, DemoCode)]

  def addCodeBox(title: String, markdownDescription: String)(demoCode: DemoCode): ExamplesSectionBuilder = {
    sections += ((title, markdownDescription, demoCode))
    this
  }

  @dom
  def build() = {
    <div></div>
    <div></div>
  }
}
