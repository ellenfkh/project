package treeTracer.ir

sealed abstract class AST
sealed abstract class Query extends AST
sealed abstract class Edge extends AST {
  val self:Person
}

case class Help() extends AST

case class RelationsList(val rels:List[Edge]) extends AST
case class Person(val name:String) extends AST

case class Self(val self:Person, val other:Person, val rel:String) extends Edge
case class Child(val self:Person, val other:Person, val rel:String) extends Edge
case class Parent(val self:Person, val other:Person, val rel:String) extends Edge
//case class Rel(val rel:String) extends Edge

case class XtoY(val x:Person, y:Person) extends Query


// TODO: turn this into a node-based graph instead of edge based thing
// TODO: search paths
// TODO: strecth: do webthings
