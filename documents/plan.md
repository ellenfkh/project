# Project plan

## Language evaluation

When the language allows me to specify familial relationships of reasonable
complexity, for multiple families (some people may not be related at all), and
all valid queries either return all ways in which two people are related or
returns that they are not related, the base language will be done.

A stretch goal might be implementing more relationships, or possibly the ability
for the user to define more relationships.

I will rely on class critiques and common sense to hopefully ensure a good
quality implementation.

## Implementation plan

I`m planning to implement this DSL in Scala, so host language found!  Hooray.

Implementing the intermetiate representation should be the first step.  It will
just be a collection of trees, so this shouldn`t take much time --- I expect
about eight hours for implementation and tests.

This will be an external DSL, so the next step will be the parser.  The language
should be pretty easy to parse using parser combinators, since valid commands
are only of two forms, so the parser should take another eight hours or so.

The last step will be the semantic engine.  The semantics are also fairly simple
--- one class of commands adds to the IR, and the other class of commands does a
search over the IR.  The searching might be more difficult, so I think this may
take about ten hours.

Therefore, my tentative schedule is as follows:

November 2 - November 8:    Implement IR and start parser (includes deciding on a
                            syntax).
November 8 - November 15:   Finish parser and start semantics.
November 15 - November 22:  Finish semantics. (Prototype)

November 22 - December 12:  If I stayed on schedule, start implementing extra
                            features, possibly including:
* user-defined relationships
* more predefined relationships
* a GUI
