package treeTracer.parser

import scala.util.parsing.combinator._
import treeTracer.ir._

object TreeParser extends JavaTokenParsers with PackratParsers {
  def apply(s:String) :ParseResult[AST] = parseAll(program, s)

  lazy val program:PackratParser[AST] =
    (
      "help" ^^ {case "help" => Help()}
      | "delete"~person ^^ {case "delete"~p => Delete(p)}
      | "load"~"""\w+""".r ^^ {case "load"~f => Load(f)}
      | "who is"~person~"to"~person ^^ {case "who is"~p1~"to"~p2 => XtoY(p1, p2)}
      | "who is"~person ^^ {case "who is"~p => WhoIsX(p)}
      | person~"is"~rel~"of"~person ^^ {case p1~"is"~rel~"of"~p2 => Edge(p1, p2,
        rel)}
      | person ^^ {case p => Edge(p, p, Relationship("self"))}
      | failure("failed to parse an Edge")
    )

  lazy val person:PackratParser[Person] =
    (
      """\w+""".r ^^ {Person}
      | failure("failed to parse a Person")
    )

  lazy val rel:PackratParser[Relationship] =
    (
      """\w+""".r ^^ {Relationship}
      | failure("failed to parse a Relationship")
    )
}
