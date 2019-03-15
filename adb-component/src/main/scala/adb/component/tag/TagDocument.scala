package adb.component.tag

import scala.collection.mutable

import adb.web.document.component._
import com.thoughtworks.binding.{dom, Binding}
import org.scalajs.dom.raw.Node
import Tag._

object TagDocument {

  private val builder: mutable.Builder[CodeDemoComponent, Seq[CodeDemoComponent]] = Seq.newBuilder[CodeDemoComponent]

  @codeDemo(builder, "Colorful Tag", "We preset a series of colorful tag style for different situation usage.")
  @dom
  private def test() = {
    // DEMO CODE
    <div>
      {tag("Magenta", TagColor.Magenta).bind}
      {tag("Red", TagColor.Red).bind}
      {tag("Volcano", TagColor.Volcano).bind}
      {tag("Orange", TagColor.Orange).bind}
      {tag("Gold", TagColor.Gold).bind}
      {tag("Lime", TagColor.Lime).bind}
      {tag("Green", TagColor.Green).bind}
      {tag("Cyan", TagColor.Cyan).bind}
      {tag("Blue", TagColor.Blue).bind}
      {tag("GeekBlue", TagColor.GeekBlue).bind}
      {tag("Purple", TagColor.Purple).bind}
      <style>
        <![CDATA[
          .ant-tag {
            margin-bottom: 8px;
          }
        ]]>
      </style>
    </div>
    // DEMO CODE
  }

  val page: Binding[Node] = Examples.examples(builder)(
    """
      |# Tag
      |
      |Tag for categorizing or markup.
      |
      |## When To Use
      |
      |- It can be used to tag by dimension or property.
      |
      |- When categorizing.
    """.stripMargin
  )

}
