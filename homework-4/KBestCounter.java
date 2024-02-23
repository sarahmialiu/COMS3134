import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;

public class KBestCounter<T extends Comparable<? super T>> {

    PriorityQueue<T> heap;
    int k;

    public KBestCounter(int k) {
        heap = new PriorityQueue<T>();
        this.k = k;
    }

    public void count(T x) {
        if (heap.size() < k) heap.offer(x);
        else if (x.compareTo(heap.peek()) > 0) {
            heap.poll();
            heap.offer(x);
        }
    }

    public List<T> kbest() {
        List<T> result = new ArrayList<T>();
        
        while (heap.size() > 0){
            T next = heap.poll();
            result.add(next);
        }
        
        heap = new PriorityQueue<T>(result);
        return result;
    }
    
}