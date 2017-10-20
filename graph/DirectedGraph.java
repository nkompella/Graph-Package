package graph;
import java.util.ArrayList;
import java.util.LinkedList;

/* See restrictions in Graph.java. */

/** Represents a general unlabeled directed graph whose vertices are denoted by
 *  positive integers. Graphs may have self edges.
 *
 *  @author Neha Kompella
 */
public class DirectedGraph extends GraphObj {

    @Override
    public boolean isDirected() {
        return true;
    }

    @Override
    public int inDegree(int v) {
<<<<<<< HEAD
        int count = 0;
        for (int i = 0; i < vertexSize(); i++) {
            if(getSuccessorData(i).contains(v)) {
                count++;
            }
        }
        return count;
=======
        if (contains(v)) {
            return getPredecessorData(v).size();
        } else {
            return 0;
        }
>>>>>>> 821d2daa8a96c320d43ea4658eaa5a5a585f354e
    }

    @Override
    public int predecessor(int v, int k) {
        if (!contains(v)) {
            return 0;
        }
        if (k >= getPredecessorData(v).size() || k < 0) {
            return 0;
        }
        Vertex a = getPredecessorData(v).get(k);
        return getVertexNumber(a);
    }

    @Override
    public Iteration<Integer> predecessors(int v) {
        if (!contains(v)) {
            return Iteration.iteration(new ArrayList<Integer>());
        } else {
            LinkedList<Vertex> checkMe = getPredecessorData(v);
            LinkedList<Integer> numbers = new LinkedList<>();
            for (int i = 0; i < checkMe.size(); i++) {
                numbers.add(getVertexNumber(checkMe.get(i)));

            }
            return Iteration.iteration(numbers);
        }
    }

    // FIXME

}
