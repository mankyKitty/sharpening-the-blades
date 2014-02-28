

class AdjList<T> {
  HashMap<T, Integer> indices;
  ArrayList<Node> g;

  public AdjList() {
    this.indices = new HashMap<T, Integer>();
    this.g = new ArrayList<ArrayList<Integer>>();
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

  private void setEdge(T start, int endI) {
    this.getVertex(start).edges.add(endI);
  }

  private boolean vertexHasEdge(T start, T end) {
    return this.getVertex(start).edges.contains(this.indices.get(end));
  }

  public boolean addVertex(T v) {
    Node newVertex;

    if (!this.has(v)) {

      // Create our new node and add it to the graph
      newVertex = new Node(v);
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
  T value;
  ArrayList<Integer> edges;

  public Node(T v) {
    this.value = v;
    this.edges = new ArrayList<Integer>();
  }
}
