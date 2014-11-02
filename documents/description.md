# Project description and plan

## Motivation

When reading a book series, watching a TV show, or keeping track of one`s own
family tree, there are a lot of people and characters to keep track of.  Many of
these characters will be connected, most obviously by way of familial
relationships, and these connections are often of interest --- obviously in the
case of someone keeping a family tree, and because family connections are often
integral to the plot of many stories.

However, in large casts and families, it quickly becomes difficult to keep track
of who is related to who, and in what manner.  In particular, it becomes even
more difficult when non-familial relationships are also added --- for instance,
if we take a long-running, complex book series like George R. R. Martin`s *A
Song of Ice and Fire*, just keeping track of the family relationships is hard
enough.  If we also wanted to track lord-vassal relatioships, for instance, a
character might be related to another through many subtrees.  They might be
distantly but directly related, and also linked many ways by combinations of
blood and my-cousin-is-your-cousin`s-vassal.

This task could be made easier by using a DSL with a nice syntax, or better yet,
a nice UI, for specifying relationships.  A separate DSL for querying this
forest of relationship trees could then be used to extract information, yielding
output that tells the user *A is related to B in this manner*.

## Language domain

The domain for the DSL will be the tracking of multiple complicated
family-relationship trees.  I see it being useful for people attempting to keep
track of characters in books, or for tracing family trees.  If the language is
extended such that new relationships could be defined by the user, then the
domain is much larger and will encompass the tracking of any sort of
interrelated things.  Another general language that encompasses this domain is
Prolog --- I intend to draw on Prolog`s data model of depth-first-searching the
data model built up by the relationship declarations when queried.  However, I
hope to make my syntax more intuitive than Prolog`s.

## Language design

**Family tree builder and query engine.**

A program in this language would look like a series of declarations followed by
a series of queries. When run, the program will build a forest of trees based on
the declarations, and then search through the tree when queried.  Syntax errors
should give compile-time errors and log the offending line.  There should be no
run time errors.  As this is a fairly limited DSL, logging the error line should
be sufficient.

## Example computations

### Example Program:
```
// Declarations
Foo is the son of Bar and Baz
Alice is the daughter of Bob and Charlie
Foolice is the daughter of Alice and Foo

// Queries
Foolice and Alice
Foo and Bob
Foolice and Baz
```

### Example Output:
Foolice is the daughter of Alice
Foo and Bob are not related
Foolice is the daughter of Foo and Foo is the son of Baz


