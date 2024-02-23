import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.Deque;
import java.util.Collection;
import java.util.PriorityQueue;

import java.lang.Math;
import java.util.ArrayList;

public class Graph<V> { 
   
    // Keep an index from node labels to nodes in the map
    protected Map<V, Vertex<V>> vertices; 

    /**
     * Construct an empty Graph.
     */
    public Graph() {
       vertices = new HashMap<V, Vertex<V>>();
    }

    /**
     * Retrieve a collection of vertices. 
     */  
    public Collection<Vertex<V>> getVertices() {
        return vertices.values();
    }

    public void addVertex(V u) {
        addVertex(new Vertex<>(u));
    }
    
    public void addVertex(Vertex<V> v) {
        if (vertices.containsKey(v.name)) 
            throw new IllegalArgumentException("Cannot create new vertex with existing name.");
        vertices.put(v.name, v);
    }

    /**
     * Add a new edge from u to v.
     * Create new nodes if these nodes don't exist yet. 
     * @param u unique name of the first vertex.
     * @param w unique name of the second vertex.
     * @param cost cost of this edge. 
     */
    public void addEdge(V u, V w, Double cost) {
        if (!vertices.containsKey(u))
            addVertex(u);
        if (!vertices.containsKey(w))
            addVertex(w);

        vertices.get(u).addEdge(
            new Edge<>(vertices.get(u), vertices.get(w), cost)); 

    }

    public void addEdge(V u, V w) {
        addEdge(u,w,1.0);
    }

    public void printAdjacencyList() {
        for (V u : vertices.keySet()) {
            StringBuilder sb = new StringBuilder();
            sb.append(u.toString());
            sb.append(" -> [ ");
            for (Edge e : vertices.get(u).getEdges()){
                sb.append(e.target.name);
                sb.append(" ");
            }
            sb.append("]");
            System.out.println(sb.toString());
        }
    }    
  
   /**
    * Add a bidirectional edge between u and v. Create new nodes if these nodes don't exist
    * yet. This method permits adding multiple edges between the same nodes.
    *
    * @param u  
    *          the name of the source vertex.
    * @param v 
    *          the name of the target vertex.
    * @param cost
    *          the cost of this edge
    */
    public void addUndirectedEdge(V u, V v, Double cost) {
        addEdge(u,v, cost);
        addEdge(v,u, cost);
    }

    /****************************
     * Your code follows here.  *
     ****************************/ 
    
    // Part 1
    public void computeAllEuclideanDistances() {
      for (V cityName : vertices.keySet()){
          Vertex<V> city = vertices.get(cityName);
          
          for (Edge<V> ed : city.getEdges()){
              ed.distance = Math.sqrt(Math.pow(ed.source.posX - ed.target.posX, 2) 
                                      + Math.pow(ed.source.posY - ed.target.posY, 2));
          }
      }
    }
    
    // Part 2
    public void doDijkstra(V s) {
      computeAllEuclideanDistances();
        
      for (V cityName : vertices.keySet()){
          Vertex<V> city = vertices.get(cityName);
          
          city.cost = Integer.MAX_VALUE;
          city.visited = false;
          city.backpointer = null;
      }
      Vertex<V> start = vertices.get(s);
      start.cost = 0;
      
      PriorityQueue<Vertex<V>> queue = new PriorityQueue<Vertex<V>>();
      queue.offer(start);
        
      while (queue.size() > 0){
          Vertex<V> current = queue.poll();
          
          if (!current.visited){
              for (Edge<V> ed : current.getEdges()){
                  Vertex<V> neighbor;
                  
                  if (ed.source.name.equals(current.name)){
                      neighbor = ed.target;
                  } else neighbor = ed.source;
                  
                  if (!neighbor.visited){
                      double totalCost = current.cost + ed.distance;
                  
                      if (totalCost < neighbor.cost){
                          System.out.println("Updating cost of " + neighbor.name + ": " + totalCost);
                          neighbor.cost = totalCost;
                          neighbor.backpointer = current;
                          queue.offer(neighbor);
                      }
                  }
              }
              current.visited = true;
          }
          
      }
    }

    // Part 3
    public List<Edge<V>> getDijkstraPath(V s, V t) {
      List<Edge<V>> result = new ArrayList<Edge<V>>(); 
      doDijkstra(s);
      
      V currentName = t;
      Vertex<V> current = vertices.get(currentName);
        
      while (!currentName.equals(s)){
          System.out.println(currentName);
          current = vertices.get(currentName);
          Vertex<V> prev = current.backpointer;
          
          for (Edge<V> ed : prev.getEdges()){
              if (ed.source.name.equals(currentName) || ed.target.name.equals(currentName)) result.add(ed);
          }
          
          currentName = prev.name;
      }
        
      return result;
    }  
    
}
