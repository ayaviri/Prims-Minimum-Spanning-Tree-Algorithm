welcome to my implementation of prim's minimum spanning tree algorithm !
this algorithm takes in as input a graph that is weighted, undirected, simple, and connected. it is
assumed that all edges weights are distinct for purposes of simplification. edge weights can be
positive or negative. given this graph, prim's algorithm then outputs a subset of the edges in the
graph such that these edges form a spanning tree of minimum cost.

for further reference on prim's minimum spanning tree algorithm, this video from william fiset is
very helpful!:https://www.youtube.com/watch?v=jsmMtJpPnhU

in this implementation, i used a priority queue in order to improve the runtime from O(n^2) to
O(mlogn). using fibonacci heaps, a faster asymptotic runtime can be achieved. as for documentation,
in order to view the javadocs for this code, an IDE such as intellij can generate the necessary html
files in order to view properly formatted javadocs. in addition to this, there is a suite of tests
that was written using JUnit 4, so this must be added to the classpath in order to run these tests.

