import java.util.NoSuchElementException;

public class TwoStackQueue<T> implements Queue<T> {

    private Stack<T> s1;
    private Stack<T> s2;
    
    public TwoStackQueue() {
      s1 = new ArrayStack<T>();
      s2 = new ArrayStack<T>();
    }
    
    public void enqueue(T x) {
      s1.push(x);
    }
    
    public T dequeue() throws NoSuchElementException {
      if (isEmpty()) throw new NoSuchElementException();
      
      else if (s2.isEmpty()){
        while (!s1.isEmpty()) s2.push(s1.pop());
      }
      
      return s2.pop();
    }
    
    public boolean isEmpty() {
      return s1.isEmpty() && s2.isEmpty();
    }
    
}