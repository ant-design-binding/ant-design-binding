package adb.router

import scala.language.implicitConversions
import scala.util.Try

import com.thoughtworks.binding.Binding
import com.thoughtworks.binding.Binding.Var
import org.scalajs.dom.raw.Node

case class Router(notFoundPage: Binding[Node])(routingStrategy: RoutingStrategy) {
  private val currentPath = Var(Path(Nil))
  private val currentPage = Var(notFoundPage)

  def route(path: String): Unit = {
    val p = Path.fromStr(path)
    val newPath = if (p.segments.headOption.contains("")) {
      // absolute path
      p.subPath(1)
    } else {
      // relative path
      Path((currentPath.value.segments ++ p.segments).reverse.dropWhile(_.isEmpty).reverse)
    }
    val page = routingStrategy.exec(newPath).getOrElse(notFoundPage)
    currentPath.value = newPath
    currentPage.value = page
  }

  def page: Binding[Binding[Node]] = currentPage

  def path: Binding[Path] = currentPath
}

trait RoutingStrategy {
  private val self = this

  def exec(path: Path): Option[Binding[Node]]

  def ~(next: RoutingStrategy): RoutingStrategy = new RoutingStrategy {
    override def exec(path: Path): Option[Binding[Node]] = self.exec(path).orElse(next.exec(path))
  }
}

trait Directive[T] {
  def apply(inner: T => RoutingStrategy): RoutingStrategy
}

case class Path(segments: Seq[String]) {
  def subPath(start: Int, len: Int = Int.MaxValue): Path = {
    //noinspection DropTakeToSlice
    Path(segments.drop(start).take(len))
  }
}

object Path {
  def fromStr(path: String) = Path(path.split("/", -1).toSeq)

  implicit def fromSeqStr(segments: Seq[String]): Path = Path(segments)
}

object RoutingStrategy {
  def path(pathPrefix: Path): Directive[Unit] = new Directive[Unit] {
    override def apply(inner: Unit => RoutingStrategy): RoutingStrategy = new RoutingStrategy {
      override def exec(path: Path): Option[Binding[Node]] = {
        if (path.segments.startsWith(pathPrefix.segments)) {
          inner(()).exec(path.subPath(pathPrefix.segments.length))
        } else {
          None
        }
      }
    }
  }

  def extract[T](extractor: String => Option[T]): Directive[T] = new Directive[T] {
    override def apply(inner: T => RoutingStrategy): RoutingStrategy = new RoutingStrategy {
      override def exec(path: Path): Option[Binding[Node]] = {
        for {
          value <- extractor(path.segments.head)
          result <- inner(value).exec(path.subPath(1))
        } yield {
          result
        }
      }
    }
  }

  def extractStr: Directive[String] = extract(Some.apply)

  def extractInt: Directive[Int] = extract(s => Try(s.toInt).toOption)

  def extractLong: Directive[Long] = extract(s => Try(s.toLong).toOption)

  def defaultPath(value: => Binding[Node]): RoutingStrategy = new RoutingStrategy {
    override def exec(path: Path): Option[Binding[Node]] =
      if (path.segments.isEmpty) {
        Some(value)
      } else {
        None
      }
  }
}
