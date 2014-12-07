package treeTracer.parser

import org.scalatest._
import treeTracer.parser._
import treeTracer.ir._
import edu.hmc.langtools._

class TreeParserTest extends FunSpec with LangParseMatchers[AST] {
  override val parser = TreeParser.apply _

  describe ("A cry for help!") {
    program (
      """
      help
      """
    ) should parseAs (
      Help()
    )
  }

  describe ("A load!") {
    program (
      """
      load foo
      """
    ) should parseAs (
      Load("foo")
    )
  }

  describe ("A delete!") {
    program (
      """
      delete foo
      """
    ) should parseAs (
      Delete(Person("foo"))
    )
  }

  describe ("A person!") {
    program (
      """
      Fred
      """
    ) should parseAs (
      Edge( Person("Fred"), Person("Fred"), Relationship("self"))
    )
  }

  describe ("A relationship!") {
    program (
      """
      Fred is child of Freddy
      """
    ) should parseAs (
      Edge(Person("Fred"), Person("Freddy"), Relationship("child"))
    )
  }

  describe ("A query!") {
    program (
      """
      who is Fred to Freddy
      """
    ) should parseAs (
      XtoY(Person("Fred"), Person("Freddy"))
    )
  }
}
