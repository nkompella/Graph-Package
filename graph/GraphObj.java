package graph;
import java.util.ArrayList;
<<<<<<< HEAD
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.LinkedList;
=======
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Comparator;
>>>>>>> 821d2daa8a96c320d43ea4658eaa5a5a585f354e

/* See restrictions in Graph.java. */

/** A partial implementation of Graph containing elements common to
 *  directed and undirected graphs.
 *
 *  @author Neha Kompella
 */
abstract class GraphObj extends Graph {

    /** A new, empty Graph. */
    GraphObj() {
        vertexList = new ArrayList<Vertex>();
        vertexList.add(null);
        edgeList = new ArrayList<Edge>();
<<<<<<< HEAD
=======
        removed = new PriorityQueue<>();
>>>>>>> 821d2daa8a96c320d43ea4658eaa5a5a585f354e
    }

    @Override
    public int vertexSize() {
<<<<<<< HEAD
        return vertexList.size() - 1;
=======
        return internalSize() - deleted() - 1;
    }

    /** The size of all elements in the list, null or existing. */
    private int internalSize() {
        return vertexList.size();
    }
    /** Number of null objects in the vertexList. */
    private int deleted() {
        return removed.size();
>>>>>>> 821d2daa8a96c320d43ea4658eaa5a5a585f354e
    }

    @Override
    public int maxVertex() {
        int max = 0;
<<<<<<< HEAD
        for (int i = 1; i < vertexSize() + 1; i++) {
            max = Math.max(max, findV(i).get_vertexNumber());
=======
        for (int i = 1; i < internalSize(); i++) {
            if (findV(i) != null) {
                max = i;
            }
>>>>>>> 821d2daa8a96c320d43ea4658eaa5a5a585f354e
        }
        return max;
    }

    @Override
    public int edgeSize() {
        return edgeList.size();
    }

    @Override
    public abstract boolean isDirected();

    @Override
    public int outDegree(int v) {
        if (contains(v)) {
<<<<<<< HEAD
            for (int i = 1; i < vertexSize() + 1; i++) {
                if (findV(v).getSuccessors().isEmpty()) {
                    return 0;
                }
            }
=======
>>>>>>> 821d2daa8a96c320d43ea4658eaa5a5a585f354e
            return findV(v).getSuccessors().size();
        } else {
            return 0;
        }
    }

    @Override
    public abstract int inDegree(int v);

    @Override
    public boolean contains(int u) {
<<<<<<< HEAD
        if (u > vertexSize()) {
            return false;
        }
        return findV(u) != null;
=======
        if (u >= internalSize()) {
            return false;
        }
        return !removed.contains(u);
>>>>>>> 821d2daa8a96c320d43ea4658eaa5a5a585f354e
    }

    @Override
    public boolean contains(int u, int v) {
        if (contains(u) && contains(v)) {
            return findV(u).hasSuccessor(findV(v));
        }
        return false;
    }

    @Override
    public int add() {
        if (vertexSize() == 0) {
            vertexList.add(new Vertex(1));
            return 1;
        } else {
<<<<<<< HEAD
            for (int i = 1; i < vertexSize() + 1; i++) {
                if (vertexList.get(i) == null) {
                    vertexList.add(i, new Vertex(i));
                    return i;
                }
            }
            vertexList.add(new Vertex(vertexSize() + 1));
            return vertexSize();
=======
            if (removed.size() == 0) {
                vertexList.add(new Vertex(internalSize()));
                return vertexSize();
            } else {
                int v = removed.poll();
                vertexList.set(v, new Vertex(v));
                return v;
            }
>>>>>>> 821d2daa8a96c320d43ea4658eaa5a5a585f354e
        }
    }

    @Override
    public int add(int u, int v) {
        if (contains(u) && contains(v)) {
            Vertex uV = findV(u);
            Vertex vV = findV(v);
            uV.addSuccessor(vV);
<<<<<<< HEAD
            Edge uv = new Edge(vV, uV);
            uV.addEdge(uv);
=======
            vV.addPredecessor(uV);
            Edge uv = new Edge(vV, uV);
            uV.addEdge(uv);
            vV.addEdge(uv);
>>>>>>> 821d2daa8a96c320d43ea4658eaa5a5a585f354e
            if (!edgeContains(uv)) {
                edgeList.add(uv);
            }
            if (!uV.equals(vV)) {
<<<<<<< HEAD
                vV.addPredecessor(uV);
                vV.addEdge(uv);
=======
>>>>>>> 821d2daa8a96c320d43ea4658eaa5a5a585f354e
                if (!isDirected()) {
                    vV.addSuccessor(uV);
                    uV.addPredecessor(vV);
                }
            }

<<<<<<< HEAD
            return u;
        }
        return u;
    }

    private boolean edgeContains(Edge e) {
        if (edgeList.contains(e)) {
            return true;
        }
        if (!isDirected()) {
            e = new Edge(e._from, e._to);
            return edgeList.contains(e);
        } else {
            return false;
        }
=======
            return edgeId(u, v);
        }
        return 0;
>>>>>>> 821d2daa8a96c320d43ea4658eaa5a5a585f354e
    }
    /** Checks if the edgeList contains Edge e. */
    private boolean edgeContains(Edge e) {
        if (edgeList.contains(e)) {
            return true;
        }
        if (!isDirected()) {
            e = new Edge(e._from, e._to);
            return edgeList.contains(e);
        } else {
            return false;
        }
    }

    @Override
    public void remove(int v) {
        if (contains(v)) {
            Vertex vV = findV(v);
            vV.terminate();
            vertexList.set(v, null);
<<<<<<< HEAD
=======
            removed.add(v);
>>>>>>> 821d2daa8a96c320d43ea4658eaa5a5a585f354e
        }
    }

    @Override
    public void remove(int u, int v) {
        if (contains(u) && contains(v)) {
            Vertex uV = findV(u);
            Vertex vV = findV(v);
            uV.removeSuccessor(vV);
            vV.removePredecessor(uV);
<<<<<<< HEAD
            Edge utov = new Edge(uV, vV);
=======
            Edge utov = new Edge(vV, uV);
>>>>>>> 821d2daa8a96c320d43ea4658eaa5a5a585f354e
            uV.removeEdge(utov);
            vV.removeEdge(utov);
            edgeList.remove(utov);
            if (!isDirected()) {
                vV.removeSuccessor(uV);
                uV.removePredecessor(vV);
<<<<<<< HEAD
                Edge vtou = new Edge(vV, uV);
=======
                Edge vtou = new Edge(uV, vV);
>>>>>>> 821d2daa8a96c320d43ea4658eaa5a5a585f354e
                uV.removeEdge(vtou);
                vV.removeEdge(vtou);
                edgeList.remove(vtou);
            }
        }
    }

    @Override
    public Iteration<Integer> vertices() {
        ArrayList<Integer> vertexNumbers = new ArrayList<Integer>();
        for (Vertex v: vertexList) {
            if (v != null) {
                vertexNumbers.add(v._vertexNumber);
            }
        }
        return Iteration.iteration(vertexNumbers);
    }

    @Override
    public int successor(int v, int k) {
        if (contains(v)) {
            Vertex uV = findV(v);
            Vertex vV = uV.findSuccessor(k);
            if (vV != null) {
                return uV.findSuccessor(k).get_vertexNumber();
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    @Override
    public abstract int predecessor(int v, int k);

    @Override
    public Iteration<Integer> successors(int v) {
        if (contains(v)) {
            Vertex vV = findV(v);
            ArrayList<Integer> success_list = new ArrayList<>();
            for (Vertex successor: vV.successorsList){
                success_list.add(successor._vertexNumber);
            }
            return Iteration.iteration(success_list);
        }
        return Iteration.iteration(new ArrayList<Integer>());
    }

    @Override
    public abstract Iteration<Integer> predecessors(int v);

    @Override
    public Iteration<int[]> edges() {
        ArrayList<int[]> edges = new ArrayList<>();
        for (Edge edge: edgeList) {
            edges.add(edge.toArray());
        }
        return Iteration.iteration(edges);
    }

    @Override
    protected void checkMyVertex(int v) {
        if (!contains(v)) {
<<<<<<< HEAD
            throw new NullPointerException("No Such Vertex");
=======
            throw new IllegalArgumentException("No Such Vertex");
>>>>>>> 821d2daa8a96c320d43ea4658eaa5a5a585f354e
        }
    }

    @Override
    protected int edgeId(int u, int v) {
        if (!isDirected()) {
            int x = Math.max(u, v);
            int y = Math.min(u, v);
            return ((x + y) * (x + y + 1)) / 2 + y;
        }
        return ((u + v) * (u + v + 1)) / 2 + v;
    }

    private Vertex findV(int v) {
<<<<<<< HEAD
        if (v >vertexSize()) {
=======
        if (v >= internalSize()) {
>>>>>>> 821d2daa8a96c320d43ea4658eaa5a5a585f354e
            return null;
        }
        return vertexList.get(v);
    }


    LinkedList<Vertex> getPredecessorData(int a) {
        return vertexList.get(a).getPredecessors();
    }

    LinkedList<Vertex> getSuccessorData(int a) {
        if (contains(a)) {
            return vertexList.get(a).getSuccessors();
        }
        return new LinkedList<>();
    }

    int getVertexNumber(Vertex v) {
        return v.get_vertexNumber();
    }

    private ArrayList<Vertex> vertexList;
    private ArrayList<Edge> edgeList;
<<<<<<< HEAD
=======
    private PriorityQueue<Integer> removed;
>>>>>>> 821d2daa8a96c320d43ea4658eaa5a5a585f354e

    class Vertex {

        Vertex(int vertexNumber) {
            _vertexNumber = vertexNumber;
            successorsList = new LinkedList<Vertex>();
            predecessorsList = new LinkedList<Vertex>();
            myEdges = new LinkedList<>();
        }

<<<<<<< HEAD
        private int get_vertexNumber() {
=======
        int get_vertexNumber() {
>>>>>>> 821d2daa8a96c320d43ea4658eaa5a5a585f354e
            return _vertexNumber;
        }

        private LinkedList<Vertex> getSuccessors() {
            return successorsList;
        }

        private LinkedList<Vertex> getPredecessors() {
            return predecessorsList;
        }

        private void addSuccessor(Vertex vertex) {
            if (!successorsList.contains(vertex)) {
                successorsList.add(vertex);
            }
        }

        private void addPredecessor(Vertex vertex) {
            if (!predecessorsList.contains(vertex)) {
                predecessorsList.add(vertex);
            }
        }

        private void addEdge(Edge edge) {
            if (!myEdges.contains(edge)) {
                myEdges.add(edge);
            }
        }

        private void removeSuccessor(Vertex vertex) {
            successorsList.remove(vertex);
        }

        private void removePredecessor(Vertex vertex) {
            predecessorsList.remove(vertex);
        }

        private void removeEdge(Edge edge) {
            myEdges.remove(edge);
        }

        private Vertex findSuccessor(int k) {
            if (k >= successorsList.size()) {
                return null;
            }
            successorsList.sort(new Comparator<Vertex>() {
                @Override
                public int compare(Vertex o1, Vertex o2) {
                    return o1.get_vertexNumber() - o2.get_vertexNumber();
                }
            });
            return successorsList.get(k);
        }

        private boolean hasSuccessor(Vertex v) {
            return successorsList.contains(v);
        }

        private void terminate() {
            for (Vertex successor: successorsList) {
                successor.removePredecessor(this);
            }

            for (Vertex predecessor: predecessorsList) {
                predecessor.removeSuccessor(this);
            }

            for (Edge edge: myEdges) {
                edgeList.remove(edge);
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Vertex vertex = (Vertex) o;

            return _vertexNumber == vertex._vertexNumber;
        }

        @Override
        public int hashCode() {
            return _vertexNumber;
        }

        private int _vertexNumber;
<<<<<<< HEAD
        private LinkedList<Vertex> successorsList;
        private LinkedList<Vertex> predecessorsList;
        private LinkedList<Edge> myEdges;
=======
        private LinkedList<Vertex> successors_list;
        private LinkedList<Vertex> predecessors_list;
        private LinkedList<Edge> my_edges;
<<<<<<< HEAD

=======
>>>>>>> 821d2daa8a96c320d43ea4658eaa5a5a585f354e
>>>>>>> f974b6c5c0a8ddd036dcdf680958a8f784dceb3f
    }

    class Edge {

        Edge(Vertex to, Vertex from) {
            _to = to;
            _from = from;
        }

        private Vertex get_to() {
            return _to;
        }

        private Vertex get_from() {
            return _from;
        }

        private int[] toArray() {
            int[] arr = {_from.get_vertexNumber(), _to.get_vertexNumber()};
            return arr;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Edge edge = (Edge) o;

            int to = _to._vertexNumber;
            int edge_to = edge._to._vertexNumber;
            int from = _from._vertexNumber;
            int edge_from = edge._from._vertexNumber;

            return (_to.equals(edge._to) && _from.equals(edge._from));
        }

        @Override
        public int hashCode() {
            int result = _to != null ? _to.hashCode() : 0;
            result = 31 * result + (_from != null ? _from.hashCode() : 0);
            return result;
        }

        private Vertex _to;
        private Vertex _from;
    }

}
