package adb.web.document.component

import scala.language.experimental.macros
import scala.language.implicitConversions

import com.thoughtworks.binding.{dom, Binding}
import org.scalajs.dom.raw.Node

case class DemoCode(component: Binding[Node], sourceCode: String)

object DemoCode {
  implicit def bindingNodeToDemoCode(bindingNode: Binding[Node]): DemoCode = macro DemoCodeMacros.bindingNodeToDemoCode

  implicit def nodeToDemoCode(node: Node): DemoCode = macro DemoCodeMacros.nodeToDemoCode
}

private class DemoCodeMacros(val c: scala.reflect.macros.blackbox.Context) {

  import c.universe._

  private def getDemoCode(pos: Position): String = {
    // because @dom macro wipes the line number inside defs, this may result in a larger range then the actual position
    val fromOutsideDef = new String(pos.source.content)
      .split("\n")
      .toList
      .drop(pos.line)
    val afterFirstCodeDemoComment = fromOutsideDef
      .dropWhile(!_.contains("// DEMO CODE"))
      .tail
    val targetCodeLines = afterFirstCodeDemoComment.takeWhile(!_.contains("// DEMO CODE"))
    val indent = targetCodeLines.map(_.takeWhile(_ == ' ').length).min
    targetCodeLines.map(_.drop(indent)).mkString("\n")
  }

  def bindingNodeToDemoCode(bindingNode: Tree): Tree = {
    q"""
       DemoCode($bindingNode, ${getDemoCode(bindingNode.pos)})
     """
  }

  def nodeToDemoCode(node: Tree): Tree = {
    q"""
       DemoCode(_root_.com.thoughtworks.binding.Binding.Constant($node), ${getDemoCode(node.pos)})
     """
  }

}

case class ExampleSection(title: String, markdownDescription: String, demoCode: DemoCode)

case class ExampleSectionsBuilder() {
  private val sections = List.newBuilder[ExampleSection]

  def addCodeBox(title: String, markdownDescription: String)(implicit demoCode: DemoCode): ExampleSectionsBuilder = {
    sections += ExampleSection(title, markdownDescription, demoCode)
    this
  }

  def build(): List[ExampleSection] = sections.result()
}
