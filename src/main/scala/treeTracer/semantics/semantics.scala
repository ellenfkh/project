package treeTracer.semantics

import treeTracer.ir._

package object TreeSemantics {
  def eval(graph: Graph) = graph match {
    // FIXME having problems with immutable sets?
    case Graph(edges) => {
      var edgeCpy:Set[Edge] = Set.empty[Edge]
      edges.foreach(x => makeReciprocal(x, edgeCpy))
      edges = edges ++ edgeCpy
    }
  }

  def makeReciprocal(edge: Edge, edgeSet: Set[Edge]) = edge match {
    case Child(child, parent, "child") => {
      edgeSet = edgeSet + Parent(parent, child, "parent")
    }
    case Parent(parent, child, "child") => {
      edgeSet = edgeSet + Child(child, parent, "child")
    }
  }
}
