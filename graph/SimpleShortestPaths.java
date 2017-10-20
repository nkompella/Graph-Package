package graph;

/* See restrictions in Graph.java. */

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;

/** A partial implementation of ShortestPaths that contains the weights of
 *  the vertices and the predecessor edges.   The client needs to
 *  supply only the two-argument getWeight method.
 *  @author Neha Kompella
 */
public abstract class SimpleShortestPaths extends ShortestPaths {

    /** The shortest paths in G from SOURCE. */
    public SimpleShortestPaths(Graph G, int source) {
        this(G, source, 0);
    }

    /** A shortest path in G from SOURCE to DEST. */
    public SimpleShortestPaths(Graph G, int source, int dest) {
        super(G, source, dest);
        _LG = new LabeledGraph<Double, Double>(G);
        predecessors = new ArrayList<>();
        for (int i = 0; i < _LG.maxVertex() + 1; i++) {
            predecessors.add(null);
            if (_LG.contains(i)) {
                _LG.setLabel(i, Double.MAX_VALUE);
            }
        }
    }

    /** Returns the current weight of edge (U, V) in the graph.  If (U, V) is
     *  not in the graph, returns positive infinity. */
    @Override
    protected abstract double getWeight(int u, int v);

    @Override
    public double getWeight(int v) {
        if (_LG.contains(v)) {
            return _LG.getLabel(v);
        } else {
            return Double.POSITIVE_INFINITY;
        }
    }

    @Override
    protected void setWeight(int v, double w) {
        _LG.setLabel(v, w);
    }

    @Override
    public int getPredecessor(int v) {
        if (predecessors.get(v) == null) {
            return 0;
        } else {
            return predecessors.get(v);
        }
    }

    @Override
    protected void setPredecessor(int v, int u) {
        predecessors.set(v, u);
    }

    LabeledGraph<Double, Double> _LG;
    ArrayList<Integer> predecessors;
}
