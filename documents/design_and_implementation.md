# Language design and implementation overview

## Language design
I envision a program in this language taking the form of either a series of
line-separated text commands, probably through an event loop, or through a GUI.
The GUI probably won't be part of the class; it would take too much time and
isn't the design part of the language.  I also want it to be possible to "load"
in text files with valid programs in them, probably with a "load" command in the
event loop.

The dsl will take imperative input statements and build a graph, represented by
a list of directed edges, and may do some basic computation while it's building
the graph (ie. making recirpocal edges).  There is no control flow, it's all
just a list of executable statements.   The user can make queries about the
graph, passing in two nodes (names?) and getting back out the relationship
subtree(s) between those two nodes.

This part will require (maybe?) some fancy graph algorithms. At the very least,
I'll need to find all (or maybe just shortest) paths between the two inputs, and
then iterate over those paths to compress the relationships down into something
more compact, possibly user-defined (ie. two parent edges in a row turns into a
generated grandparent edge).

Parse erros will be handled by the parser throwing "failed to parse <what I
expected to see> errors", which is not ideal but I think fairly reasonable.
There is no other tool support -- it's such a simple language that the only
syntax errors I can think of would be typos, and I don't think it's possible to
have semantic errors -- if you type something in that doesn't make sense, the
graph might be oddly malformed, but it will be valid.

Prolog is a general-purpose language that seems pretty close to this domain.  I
hope to make this DSL easier to use, but much more limited in scope than Prolog.

## Language implementation

I made the language external because I expected the target audience to be not
necessarily experienced at normal programming languages, and therefore an
external dsl with a very limited vocabulary set might be easier for such a user.
The host language is Scala because scala parser-combinators are awesome, and
because we've been using it and have templates for it.

The syntax is pretty simple, so much that the design of it is more or less
trivial.

The general data flow will be:
* user gives a declaration defining either an individual person or a
relationship
* parser makes that person/both people if necesssary, defines an edge, and adds
it to the graph.

or

* user makes a query about two people
* parser and formulates the query and hands it to the semantic portion
* semantics evaluator searches through the graph, possibly in different ways
depending on the query, and spits back out a pruned/curated subtree in its
string representation form
* the subtree is displayed to terminal

The display part might change if I make a GUI for this.
