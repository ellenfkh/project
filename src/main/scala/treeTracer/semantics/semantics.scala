package treeTracer.semantics
import treeTracer.parser.TreeParser

import treeTracer.ir._
import scala.io.Source


package object TreeSemantics {
  def eval(ast:AST, graph:Map[Person, Set[Edge]]):Map[Person,Set[Edge]] = ast match {
    case x:Edge => addToMap(x, graph)
    case x:Help => {
      println("commands available are:")
      println("===> <name>")
      println("===> load <file>")
      println("===> <name> is child of <name>")
      println("===> <name> is parent of <name>")
      println("===> who is <name> to <name>")
      graph
    }
    case q:Query => {
      println("you asked something but I can't handle that yet");
      print(q.x.name + q.y.name)
      graph
    }
    case x:Load => loadFile(x, graph)
    case _ => {
      println("you did something really weird?")
      graph
    }
  }



  def loadFile(load:Load, graph:Map[Person,Set[Edge]]):Map[Person,Set[Edge]] = {
    var g:Map[Person, Set[Edge]] = graph

    try {
      var readFile = io.Source.fromFile("resources/" + load.file + ".txt")
      // Loop through lines in file and parse them in
      for(line <- readFile.getLines()) {
        TreeParser(line) match {
          case TreeParser.Success(t, _) => {
            g = eval(t, g)
          }
          case e: TreeParser.NoSuccess  => println(e)
        }
      }
      g

      // If user gives a nonexistent file
    } catch {
      case e:java.io.FileNotFoundException =>
      println("file " + load.file + " not found")
      return graph
    }
  }

  def addToMap(e:Edge, graph:Map[Person,Set[Edge]]):Map[Person,Set[Edge]] = {
    e match {
      case x:Self => graph.get(e.self) match {
          case None => graph + (e.self -> Set.empty[Edge])
          case _ => graph
        }
      case x:Edge => graph.get(e.self) match {
          case None => graph + (e.self -> (Set.empty[Edge] +e))
          case Some(edges:Set[Edge]) => (graph - e.self) + (e.self -> (edges + e))
        }
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
