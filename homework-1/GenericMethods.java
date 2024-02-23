//Programming Assignment 1: Problem 1
//SARAH LIU

public class GenericMethods {
   
  public static <T extends Comparable<T>> int findMax(T[] arr) {
    int max = 0;
    for (int i = 0; i < arr.length; i++) if (arr[i].compareTo(arr[max]) > 0) max = i;
    return max;
  }
  
  
  public static <T extends Comparable<T>> int findMaxRecursive(T[] arr) {
    return findMaxRec(arr, arr.length - 1); 
  }
  
  //Helper Method: 
  private static <T extends Comparable<T>> int findMaxRec(T[] arr, int currentIndex){
    if (currentIndex == 0) return currentIndex;
    
    int possibleIndex = findMaxRec(arr, currentIndex-1);
    if (arr[possibleIndex].compareTo(arr[currentIndex]) > 0) return possibleIndex;
    else return currentIndex;
    
  }
  

  public static <T extends Comparable<T>> int binarySearch(T[] arr, T x) {
    return binarySearchRec(arr, x, 0, arr.length);
  }
  
  //Helper Method:
  private static <T extends Comparable<T>> int binarySearchRec(T[] arr, T x, int start, int end){
    int index = (start + end) / 2;
    
    if (arr.length <= 0) return -1;
    
    else if (arr[index].compareTo(x) == 0) return index;
    
    else if (arr[index].compareTo(x) > 0) return binarySearchRec(arr, x, start, index);
    else return binarySearchRec(arr, x, index, end);
    
  }
  
}