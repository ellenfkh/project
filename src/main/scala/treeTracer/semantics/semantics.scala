package treeTracer.semantics
import treeTracer.parser.TreeParser

import treeTracer.ir._
import scala.io.Source


package object TreeSemantics {
  def eval(ast:AST, graph:Map[Person, Set[Edge]]):Map[Person,Set[Edge]] = ast match {
    case x:Edge => addToMap(x, graph)
    case x:Help => {
      println("commands available are:")
      println("===> load <file>")
      println("===> <name>")
      println("===> delete <name>")
      println("===> <name> is <relationship> to <name>")
      println("===> who is <name> to <name>")
      graph
    }
    case q:XtoY => {
      flatSearch(q.x, q.y, graph)
      graph
    }
    case x:WhoIsX => {
      whois(x.x, graph)
      graph
    }
    case x:Load => loadFile(x, graph)
    case x:Delete => delete(x.person, graph)
    case _ => {
      println("you did something really weird?")
      graph
    }
  }

  def delete(del:Person, graph:Map[Person,Set[Edge]]):Map[Person,Set[Edge]] = {
    var g:Map[Person,Set[Edge]] = graph - del

    for ((person, edges) <- g) {
      var newEdges = edges
      for (edge <- edges) {
        if (edge.other == del) {
          newEdges = newEdges - edge
        }
      }
      g = g - person + (person -> newEdges)
    }
    g
  }

  def whois(x:Person, graph:Map[Person,Set[Edge]]) = graph.get(x) match {
    case Some(edges) => printRelList(edges)
    case None => println("no such person")
  }

  def flatSearch(x:Person, y:Person, graph:Map[Person,Set[Edge]]) = {
    val self = x.name
    val other = y.name
    var rels = Set.empty[Edge]

    graph.get(x) match {
      case Some(xRels) => {
        for (edge <- xRels) {
          if (edge.other == y) {
            rels = rels + edge
          }
        }
      }
      case None => {
        println(s"No person named $self")
      }
    }

    if (rels.isEmpty) {
      println(s"No relation between $self and $other")
    } else {
      for (e <- rels) {
        val r = e.rel.rel
        println(s"$self is $r of $other")
      }
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
      printGraph(g)
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
