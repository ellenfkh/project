package treeTracer.ir

sealed abstract class AST
sealed abstract class Query extends AST {
  val x:Person
  val y:Person
}

case class Help() extends AST

case class Person(val name:String) extends AST
case class Relationship(val rel:String) extends AST

case class Load(val file:String) extends AST
case class Delete(val person:Person) extends AST
case class Edge(val self:Person, val other:Person, val rel:Relationship) extends AST

case class XtoY(val x:Person, y:Person) extends Query

// TODO: search paths
// TODO: strecth: do webthings
