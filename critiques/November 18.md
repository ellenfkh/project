First off, I like this project. It seems it could be a really useful way of keeping track of relationships.

As for what you need to implement next, I completely agree with you. For now, I would focus on getting the querying system up so you can ask the database for results. After that, you can work on relationships. Definitely think about adding in new relationships soon though, since that'll be the backbone of the project. 

As for who handles Child(Freddy, Fred) => Parent(Fred, Freddy), I would say that this should be handled only by the interpreter. The parser should add the relationships you explicitly specify only. After that, the interpreter can build the graph and figure out the more complex relationships, like the one you mentioned about a child of a child being a grandchild.

I wish I knew more about graph algorithms, but I would be there is a way to handle this cleanly! Naively, you could do a pass through all possible relations to determine whose child of a child is a grandchild, given any child relationship, but there's probably a way better way to do this. If you could make it a bidirectional graph where the direction dictates parent vs. child relationship, that could be quite useful too. Not sure how feasible any of this is though.

Queries I would want is asking relationships between two individuals, asking "Who is the X of Fred?" where X is "Child", etc, and asking "What is Fred to everyone else?" which would return all connections to Fred. You also could add the inverse of both of these, "Who is Fred an X to?" or "What is everyone's relationship to Fred?" 
