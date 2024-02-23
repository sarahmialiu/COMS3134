// Programming Assignment 1: Problem 2
//SARAH LIU

import java.util.Iterator; 

/**
 * An iterable that produces the first steps terms of the geometric sequence
 * defined by an initial term and constant factor. 
 * @param   initialTerm     the initial term of the sequence 
 * @param   constantFactor  the constant factor of the sequence 
 * @param   steps           the number of terms to produce before iteration terminates. 
 */
public class GeometricSequence implements Iterable<Double> {  
  private double initial;
  private double factor;
  private double step;
  
  private int count;
  private double current;
  
  //Iterator implementation:
  public class GeometricSequenceIterator implements Iterator<Double> {    
    @Override
    public boolean hasNext(){
      return count < step;
    }
    
    @Override
    public Double next(){
      count++;
      
      double temp = current;
      current *= factor;
      return temp;
    }
 
  }
  
  //Constructor:
  public GeometricSequence(double initialTerm, double constantFactor, int steps) {
    initial = initialTerm;
    factor = constantFactor;
    step = steps;
    
    count = 0;
    current = initial;
  }
  
  public Iterator<Double> iterator() {
      return new GeometricSequenceIterator();
  }
  
}


