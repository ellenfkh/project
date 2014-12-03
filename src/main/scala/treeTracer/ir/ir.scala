package treeTracer.ir

sealed abstract class AST
sealed abstract class Query extends AST {
  val x:Person
  val y:Person
}
sealed abstract class Edge extends AST {
  val self:Person
  val other:Person
  val rel:String
}

case class Help() extends AST

case class Person(val name:String) extends AST
case class Load(val file:String) extends AST

case class Self(val self:Person, val other:Person, val rel:String) extends Edge
case class Child(val self:Person, val other:Person, val rel:String) extends Edge
case class Parent(val self:Person, val other:Person, val rel:String) extends Edge

case class XtoY(val x:Person, y:Person) extends Query

// TODO: search paths
// TODO: strecth: do webthings
