import java.util.List; 

public abstract class BinarySearchTree<T extends Comparable<? super T>> {
    
    // stripped down and adapted from 
    // https://users.cs.fiu.edu/~weiss/dsaajava2/code/BinarySearchTree.java


    protected static class BinaryNode<T> {
    
        T data;            // The data in the node
        BinaryNode<T> left;   // Left child
        BinaryNode<T> right;  // Right child
    
        BinaryNode( T thedata ) {
            this( thedata, null, null );
        }

        BinaryNode( T thedata, BinaryNode<T> lt, BinaryNode<T> rt ) {
            data  = thedata;
            left     = lt;
            right    = rt;
        }

        public String toString() {

            if (left == null && right == null) // if this is a leaf
                return data.toString();
           
            StringBuilder sb = new StringBuilder( "("); 
            sb.append(data);
            sb.append(" ");
            if (left != null)
                sb.append(left.toString());
            else 
                sb.append("*");
            sb.append(" ");
            if (right != null) 
                sb.append(right.toString());
            else
                sb.append("*");
            sb.append(")");
            return sb.toString();
        }
    }
	
    
    BinaryNode<T> root;
    
	// implement these!
	abstract T smallestGreaterThan(T low);
    abstract List<T> getRange(T low, T high);	      
    
    public BinarySearchTree( ) {
        root = null;
    }

    public String toString() {
        return root.toString();
    }

    public void insert( T x ) {
        root = insert( x, root );
    }

    public void remove( T x ) {
        root = remove( x, root );
    }
	
	public T findMin( ) {
        if(root == null)
            throw new NullPointerException( );
        return findMin( root ).data;
    }
	
    public boolean contains( T x ) {
        return contains( x, root );
    }
	
    private BinaryNode<T> insert( T x, BinaryNode<T> t ) {
        if( t == null )
            return new BinaryNode<T>( x, null, null );
        
        int compareResult = x.compareTo( t.data );
            
        if( compareResult < 0 )
            t.left = insert( x, t.left );
        else if( compareResult > 0 )
            t.right = insert( x, t.right );
        else
            ;  // Duplicate; do nothing
        return t;
    }

 
    private BinaryNode<T> remove( T x, BinaryNode<T> t ) {
        if( t == null )
            return t;   // Item not found; do nothing
            
        int compareResult = x.compareTo( t.data );
            
        if( compareResult < 0 )
            t.left = remove( x, t.left );
        else if( compareResult > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) { // Two children
            t.data = findMin( t.right ).data;
            t.right = remove( t.data, t.right );
        }
        else
            t = ( t.left != null ) ? t.left : t.right;
        return t;
    }
	
	private BinaryNode<T> findMin( BinaryNode<T> t ) { 
        if( t == null )
            return null;
        else if( t.left == null )
            return t;
        return findMin( t.left );
    }

    private boolean contains( T x, BinaryNode<T> t ) {
        if( t == null )
            return false;
            
        int compareResult = x.compareTo( t.data );
            
        if( compareResult < 0 )
            return contains( x, t.left );
        else if( compareResult > 0 )
            return contains( x, t.right );
        else
            return true;    // Match
    }
	

}
