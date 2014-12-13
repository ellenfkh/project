package treeTracer

import scala.tools.nsc.EvalLoop
import treeTracer.parser.TreeParser
import treeTracer.ir._
import treeTracer.semantics.TreeSemantics.eval

object TreeLoop extends EvalLoop with App {
  override def prompt = "===> "

  var graph:Map[Person, Set[Edge]] = Map.empty[Person, Set[Edge]]

  loop { line => TreeParser(line) match {
      case TreeParser.Success(t, _) => {
        graph = eval(t, graph)
        //printGraph(graph)
      }
      case e: TreeParser.NoSuccess  => println(e)
    }
  }

  def printGraph(graph:Map[Person, Set[Edge]]) = {
    println()
    println("======================")
    println("== Current Universe ==")
    println("======================")
    for ((person, relationships) <- graph) {
      val name:String = person.name
      println(s"$name is:")
      printRelList(relationships)
    }
    println("======================")
  }

  def printRelList(relations:Set[Edge]) = {
    for (edge <- relations) {
      val reltype:String = edge.rel.rel
      val name:String = edge.other.name
      println(s"    $reltype of $name")
    }
  }


}
