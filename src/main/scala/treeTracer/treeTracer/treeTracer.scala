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
        println("Everybody currently in the world:")
        // TODO: write a prettier printing function for this.
        //println(graph)
        printGraph(graph)
      }
      case e: TreeParser.NoSuccess  => println(e)
    }
  }

  def printGraph(graph:Map[Person, Set[Edge]]) = {
    for ((person, relationships) <- graph) {
      val name:String = person.name
      println(s"$name has relationships:")
      printRelList(relationships)
    }
  }

  def printRelList(relations:Set[Edge]) = {
    for (edge <- relations) {
      val reltype:String = edge.rel
      val name:String = edge.other.name
      println(s"    $reltype : $name")
    }
  }


}
