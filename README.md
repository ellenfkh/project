# Tree-Tracer

A DSL for keeping track of (family) trees.

## To build:

Run `sbt run` in the root directory of this repo.

You should get this prompt: `===>`.  Type `help` for a list of available
commands.

Files can be loaded into the universe with the `load` command; they must have
extension `.txt` and be in the `/resources/` directory.  Don't include the
directory or the extension; for instance, to load the file
`/resources/fred.txt`, the command is `load fred`.
