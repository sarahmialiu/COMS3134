# COMS W3134 Fall 21 - Programming Assignment 1  (100 pts)

Please remember that to submit the assignment you must click Mark as Complete under the Education menu in the toolbar.

Please make sure to look at the written portion of this assignment, which is available on Courseworks. 


## Problem 1 - Generic Methods, Comparable, and Recursion - 25 points

Take a look at the file `GenericMethods.java`.

There are three methods you must implement:

* `public static <T extends Comparable<T>> int findMax(T[] arr)`: Iterate through the array to find the index of the largest element in the array. If there are two elements with the largest value, the method should return the index of the first one. The method should run in linear time.
* `public static <T extends Comparable<T>> int findMaxRecursive(T[] arr)`: Should behave just like `findMax`, but should be implemented **recursively**. Your method may not contain any `while` or `for` loops. Hint: the public `findMaxRecursive` method should not, itself, be recursive. Instead, write an additional private helper method with additional parameters. This private helper method should be called from `findMaxRecursive`. This must run in O(n) time. 
* `public static <T extends Comparable<T>> int binarySearch(T[] arr, T x)`: Implement a **recursive** binary search to find a value equal to `x`. Hint: The public `binarySearch` method, itself, should not be recursive. Instead, write an additional private helper method with additional parameters. This private helper method should be called from the public `binarySearch` method. This must run in O(log n) time. If the value is not found in the array, return -1. Else, return the index in the array where the value was found.

To test your code, you may compile and run `GenericMethodTester.java`. This tester class uses the types defined in the package `shapes`, which includes an interface `Shape` and concrete classes `Rectangle`, `Square`, and `Circle`. The `Shape` interface extends the `Comparable` interface, which means that the concrete classes all need to have a compareTo method. In this case, the different shapes are compared according to their **area**. Take a look at the code for these classes to make sure you understand how everything works. There is nothing you need to change in this package -- it's only here to test the GenericMethods. 
 
## Problem 2 - Geometric Sequence Iterable - 25 points

In a *geometric sequence*, each term is computed form the previous term by multiplying it with a *constant factor*. The first term of the sequence is called the *initial term*.  

For example, the geometric sequence with initial term 1 and constant factor 0.5 is  

`1,  0.5, 0.25, 0.125, 0.0625, ...`

Your task is to complete the class `GeometricSequence.java`. 
The arguments passed to the constructor of GeometricSequence are `new GeometricSeqeuence(Double initialTerm, Double constantFactor, int steps)`.
Where `steps` is the number of terms produced

The class should behave as illustrated in the following example 
```
            for(Double d : new GeometricSequence(1, 0.5, 5)) {
             System.out.print(d);  
            }
            // Prints 1,  0.5, 0.25, 0.125, 0.0625 (in separate lines)

            for(Double d : new GeometricSequence(2,2,4)) {
             System.out.print(j);
            }
            // Prints 2, 4, 8, 16

            for(Double d : new GeometricSequence(1,1,3)) {
             System.out.print(j);
            }
            // Prints 1, 1, 1
```
To implement `GeometricSequence`, use an inner class for the `Iterator` that is returned by the `iterator()`` method.
The `Iterator` does not need to support the `remove() operation.
Important: The `GeometricSeqeunce` class should not explicity store a list of terms. Only the current term should be stored. 

## Problem 3 - Linked List Operations - 25 points 
The file `LinkedList.java` contains a doubly linked list, essentially identical to the implementation discussed in class.

Your task is to complete the method `void flipPairs()`, which should flip each adjacent pair of elements in the linked list.
For example, assume you have a `LinkedList<String>`

```
"A" "B" "C" "D" "E"
```

after calling `flipPairs()` on the list, the list should be changed to 

```
"B" "A" "D" "C" "E"
```

If the list contains an odd number of elements, the extra element in the end should stay in place unchanged (element "E" in the example).

The method should modify the list data structure by changing the `next` and `prev` references in the linked list nodes. 

You can test your method by running the main method of LinkedList, which should print out a list in the original, and then with the elements swapped as ilustrated in the example. 
    

## Problem 4 - Linked List Operations - 25 pts
Reconsider the list implementation in `LinkedList.java`. 

Complete the method `void reverse()`, which should modify the linked list in place, such that the elements are reversed. 

The method should modify the list data structure by changing the next and prev references in the linked list nodes.

You can test your method by running the main method of LinkedList, which should print out a list in the original, and then in reversed order.
