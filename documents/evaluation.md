# Preliminary evaluation

So far, I have the parser and eval loop working.  I think I'll have to redo the
IR in order to have the semantics I want, but at least the user-facing part of
the DSL is unlikely to change.  I like the decision to move to an eval loop
instead of handing in a text file --- I feel like my DSL is something that
should be real-time queryable.

I think the main thing I need to change is the IR and the semantics.  I need to
get queries working, which means probably redoing the IR to be a node-based
representation instead of an edge-based representation.

The tests and critiques really haven't done much for me --- my tests are
basically "does this work", and most of the critiques I've gotten weren't too
helpful.  Last week's (UI testing) was helpful, but the others were rarely
in-depth enough to help.

I ran into trouble in that my DSL had more moving parts than I thought it would
have.  I had to redesign my IR twice already, and I have to now do it again.
My parser was originally designed to accept a whole program, and now it has to
deal with eval loops.  I thought it would be easy to extend from declartions to
queries, but the two had to be handled very differently, and were impossible to
deal with without changing the IR.  Basically, I underestimated how hard a lot
of the parts were, and my initial design was not careful enough.

I need to:
* redo IR to be node-based
* make basic queries work
* do path compression
* Implement "load from file" function
* make a big example file to load as demo
