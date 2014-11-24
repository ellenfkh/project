package treeTracer.semantics

import treeTracer.ir._

package object TreeSemantics {
  def eval(ast:AST, graph:Set[Edge]):Set[Edge] = ast match {
    case x:Edge => graph + x
    case x:Query => {
      println("you asked something but I can't handle that yet");
      graph
    }
    case _ => {
      println("you did something really weird?")
      graph
    }
  }

  /*
   // TODO: add this back in once querying works
  def makeReciprocal(edges: Set[Edge]) = {
    var tmpEdges:Set[Edge] = Set.empty[Edge]

    for (edge <- edges) {
      val maybeRecip:Option[Edge] = makeReciprocalEdge(edge)
      maybeRecip match {
        case None => ;
        case Some(x) => {tmpEdges = tmpEdges + x}
      }
    }
    tmpEdges
  }

  def makeReciprocalEdge(edge: Edge): Option[Edge] = edge match {
    case Child(child, parent, "child") => Some(Parent(parent, child, "parent"))
    case Parent(parent, child, "child") => Some(Child(child, parent, "child"))
    case _ => None
  }
  */
}
