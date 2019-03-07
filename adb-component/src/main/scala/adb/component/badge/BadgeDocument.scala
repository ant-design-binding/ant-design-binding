package adb.component.badge

import scala.collection.mutable

import adb.web.document.component._
import com.thoughtworks.binding.{dom, Binding}
import org.scalajs.dom.raw.Node
import Badge._

object BadgeDocument {

  private val builder: mutable.Builder[CodeDemoComponent, Seq[CodeDemoComponent]] = Seq.newBuilder[CodeDemoComponent]

  @codeDemo(builder, "Status", "Standalone badge with status.")
  @dom
  private def test() = {
    // DEMO CODE
    <div>
      {badge(BadgeStatus.Success).bind}
      {badge(BadgeStatus.Error).bind}
      {badge(BadgeStatus.Default).bind}
      {badge(BadgeStatus.Processing).bind}
      {badge(BadgeStatus.Warning).bind}
      <br/>
      {badge(BadgeStatus.Success).bind}
      <br/>
      {badge(BadgeStatus.Error).bind}
      <br/>
      {badge(BadgeStatus.Default).bind}
      <br/>
      {badge(BadgeStatus.Processing).bind}
      <br/>
      {badge(BadgeStatus.Warning).bind}
    </div>
    // DEMO CODE
  }

  val page: Binding[Node] = Examples.examples(builder)(
    """
      |# Badge
      |
      |Small numerical value or status descriptor for UI elements.
      |
      |## When To Use
      |
      |Badge normally appears in proximity to notifications or user avatars with eye-catching appeal, typically displaying unread messages count.
    """.stripMargin
  )

}
