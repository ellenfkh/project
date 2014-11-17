package treeTracer.ir

sealed abstract class AST
sealed abstract class Edge extends AST

case class Graph(val edges:Set[Edge]) extends AST

case class Person(val name:String) extends AST

case class Self(val self1:Person, val self2:Person, val rel:String) extends Edge
case class Child(val self:Person, val parent:Person, val rel:String) extends Edge
case class Parent(val self:Person, val child:Person, val rel:String) extends Edge
