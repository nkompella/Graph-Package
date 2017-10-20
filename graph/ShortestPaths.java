package graph;

import java.util.List;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.AbstractQueue;
import java.util.TreeSet;
import java.util.Iterator;

/**
 * The shortest paths through an edge-weighted graph.
 * By overrriding methods getWeight, setWeight, getPredecessor, and
 * setPredecessor, the client can determine how to represent the weighting
 * and the search results.  By overriding estimatedDistance, clients
 * can search for paths to specific destinations using A* search.
 * @author Neha Kompella
 */
public abstract class ShortestPaths {

    /**
     * Implements traversal.
     */
    private PathTraverse pt;

    /**
     * The shortest paths in G from SOURCE.
     */
    public ShortestPaths(Graph G, int source) {
        this(G, source, 0);
    }

    /**
     * A shortest path in G from SOURCE to DEST.
     */
    public ShortestPaths(Graph G, int source, int dest) {
        _G = G;
        _source = source;
        _dest = dest;
    }

    /**
     * Initialize the shortest paths.  Must be called before using
     * getWeight, getPredecessor, and pathTo.
     */
    public void setPaths() {
        pt = new PathTraverse();
        setWeight(_source, 0);
        setPredecessor(_source, 0);
        pt.traverse(_source);
        int k = 0;
    }

    /**
     * Returns the starting vertex.
     */
    public int getSource() {
        return _source;
    }

    /**
     * Returns the target vertex, or 0 if there is none.
     */
    public int getDest() {
        return _dest;
    }

    /**
     * Returns the current weight of vertex V in the graph.  If V is
     * not in the graph, returns positive infinity.
     */
    public abstract double getWeight(int v);

    /**
     * Set getWeight(V) to W. Assumes V is in the graph.
     */
    protected abstract void setWeight(int v, double w);

    /**
     * Returns the current predecessor vertex of vertex V in the graph, or 0 if
     * V is not in the graph or has no predecessor.
     */
    public abstract int getPredecessor(int v);

    /**
     * Set getPredecessor(V) to U.
     */
    protected abstract void setPredecessor(int v, int u);

    /**
     * Returns an estimated heuristic weight of the shortest path from vertex
     * V to the destination vertex (if any).  This is assumed to be less
     * than the actual weight, and is 0 by default.
     */
    protected double estimatedDistance(int v) {
        return 0.0;
    }

    /**
     * Returns the current weight of edge (U, V) in the graph.  If (U, V) is
     * not in the graph, returns positive infinity.
     */
    protected abstract double getWeight(int u, int v);

    /**
     * Returns a list of vertices starting at _source and ending
     * at V that represents a shortest path to V.  Invalid if there is a
     * destination vertex other than V.
     */
    public List<Integer> pathTo(int v) {
        if (_dest == 0 || v == _dest) {
            if (getPredecessor(v) != 0) {
                LinkedList<Integer> path = new LinkedList<>();

                while (v != 0) {
                    path.addFirst(v);
                    v = getPredecessor(v);
                }
                return path;
            } else {
                return new LinkedList<Integer>();
            }
        }
        return null;
    }

    /**
     * Returns a list of vertices starting at the source and ending at the
     * destination vertex. Invalid if the destination is not specified.
     */
    public List<Integer> pathTo() {
        return pathTo(getDest());
    }

    /**
     * The graph being searched.
     */
    protected final Graph _G;
    /**
     * The starting vertex.
     */
    private final int _source;
    /**
     * The target vertex.
     */
    private final int _dest;

    class PathTraverse extends Traversal {
        PathTraverse() {
            super(_G, new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    if (o1.equals(o2)) {
                        return 0;
                    }
                    double val1 = getWeight(o1) + estimatedDistance(o1);
                    double val2 = getWeight(o2) + estimatedDistance(o2);
                    if (val1 < val2) {
                        return -1;
                    } else if (val1 == val2) {
                        return 0;
                    } else {
                        return 1;
                    }
                }
            }));
        }

        @Override
        protected boolean processSuccessor(int u, int v) {
            if (marked(v)) {
                return false;
            }
            double newweight = getWeight(u) + getWeight(u, v);
            if (newweight < getWeight(v)) {
                setPredecessor(v, u);
                setWeight(v, newweight);
                _fringe.remove(v);
                _fringe.add(v);
            }
            if (!_fringe.contains(v)) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        protected boolean visit(int v) {
            return v != _dest;
        }
    }

    class TreeQueue extends AbstractQueue<Integer> {
        TreeSet<IntWrapper> pq;

        class IntWrapper implements Comparable<IntWrapper> {
            int val;

            public IntWrapper(int val) {
                this.val = val;
            }

            public int compareTo(IntWrapper b) {
                if (this.equals(b)) {
                    return 0;
                }
                double val1 = getWeight(val) + estimatedDistance(val);
                double val2 = getWeight(b.val) + estimatedDistance(b.val);
                if (val1 < val2) {
                    return -1;
                } else if (val1 == val2) {
                    return 0;
                } else {
                    return 1;
                }
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                IntWrapper that = (IntWrapper) o;

                return val == that.val;
            }

            @Override
            public int hashCode() {
                return val;
            }
        }

        public TreeQueue() {
            this.pq = new TreeSet<>();
        }

        @Override
        public boolean add(Integer o) {
            IntWrapper io = new IntWrapper(o);
            if (!pq.contains(io)) {
                if (pq.add(io)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

        @Override
        public boolean offer(Integer o) {
            IntWrapper io = new IntWrapper(o);
            if (!pq.contains(io)) {
                return pq.add(io);
            } else {
                return false;
            }
        }

        @Override
        public Integer remove() {
            IntWrapper iw = pq.pollFirst();
            if (iw != null) {
                return iw.val;
            } else {
                return 0;
            }
        }

        @Override
        public boolean remove(Object o) {
            IntWrapper iw = new IntWrapper((Integer) o);
            return pq.remove(iw);
        }

        @Override
        public Integer poll() {
            if (pq.size() == 0) {
                return null;
            } else {
                return remove();
            }
        }

        @Override
        public Integer element() {
            int head = pq.first().val;
            return head;
        }

        @Override
        public Integer peek() {
            if (pq.size() == 0) {
                return null;
            } else {
                return element();
            }
        }

        @Override
        public void clear() {
            pq.clear();
        }

        @Override
        public Integer[] toArray() {
            return (Integer[]) pq.toArray();
        }

        public Iterator<Integer> iterator() {
            LinkedList<Integer> list = new LinkedList<>();
            Iterator<IntWrapper> i = pq.iterator();
            while (i.hasNext()) {
                IntWrapper next = i.next();
                list.add(next.val);
            }
            return Iteration.iteration(list);
        }

        public int size() {
            return pq.size();
        }
    }
}
