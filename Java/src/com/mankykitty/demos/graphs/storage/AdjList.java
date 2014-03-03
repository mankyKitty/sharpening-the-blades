
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class AdjList {
  public static void main(String[] args) {
    Graph<Character> g = new Graph<Character>();

    /**
     * a -> b
     *   \  |
     *    - c
     */

    g.addVertex('a');
    g.addVertex('b');
    g.addVertex('c');

    g.addEdge('a','b');
    g.addEdge('b','c');
    g.addEdge('c','a');

	// Depth First Traverse
	System.out.println("Depth First");
    g.dfs();

	// Breadth First Traverse
	System.out.println("Breadth First");
	g.bfs();

    System.out.println("woo?");
  }
}

class Graph<T> {
  private HashMap<T, Integer> indices;
  private ArrayList<Node> g;

  public Graph() {
    this.indices = new HashMap<T, Integer>();
    this.g = new ArrayList<Node>();
  }

  /**
   * Breadth First Traversal of the graph.
   *
   * Just prints the value of each node as it is visited.
   */
  public void bfs() {
    ArrayList<Node> visited = new ArrayList<Node>();
    LinkedList<Node> queue = new LinkedList<Node>();
    Node x;
    Iterator itr;
    Integer nextConnectedNode;
    
    // visit(start node)
    visit(this.g.get(0), visited);
   
    // queue <- start node
    queue.add(this.g.get(0));
    
    // WHILE queue is not empty DO
    while (queue.size() != 0) {
    	// x <- queue
    	x = queue.removeFirst();
    	
    	// FOR each y such that (x,y) is an edge and y has not been visited DO
    	itr = x.getEdges().iterator();
    	
    	while (itr.hasNext()) {
    		nextConnectedNode = (Integer) itr.next();
    		if (!visited.contains(this.g.get(nextConnectedNode))) {
    			visit(this.g.get(nextConnectedNode), visited);
    			queue.add(this.g.get(nextConnectedNode));
    		}
    	}
    }
  }

  /**
   * Depth First Traversal of the graph.
   *
   * Simply prints each node as it is visited.
   */
  public void dfs() {
    ArrayList<Node> visited = new ArrayList<Node>();
    // We're using a recursive DFS so pass in our starting parameters and have at it.
    dfs(this.g.get(0), visited);
  }

  private void visit(Node n, ArrayList<Node> visited) {
    // We're just printing at the moment, no need to get fancy.
    System.out.println(n.getValue());
    visited.add(n);
  }

  private void dfs(Node x, ArrayList<Node> v) {
    // Visit
    visit(x, v);
    // You know, Java, I think you still have a lot to learn.
    Iterator itr = x.getEdges().iterator();
    Integer nextConnectedNode;

    while (itr.hasNext()) {
      // This is what I'm talking about...
      nextConnectedNode = (Integer) itr.next();
      // If there is a node that we have not visited yet.
      if (!v.contains(this.g.get(nextConnectedNode))) {
        dfs(this.g.get(nextConnectedNode), v);
      }
    }
  }

  public boolean has(T v) {
    return this.indices.containsKey(v);
  }

  public boolean addEdge(T start, T end) {
    if (!this.hasEdge(start, end)) {

      int startI = this.indices.get(start);
      int endI = this.indices.get(end);

      this.setEdge(start, endI);
      this.setEdge(end, startI);

    }
    return false;
  }

  public boolean hasEdge(T start, T end) {
    return this.vertexHasEdge(start, end)
      && this.vertexHasEdge(end, start);
  }

  @SuppressWarnings("unchecked")
  private void setEdge(T start, int endI) {
    this.getVertex(start).edges.add(endI);
  }

  private boolean vertexHasEdge(T start, T end) {
    return this.getVertex(start).edges.contains(this.indices.get(end));
  }

  public boolean addVertex(T v) {
    Node<T> newVertex;

    if (!this.has(v)) {

      // Create our new node and add it to the graph
      newVertex = new Node<T>(v);
      this.g.add(newVertex);
      // Store the index of this node for quick retrieval later
      this.indices.put(v, this.g.indexOf(newVertex));

      return true;
    }
    return false;
  }

  private Node getVertex(T value) {
    return this.g.get(this.indices.get(value));
  }
}

class Node<T> {
  public T value;
  public ArrayList<Integer> edges;

  public Node(T v) {
    this.value = v;
    this.edges = new ArrayList<Integer>();
  }

  public ArrayList<Integer> getEdges() {
    return this.edges;
  }

  public T getValue() {
    return this.value;
  }
}
