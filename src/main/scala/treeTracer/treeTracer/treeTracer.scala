package treeTracer

import scala.tools.nsc.EvalLoop
import treeTracer.parser.TreeParser
import treeTracer.ir._
import treeTracer.semantics.TreeSemantics.eval

object TreeLoop extends EvalLoop with App {
  override def prompt = "===> "

  var edgeGraph:Set[Edge] = Set.empty[Edge]

  loop { line => TreeParser(line) match {
      case TreeParser.Success(t, _) => {
        edgeGraph = eval(t, edgeGraph)
        println("Everybody currently in the world:")
        // TODO: write a prettier printing function for this.
        println(edgeGraph)
      }
      case e: TreeParser.NoSuccess  => println(e)
    }
  }
}
