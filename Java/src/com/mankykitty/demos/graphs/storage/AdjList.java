

public class Graph<T> {
  HashMap<T, Integer> indicies;
  ArrayList<Node> g;

  public Graph() {
    this.indicies = new HashMap<T, Integer>();
    this.g = new ArrayList<ArrayList<Integer>>();
  } 

  public boolean has(T v) {
    return this.indicies.containsKey(v);
  }

  public boolean addEdge(T start, T end) {
    if (this.has(start) && this.has(end)) {
      
      int startI = this.indicies.get(start);
      int endI = this.indicies.get(end);

      this.setEdge(start, endI);
      this.setEdge(end, startI);
      
    }
    return false;
  }

  public boolean hasEdge(T start, T end) {
    return this.vertexHasEdge(start, end)
      && this.vertexHasEdge(end, start);
  }

  private void setEdge(T start, int endI) {
    this.getVertex(start).edges.add(endI);
  }
  
  private boolean vertexHasEdge(T start, T end) {
    this.getVertex(start).edges.contains(this.indicies.get(end));
  }
  
  public boolean addVertex(T v) {
    Node newVertex;
    int indexOf;
    
    if (!this.has(v)) {

      // Create our new node and add it to the graph
      newVertex = new Node(v);
      this.g.add(newVertex);
      // Store the index of this node for quick retrieval later
      this.indicies.put(v, this.g.indexOf(newVertex));
      
      return true;
    }
    return false;
  }

  /***
   * Private helper functions
   */
  
  private Node getVertex(T value) {
    return this.g.get(this.indicies.get(value));
  }
}

class Node<T> implements {
  T value;
  ArrayList<Integer> edges;

  public Node() {
    this.value = -1;
    this.edges = new ArrayList<Integer>();
  }
}
