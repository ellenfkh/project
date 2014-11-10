package treeTracer.parser

import scala.util.parsing.combinator._
import treeTracer.ir._

object PiconotParser extends JavaTokenParsers with PackratParsers {
  def apply(s:String) :ParseResult[AST] = parseAll(listRules, s)

  lazy val tree:PackratParser[Person] =
    (
      rep(person) ^^ {case people => Forest(people)}
      | failure("failed to parse a Forest")
    )

  lazy val person:PackratParser[Person] =
    (
      | person~"is child of"~person~"and"~person ^^ { case p~"is child of"~p1~"and"~p2 => Child(p, p1, p2)}
      | """\w+""".r ^^ {Node}
      | failure("failed to parse a person")
    )


}
