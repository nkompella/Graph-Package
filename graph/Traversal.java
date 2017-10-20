package graph;

/* See restrictions in Graph.java. */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Queue;

/** Implements a generalized traversal of a graph.  At any given time,
 *  there is a particular collection of untraversed vertices---the "fringe."
 *  Traversal consists of repeatedly removing an untraversed vertex
 *  from the fringe, visting it, and then adding its untraversed
 *  successors to the fringe.
 *
 *  Generally, the client will extend Traversal.  By overriding the visit
 *  method, the client can determine what happens when a node is visited.
 *  By supplying an appropriate type of Queue object to the constructor,
 *  the client can control the behavior of the fringe. By overriding the
 *  shouldPostVisit and postVisit methods, the client can arrange for
 *  post-visits of a node (as in depth-first search).  By overriding
 *  the reverseSuccessors and processSuccessor methods, the client can control
 *  the addition of neighbor vertices to the fringe when a vertex is visited.
 *
 *  Traversals may be interrupted or restarted, remembering the previously
 *  marked vertices.
 *  @author Neha Kompella
 */
public abstract class Traversal {

    /** A Traversal of G, using FRINGE as the fringe. */
    protected Traversal(Graph G, Queue<Integer> fringe) {
        _G = G;
        _fringe = fringe;
        vertexList = new ArrayList<Integer>();
        postvisited = new ArrayList<Integer>();
    }

    /** Unmark all vertices in the graph. */
    public void clear() {
        vertexList = new ArrayList<Integer>();
    }

    /** Initialize the fringe to V0 and perform a traversal. */
    public void traverse(Collection<Integer> V0) {
        _fringe.addAll(V0);
        while (!_fringe.isEmpty()) {
<<<<<<< HEAD
            Integer c = _fringe.remove();
            if (!marked(c)) {
                mark(c);
                if (!visit(c)) {
                    break;
                }
                _fringe.add(c);
                for (Integer v : _G.successors(c)) {
                    if (processSuccessor(c, v)) {
=======
<<<<<<< HEAD
            int checkMe = _fringe.remove();
            if (!marked(checkMe)) {
                mark(checkMe);
                visit(checkMe);
                _fringe.add(checkMe);
                for (int v : _G.successors(checkMe)) {
                    if (!marked(v)) {
=======
            Integer checkMe = _fringe.remove();
            if (!marked(checkMe)) {
                mark(checkMe);
                if (!visit(checkMe)) {
                    break;
                }
                _fringe.add(checkMe);
                for (Integer v : _G.successors(checkMe)) {
                    if (processSuccessor(checkMe, v)) {
>>>>>>> 821d2daa8a96c320d43ea4658eaa5a5a585f354e
>>>>>>> f974b6c5c0a8ddd036dcdf680958a8f784dceb3f
                        _fringe.add(v);
                    }
                }
            } else {
<<<<<<< HEAD
                if (!postvisited.contains(c)) {
                    if (vertexList.contains(c) && shouldPostVisit(c)) {
                        postVisit(c);
                        postvisited.add(c);
=======
                if (!postvisited.contains(checkMe)) {
<<<<<<< HEAD
                    if (vertexList.contains(checkMe)) {
=======
                    if (vertexList.contains(checkMe) && shouldPostVisit(checkMe)) {
>>>>>>> 821d2daa8a96c320d43ea4658eaa5a5a585f354e
                        postVisit(checkMe);
                        postvisited.add(checkMe);
>>>>>>> f974b6c5c0a8ddd036dcdf680958a8f784dceb3f
                    }
                }
            }
        }
<<<<<<< HEAD
=======
        _fringe.clear();
>>>>>>> 821d2daa8a96c320d43ea4658eaa5a5a585f354e
    }

    /** Initialize the fringe to { V0 } and perform a traversal. */
    public void traverse(int v0) {
        traverse(Arrays.<Integer>asList(v0));
    }

    /** Returns true iff V has been marked. */
    protected boolean marked(int v) {
        return vertexList.contains(v);
    }

    /** Mark vertex V. */
    protected void mark(int v) {
        vertexList.add(v);
    }

    /** Perform a visit on vertex V.  Returns false iff the traversal is to
     *  terminate immediately. */
    protected boolean visit(int v) {
        return true;
    }

    /** Return true if we should postVisit V after traversing its
     *  successors.  (Post-visiting generally is useful only for depth-first
     *  traversals, although we define it for all traversals.) */
    protected boolean shouldPostVisit(int v) {
        return false;
    }

    /** Revisit vertex V after traversing its successors.  Returns false iff
     *  the traversal is to terminate immediately. */
    protected boolean postVisit(int v) {
        return true;
    }

    /** Return true if we should schedule successors of V in reverse order. */
    protected boolean reverseSuccessors(int v) {
        return false;
    }

    /** Process successor V to U.  Returns true iff V is then to
     *  be added to the fringe.  By default, returns true iff V is unmarked. */
    protected boolean processSuccessor(int u, int v) {
        return !marked(v);
    }

    /** The graph being traversed. */
    private final Graph _G;
    /** The fringe. */
    protected final Queue<Integer> _fringe;
    /** List of vertexes to be traversed. */
    private ArrayList<Integer> vertexList;
    /** List of vertex indices already visited. */
    private ArrayList<Integer> postvisited;

}
