
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class AdjList {
  public static void main(String[] args) {
    Graph<Character> g = new Graph<Character>();

    g.addVertex('a');
    g.addVertex('b');
    g.addVertex('c');

    g.addEdge('a','b');
    g.addEdge('b','c');
    g.addEdge('b','a');


    g.dfs();

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

  public void dfs() {
    ArrayList<Node> visited = new ArrayList<Node>();

    dfs(this.g.get(0), visited);
  }

  private void visit(Node n, ArrayList<Node> visited) {
    System.out.println(n.getValue());
    visited.add(n);
  }

  private void dfs(Node x, ArrayList<Node> v) {
    // Visit
    visit(x, v);

    Iterator itr = x.getEdges().iterator();
    Integer nextConnectedNode;

    while (itr.hasNext()) {
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
