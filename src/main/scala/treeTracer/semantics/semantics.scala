package treeTracer.semantics

import treeTracer.ir._

package object TreeSemantics {
  def eval(graph: Graph) = graph match {
    case Graph(edges) => {
      var currentEdgeSet:Set[Edge] = edges;

      currentEdgeSet = currentEdgeSet ++ makeReciprocal(edges)

    }
  }

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
}
