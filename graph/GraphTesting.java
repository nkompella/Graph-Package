package graph;

import org.junit.Test;
import static org.junit.Assert.*;

/** Unit tests for the Graph class.
 *  @author Michael Ferrin
 */
public class GraphTesting {

    @Test
    public void emptyGraph() {
        DirectedGraph g = new DirectedGraph();
        assertEquals("Initial graph has vertices", 0, g.vertexSize());
        assertEquals("Initial graph has edges", 0, g.edgeSize());
    }

    @Test
    public void checkAdd() {
        DirectedGraph a = new DirectedGraph();
        assertEquals(0, a.vertexSize());
        a.add();
        a.add();
        assertEquals(2, a.vertexSize());
        assertEquals(2, a.maxVertex());
        a.remove(1);
        assertFalse(a.contains(1));
        assertEquals(1, a.vertexSize());
        assertEquals(2, a.maxVertex());
        a.add();
        assertEquals(2, a.maxVertex());
        assertTrue(a.contains(1));
    }

    @Test
    public void checkRemove() {
        DirectedGraph a = new DirectedGraph();
        a.add();
        a.add();
        a.add();
        a.add();
        assertEquals(4, a.maxVertex());
        assertEquals(4, a.vertexSize());
        a.remove(3);
        assertEquals(4, a.maxVertex());
        assertEquals(3, a.vertexSize());
        a.remove(4);
        assertEquals(2, a.vertexSize());
        assertEquals(2, a.maxVertex());
        a.remove(1);
        a.add();
        a.remove(1);
        a.add();
        a.remove(10);
    }

    @Test
    public void checkMaxVertex() {
        DirectedGraph a = new DirectedGraph();
        assertEquals(0, a.maxVertex());
        a.add();
        a.add();
        a.add();
        assertEquals(3, a.maxVertex());
        a.remove(2);
        a.remove(1);
        assertEquals(3, a.maxVertex());
    }

    @Test
    public void checkEdgeSize() {
        DirectedGraph a = new DirectedGraph();
        assertEquals(0, a.edgeSize());
        a.add();
        a.add();
        a.add();
        assertEquals(0, a.edgeSize());
        a.add(1, 2);
        a.add(2, 3);
        assertEquals(2, a.edgeSize());
    }

    @Test
    public void checkk

}