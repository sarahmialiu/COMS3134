import java.util.List; 
import java.util.ArrayList;

public class BetterBST<T extends Comparable<? super T>> extends BinarySearchTree<T> {
    
    
    @Override
	T smallestGreaterThan(T low){
       if (root == null) return null;
       else if (smallGreater(root, low) == null) return null;
       else return smallGreater(root, low).data;
    }
    
    private BinaryNode<T> smallGreater(BinaryNode<T> t, T low){ 
        if (t.data.compareTo(low) <= 0){
            if (t.right != null) return smallGreater(t.right, low);
            
            else {
                System.out.println("No values greater than given threshold");
                return null;
            } 
        }
        
        else if (t.left != null){
            if (t.left.data.compareTo(low) <= 0){
                if (t.left.right != null && t.left.right.data.compareTo(low) > 0) 
                    return smallGreater(t.left.right, low);
                else return t;
            }
            
            else return smallGreater(t.left, low);
        }
        
        else return t;
    }
    
    //Retrieve a list of all keys k in the BST, such that low <= k < high for some keys low < high. 
    //For example, if the BST contains the values 1,3,5,7,9, and the function is called with low = 2 and high = 7, 
    //it should return a list containing 3 and 5.
    
    List<T> tempRange;
    
    @Override
    List<T> getRange(T low, T high) {
        tempRange = new ArrayList<T>();
        range(root, low, high); 
        return tempRange;
    }    
    
    private void range(BinaryNode<T> t, T low, T high){
        if (t.data.compareTo(low) >= 0 && t.data.compareTo(high) < 0) {
            tempRange.add(t.data);
            if (t.right != null) range(t.right, low, high);
            if (t.left != null) range(t.left, low, high);
        }
        else if (t.data.compareTo(low) < 0 && t.right != null) range(t.right, low, high);
        else if (t.data.compareTo(high) >= 0 && t.left != null) range(t.left, low, high);
    }
    
    public static void main(String[] args){
    
        BetterBST<Integer> tree = new BetterBST();
        
        tree.insert(6);
        tree.insert(14);
        tree.insert(1);
        tree.insert(8);
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(13);
        tree.insert(21);
        
        System.out.println(tree.toString());
        
//         System.out.println(tree.smallestGreaterThan(12));
//         System.out.println(tree.smallestGreaterThan(1));
//         System.out.println(tree.smallestGreaterThan(10));
//         System.out.println(tree.smallestGreaterThan(13));
//         System.out.println(tree.smallestGreaterThan(6));
//         System.out.println(tree.smallestGreaterThan(2));
//         System.out.println(tree.smallestGreaterThan(21));
        
        System.out.println(tree.getRange(0, 1).toString());
        System.out.println(tree.getRange(4, 15).toString());

    
    }
              	
    
}

