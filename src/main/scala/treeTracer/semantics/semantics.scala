package treeTracer.semantics

import treeTracer.ir._

package object TreeSemantics {
  def eval(ast:AST, graph:Map[Person, Set[Edge]]):Map[Person,Set[Edge]] = ast match {
    case x:Edge => addToMap(x, graph)
    case x:Help => {
      println("commands available are:")
      println("===> <name>")
      println("===> <name> is child of <name>")
      println("===> <name> is parent of <name>")
      println("===> who is <name> to <name>")
      graph
    }
    case x:Query => {
      println("you asked something but I can't handle that yet");
      graph
    }
    case _ => {
      println("you did something really weird?")
      graph
    }
  }

  def addToMap(e:Edge, graph:Map[Person,Set[Edge]]):Map[Person,Set[Edge]] = {
    graph.get(e.self) match {
      case None => graph + (e.self -> (Set.empty[Edge] +e))
      case Some(edges:Set[Edge]) => (graph - e.self) + (e.self -> (edges + e))
    }
  }



  // TODO: write a load

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
