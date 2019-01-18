package adb.web.document.component

import scala.annotation.{compileTimeOnly, StaticAnnotation}
import scala.collection.mutable
import scala.language.experimental.macros

import com.thoughtworks.binding.{dom, Binding}
import com.thoughtworks.binding.Binding.{Constants, Var}
import org.scalajs.dom.raw.{Event, Node}

@compileTimeOnly("enable macro paradise to expand macro annotations")
class codeDemo(builder: mutable.Builder[CodeDemoComponent, Seq[CodeDemoComponent]], title: String, markdownDescription: String)
  extends StaticAnnotation {
  def macroTransform(annottees: Any*): Any = macro CodeDemoMacros.macroTransform
}

private class CodeDemoMacros(val c: scala.reflect.macros.whitebox.Context) {

  import c.universe._
  import scalaz.{-\/, \/-}
  import scalaz.syntax.either._

  private def getDemoCode(pos: Position): String = {
    // since compiler does not provide range pos by default, use some trick to retrieve source code surrounded by `// DEMO CODE` comments
    val fromOutsideDef = new String(pos.source.content)
      .split("\n")
      .toList
      .drop(pos.line - 2)
    val afterFirstCodeDemoComment = fromOutsideDef
      .dropWhile(!_.contains("// DEMO CODE"))
      .tail
    val targetCodeLines = afterFirstCodeDemoComment.takeWhile(!_.contains("// DEMO CODE"))
    val indent = targetCodeLines.map(_.takeWhile(_ == ' ').length).min
    targetCodeLines.map(_.drop(indent)).mkString("\n")
  }

  def macroTransform(annottees: Tree*): Tree = {
    val (title, markdownDescription, builderTree) = (c.prefix.tree match {
      case Apply(_, List(builder, Literal(Constant(titleLiteral)), Literal(Constant(markdownDescriptionLiteral)))) =>
        (titleLiteral.toString, markdownDescriptionLiteral.toString, builder).right
      case Apply(_, List(_, Literal(_), p2)) => p2.left
      case Apply(_, List(_, p1, _)) => p1.left
    }) match {
      case -\/(errTree) => c.abort(errTree.pos, "Annotation param must be literal.")
      case \/-(v) => v
    }
    val r = annottees match {
      case Seq(annottee) =>
        annottee match {
          case defDef@DefDef(Modifiers(_, _, annotations), _, _, _, _, _) if annotations.collect {
            case Apply(Select(New(Ident(TypeName("dom"))), _), _) => Unit
          }.nonEmpty =>
            defDef match {
              case DefDef(_, name, Nil, List(Nil), _, rhs) =>
                val sourceCode = getDemoCode(rhs.pos)
                q"""
                   val $name = new _root_.adb.web.document.component.CodeDemoComponent {
                     def title = $title
                     def markdownDescription = $markdownDescription
                     override def sourceCode = $sourceCode
                     ${Modifiers(NoFlags, typeNames.EMPTY, annotations)} def component = $rhs
                   }
                   $builderTree.+=($name)
                 """.right
              case DefDef(_, _, tp, vp, _, _) => ("Method must not contains any parameters.", if (tp.nonEmpty) tp.head.pos else vp.head.head.pos).left
            }
          case _ => ("Annottee must annotated by @dom.", annottee.pos).left
        }
      case _ :: x :: _ => ("There must be only one annottee.", x.pos).left
    }
    r match {
      case -\/((msg, pos)) => c.abort(pos, msg)
      case \/-(t) => t
    }
  }

}

trait CodeDemoComponent {
  def title: String

  def markdownDescription: String

  def sourceCode: String

  def component: Binding[Node]

  @dom
  def render: Binding[Node] = {
    val expanded = Var(false)
    val handler = (_:Event) => {
      expanded.value = !expanded.value
    }

    <section class={"code-box " + (if (expanded.bind) "expand" else "")}>
      <section class="code-box-demo">
        {component.bind}
      </section>
      <section class="code-box-meta markdown">
        <div class="code-box-title">
          {title}
        </div>
        <div>
          <p>
            {markdownDescription}
          </p>
        </div>
        <span class="code-expand-icon" style="" onclick={handler}>
          <img alt="expand code"
               src="https://gw.alipayobjects.com/zos/rmsportal/wSAkBuJFbdxsosKKpqyq.svg"
               class={"code-expand-icon-" + (if(expanded.bind) "hide" else "show")}/>
          <img alt="expand code"
               src="https://gw.alipayobjects.com/zos/rmsportal/OpROPHYqWmrMDBFMZtKF.svg"
               class={"code-expand-icon-" + (if(!expanded.bind) "hide" else "show")}/>
        </span>
      </section>
      <section class={"highlight-wrapper " + (if (expanded.bind) "highlight-wrapper-expand" else "")}>
        <div class="highlight"><pre class="language-jsx">
          <code style="padding-left:32px; padding-right:32px;">{sourceCode}</code>
        </pre></div>
      </section>
    </section>
  }
}

object Examples {

  @dom
  def examples(builder: mutable.Builder[CodeDemoComponent, Seq[CodeDemoComponent]]): Binding[Node] = {
    val groups = builder
      .result()
      .zipWithIndex
      .groupBy(_._2 % 2)
      .mapValues(_.map(_._1.render))
    val leftGroup = groups.getOrElse(0, Nil)
    val rightGroup = groups.getOrElse(1, Nil)

    <div>
      <h2>Examples</h2>
      <div class="ant-row" style="margin-left: -8px; margin-right: -8px;">
        <div class="ant-col-12 code-boxes-col-2-1" style="padding-left: 8px; padding-right: 8px;">
          {Constants(leftGroup: _*).map(v => v.bind)}
        </div>
        <div class="ant-col-12 code-boxes-col-2-1" style="padding-left: 8px; padding-right: 8px;">
          {Constants(rightGroup: _*).map(v => v.bind)}
        </div>
      </div>
    </div>
  }

}
