package treeTracer.ir

sealed abstract class AST
sealed abstract class Edge extends AST
sealed abstract class Query extends AST

case class Help() extends AST

case class Person(val name:String) extends AST

case class Self(val self1:Person, val self2:Person, val rel:String) extends Edge
case class Child(val self:Person, val parent:Person, val rel:String) extends Edge
case class Parent(val self:Person, val child:Person, val rel:String) extends Edge
case class Rel(val rel:String) extends Edge

case class XtoY(val x:Person, y:Person) extends Query


// TODO: turn this into a node-based graph instead of edge based thing
// TODO: search paths
// TODO: strecth: do webthings
