import java.util.LinkedList;
import java.util.Deque;
import java.util.List;

public class BinaryTree {
  
  private Node root = null; 
  
  
  private static class Node{  // represents an individual node
    Integer data; 
    Node left; 
    Node right;
    
    public Node(Integer data, Node left, Node right){
      this.data = data; 
      this.left = left; 
      this.right = right;
    }
    
    public Node(Integer data) {
      this.data = data; 
      this.left = null; 
      this.right = null;
    }
        
  }

  
  public BinaryTree(Node node) {
    this.root = node;
  }
  
  /**
   * Returns the sum of the integer values in the tree.
   */
  public Integer sumTree() {
    return sumTreeRec(root);
  }
  
  /**
   * Helper method to perform the recursive tree traversal.
   */
  public Integer sumTreeRec(Node node) {
    if (node == null)
      return 0;
    
    return sumTreeRec(node.left) + sumTreeRec(node.right) + node.data;        
  }
   
  /**
   * TODO: write this method. Use a stack to perform the recursive tree traversal. 
   */
  public Integer sumTreeStack() {
    Node current = root;
    Integer sum = 0;
    Stack<Node> stack = new ArrayStack<>();
    
    while (!stack.isEmpty() || current != null){
      if (current == null){
        current = stack.pop();
        sum += current.data;
        current = current.right;
      } else {
        stack.push(current);
        current = current.left;
      }
    }
    
    return sum;
    
  }
  
  /**
   * TODO: write this method. Use a queue to print the integer values in level order. 
   */  
  public List<Integer> levelOrder() {
    Queue<Node> queue = new TwoStackQueue<>();
    List<Integer> result = new LinkedList<>();
    
    queue.enqueue(root);
    
    while (!queue.isEmpty()){
      Node current = queue.dequeue();
      result.add(current.data);
      
      if (current.left != null) queue.enqueue(current.left);
      if (current.right != null) queue.enqueue(current.right);
    }
    
    return result;     
  }
  
  /**
   * Factory method that creates a new BinaryTree with two subtrees.
   * The two btree methods make it possible to easily construt binary trees like this:
   * BinaryTree t = btree(1,btree(2,btree(3),btree(4)),btree(5));
   * @return a new BinaryTree with two children.
   */    
   public static BinaryTree btree(Integer data, BinaryTree t1, BinaryTree t2) {
        Node root = new Node(data, t1.root, t2.root);
        return new BinaryTree(root);
   }
   
   public static BinaryTree btree(Integer data) {
        Node root = new Node(data);
        return new BinaryTree(root);
   }
            
   public static void main(String[] args) {
          
     BinaryTree tree = btree(1, btree(2, btree(3), btree(4)), btree(5));
     System.out.println(tree.sumTreeStack());     
     System.out.println(tree.levelOrder());
     
   }
    
  
}