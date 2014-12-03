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
      println("===> <name> is <relationship> to <name>")
      println("===> who is <name> to <name>")
      graph
    }
    case q:Query => {
      //println("you asked something but I can't handle that yet");
      flatSearch(graph, q.x, q.y)
      graph
    }
    case x:Load => loadFile(x, graph)
    case _ => {
      println("you did something really weird?")
      graph
    }
  }

  def flatSearch(graph:Map[Person,Set[Edge]], x:Person, y:Person):Int= {
    val xRels = graph.get(x).get
    val self = x.name
    val other = y.name

    for (edge <- xRels) {
      if (edge.other == y) {
        val rel = edge.rel.rel
        println(s"$self has $rel : $other")
        return 1
      }
    }
    println(s"No relation between $self and $other")
    return 0
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
    if (e.rel.rel =="self") {
      graph.get(e.self) match {
        case None => graph + (e.self -> Set.empty[Edge])
        case _ => graph
      }
    } else {
      graph.get(e.self) match {
        case None => graph + (e.self -> (Set.empty[Edge] +e))
        case Some(edges:Set[Edge]) => (graph - e.self) + (e.self -> (edges + e))
      }
    }
  }
}
