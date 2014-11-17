package treeTracer.parser

import org.scalatest._
import treeTracer.parser._
import treeTracer.ir._
import edu.hmc.langtools._

class TreeParserTest extends FunSpec with LangParseMatchers[AST] {
  override val parser = TreeParser.apply _

  describe ("A person!") {
    program (
      """
      Fred
      """
    ) should parseAs (
      Graph(
        Set(
          Self(
            Person("Fred"), Person("Fred"), "self"
            )
          )
        )
      )
  }

  describe ("Some people!") {
    program (
      """
      Fred
      Freddy
      Fredina
      """
    ) should parseAs (
      Graph(
        Set(
          Self(Person("Fred"), Person("Fred"), "self"),
          Self(Person("Freddy"), Person("Freddy"), "self"),
          Self(Person("Fredina"), Person("Fredina"), "self")
          )
        )
      )
  }

  describe ("A child and a parent!") {
    program (
      """
      Fred is child of Freddy
      Fredina is parent of Freddy
      """
    ) should parseAs (
      Graph(
        Set(
          Parent(Person("Fredina"), Person("Freddy"), "parent"),
          Child(Person("Fred"), Person("Freddy"), "child")
          )
        )
      )
  }

}
