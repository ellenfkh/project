package treeTracer.parser

import scala.util.parsing.combinator._
import treeTracer.ir._

object TreeParser extends JavaTokenParsers with PackratParsers {
  def apply(s:String) :ParseResult[AST] = parseAll(graph, s)

  lazy val graph:PackratParser[Graph] =
    (
      rep(edge) ^^ {case edges => Graph(edges.toSet)}
      | failure("failed to parse a Graph")
    )

  lazy val person:PackratParser[Person] =
    (
      """\w+""".r ^^ {Person}
      | failure("failed to parse a Person")
    )

  lazy val edge:PackratParser[Edge] =
    (
      person~"is child of"~person ^^ {case p1~"is child of"~p2 => Child(p1, p2,
        "child")}
      | person~"is parent of"~person ^^ {case p1~"is child of"~p2 => Parent(p1, p2,
        "parent")}
      | person ^^ {case p => Self(p, p, "self")}
      |failure("failed to parse an Edge")
    )
}
