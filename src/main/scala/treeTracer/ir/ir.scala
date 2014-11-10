package treeTracer.ir

sealed abstract class AST
sealed abstract class Person extends AST

case class Forest(val people:List[Person]) extends Person

case class Node(val name:String) extends Person
case class Child(val name:String, val parent1:Person, val parent2:Person) extends Person

