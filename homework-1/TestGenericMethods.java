import shapes.*; 
import java.util.Arrays;


public class TestGenericMethods {

  public static void testBinarySearch(Shape[] testArr, Shape x, int target) {    
    int binSearchTest = GenericMethods.binarySearch(testArr, x);      
    
    System.out.print("GenericMethods.binarySearch for "+x.toString()+" returned index " +Integer.toString(binSearchTest)+": ");

    if (binSearchTest!=-1)
      System.out.print(testArr[binSearchTest].toString());
                     
    if (binSearchTest == target) {      
      System.out.println(" "+(char)27 + "[32mOK");
    } else {                       
      System.err.println("\n"+(char)27 + "[31mWas expecting: "+testArr[target].toString());
    }
    System.out.println((char)27+"[0m\n");
  }
    
  public static void main(String[] args) {
    
    Shape[] testArr = new Shape[6];
    testArr[0] = new Circle(3);
    testArr[1] = new Rectangle(2, 8);
    testArr[2] = new Circle(4);
    testArr[3] = new Rectangle(8, 2);
    testArr[4] = new Square(5);   
    testArr[5] = new Square(4);
   
    // test findMax
    int maxTest = GenericMethods.findMax(testArr);
    int target = 2;
    if (maxTest == -1) {
      System.err.println((char)27 + "[31mGenericMethods.findMax returned -1. Probably not yet implemented.");
    } else if (maxTest!=target) { 
      System.err.println("GenericMethods.findMax returned index "+Integer.toString(maxTest)+": "+testArr[maxTest].toString()+" ");
      System.err.println((char)27 + "[31mWas expecting index "+Integer.toString(target)+": "+testArr[target].toString());
    } else {
      System.out.print("GenericMethods.findMax returned index "+Integer.toString(maxTest)+": "+testArr[maxTest].toString());
      System.out.println((char)27 + "[32m OK");
    }
    System.out.println((char)27+"[0m\n");
    
    // test findMaxRecursive
    maxTest = GenericMethods.findMaxRecursive(testArr);
    target = 2;
    if (maxTest == -1) {
      System.err.println((char)27 + "[31mGenericMethods.findMaxRecursive returned -1. Probably not yet implemented.");
    } else if (maxTest!=target) { 
      System.err.println("GenericMethods.findMaxRecursive returned index "+Integer.toString(maxTest)+": "+testArr[maxTest].toString()+" ");
      System.err.println((char)27 + "[31mWas expecting index "+Integer.toString(target)+": "+testArr[target].toString());
    } else {
      System.out.print("GenericMethods.findMaxRecursive returned index "+Integer.toString(maxTest)+": "+testArr[maxTest].toString());
      System.out.println((char)27 + "[32m OK");
    }
    System.out.println((char)27+"[0m\n");
        
    // test binarySearchGeneric   
    Arrays.sort(testArr); // sort the array
    testBinarySearch(testArr, new Circle(4), 5);
    testBinarySearch(testArr, new Rectangle(5,5), 3);
    
  }
  
}