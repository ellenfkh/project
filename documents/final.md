##Introduction

This DSL was made mostly for people like myself, who want to keep track of
complicated relationship trees including many characters.  Namely, I intended
this DSL to be useful for reading long, complex book series, in which there are
many characters whose relationship to one another drives the plot.  Because such
series tend to be very long, it's easy to lose track or forget about characters,
and then be confused when they are mentioned again.  This DSL is for keeping
track of them.

##Language design details
This DSL has a very simple syntax, composed of declarations and queries.
Declarations are of the form `fred is friend of freddy`, where the relationship
specified is anything.  Quereies are of the form `who is fred to freddy`.
Declarations cause relationships to be stored in an internal graph, which is
then searched when queried.  This all takes place in a read-eval-print loop,
though text files (.txt) can be loaded into the program using the `load`
command, which allows the user to avoid typing in lots of declarations many
times.  There isn't any error checking, since the program goes line by line, but
the error messages are sane, and the program won't crash on syntax errors.  A
similar language to this DSL is Prolog, which is much more general.  It's also
harder to use, so this DSL aims to be a simpler slice of Prolog for people who
might not have too much programming experience.

##Example program
```
Fred is child of Freddy
Freddy is child of Fredina
Fred is friend of Frederick
Frederick is child of Freida
Frieda is child of Fredina
who is Fred to Freddy
who is Freddy to Fredina
who is Freida to Fredina
```

##Implementation
I chose to use Scala to implement this external DSL.  It was a bit of a mistake;
I thought my project would be a lot simpler than it was, so I should take the
opportunity to work with a language I wasn't too familiar with, and learn Scala
better.  Since my problem was pretty simple, I figured that the host language
wouldn't matter much, and it didn't; it was just difficult because I wasn't good
at Scala.

My program has an IR, a parser, some semantics, and a eval loop.  The IR is very
simple, just composed of some definitions for edges and nodes in my relationship
graph.  The parser is also simple, and very flat; there can only be a few
classes of commands, so it's very simple case class matching.  The eval loop
just runs the program, waits for a line to be entered, and sends it through the
parser and the semantics.

The semantics are by far the most complicated part of this DSL.  Upon getting
a parsed something from the eval loop, the semantics matches on whether the
command is a query or a declaration.  If it's a declaration, the semantics
updates the internal graph and passes it back to the eval loop, which is in
charge of keeping it.  If it's a query, the semantics performs a
breadth-first-search over the graph, and prints out the edges of the resulting
path.

##Evaluation
My language is pretty DSL-y.  It's not at all general purpose.  I'm pretty
pleased with the final IR I have, and how simple it is.  I also like the parser,
for the same reason.  I'm also happy with my decision to move to a
read-eval-print loop.  I wish I'd had time to implement path folding (if the
query yields that Fred is child of Freddy is child of Fredina, fold that
to Fred is grandchild of Fredina) or reciprocal relations (if Fred is child of
Freddy, then Freddy is parent of Fred).  I also would have liked to have a GUI
of some kind, possibly web-based.

The main things that changed were implementation details; mostly, I kept writing
something only to realize that I'd have to completely redo it or the next step
wouldn't work.  I used tests, but since my semantics were pretty complicated, I
couldn't figure out how to unit test them; the tester example we had been using
only accepted an int as an output, but I was returing a map.

I ran into a lot of trouble with my IR.  I made it a lot more complicated than I
should have, and then correspondlingly had trouble with the parser.  I think
once I managed to wrap my head around the split between declarations and queries
I was fine, but that was pretty late in the semester.

I worked alone.
