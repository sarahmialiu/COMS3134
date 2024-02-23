# COMS W3134 Fall 21 - Programming Assignment 2  (100 pts)
**Due: Monday October 25**

Please remember that to submit the assignment you must click Mark as Complete under the Education menu in the toolbar.

Please make sure to look at the written portion of this assignment, which is available on Courseworks. 


## Problem 1 - Two Stack Queue - 25 points

In this problem you will implement a Banker's queue, as discussed in class, which uses two completely separate stacks, `s1` and `s2`. Enqueue operations happen by pushing the data on to `s1`.
Dequeue operations are completed with a pop from `s2`. Recall that when `s2` is empty, the dequeue operation should first move all elements from `s1` to `s2` using a sequence of pop and push operations.  

**TODO**: Complete the class `TwoStackQueue` that provides the Queue ADT (by implementing the `Queue` interface) using two stacks. Implement all methods specified in the interface. Your class should not use any additional memory to store the data in the queue except for the two stacks. 
For your stacks, use the provided `ArrayStack` class.

Your `TwoStackQueue` should raise a [java.util.NoSuchElementException](https://docs.oracle.com/javase/7/docs/api/java/util/NoSuchElementException.html) when trying to dequeue from an empty queue. 
You may use the `TestTwoStackQueue` class to test your code.


## Problem 2 - Extracting Relations from English Sentences - 45 points

Nested structures are a hallmark of natural languages (as well as programming languages). 
To correctly analyze the grammatical structure of natural language expressions, and ultimate figure out semantic relations ('who does what to whom?') 
we must be able to model such nested structures. Most computational linguists believe that this can be done using a stack. 

We will consider a fragment of English illustrating a phenomenon called **center embeddings**. Consider the sentence *"The child that the woman that the man knew loved laughed"*. 

This sentence contains two nested relative clauses. Such sentences are difficult to understand, especially for non-native speakers, but they are perfectly grammatical. 
To get a clearer understanding, we can start with the subject and predicate and then add the relative clauses one by one. 

* *the child laughed*
* *the child [that the the woman loved] laughed* 
* *the child [that the woman [that the man knew] loved] laughed*
* *the child [that the woman [that the man [...] knew] loved] laughed*

The relative clauses are embedded in between the subject (*child*) and the predicate (*laughed*). In principle, an arbitrary number of nested clauses can be embedded this way, but each new phrase one pushes the subject further to the left and the predicate further to the right. 

Our goal is to extract a set of relations from sentences of this type. Relations are either of the form (subject, predicate) or (subject, predicate, object). For the example sentence above, the following relations should be produced. 

```
(man, knew, woman)
(woman, loved, child)
(child, laughed)
```
Note that *laugh* is an intransitive verb, so there is no object, while *know* and *laugh* are transitive verbs. 

When processing a sentence word by word (left to right), we need to keep track of word that still need to be "attached" to the predicate. This can be done using a stack. 

**TODO**: 
Take a look at the class `CenterEmbeddings`. The class contains a nested class `Relation` to represent the relations described above. Each Relation has a subject, a predicate, and an object. The object may be `null` if the predicate is an intransitive verb. 
The `toString` method prints the relation in the format illustrated above. 

The class also defines two static String arrays, listing transitive and intransitive verbs in the language. 

Your task is to write the method `public static List<Relation> parseSentence(String sentence)`. This method should take a sentence as input (as a string) and return a list of `Relation` instances.

* To separate the input string into tokens (words), you may use the [`split`](https://docs.oracle.com/javase/7/docs/api/java/lang/String.html#split(java.lang.String)) method of the String class. 
* Process the sentence left to right. Use a stack to keep track of subjects and objects. Create a new Relation when you encounter a verb. Make sure your program handles both transitive and intransitive verbs correctly. Think about what, if anything, needs to be pushed back onto the stack after processing a verb.
* As a Stack implementation, you can either use the provided `ArrayStack` class (which implements the provided `Stack` interface), or  use [`java.util.LinkedList`](https://docs.oracle.com/javase/7/docs/api/java/util/LinkedList.html). If you use a LinkedList, you may only use the methods `push, pop, isEmpty, and peek`. 
* The input contains articles (*the*, *a*) and the relative pronoun *that*. You can ignore these as you process the sentence. All other tokens are either nouns or verbs. You can assume the input is lower-case and there is no punctuation. 
* The input sentences will follow the center embedding pattern illustrated above and will not have any other structure not shown. However, one special case to watch out for is that the main verb can be transitive. For example, consider the following sentence: *the woman that the child knew loved the man*. Make sure your program works for the other cases before attempting these exceptions (test case 4, 5, and 6).
* If the input cannot be processed, your method should throw an [java.util.IllegalArgumentException](https://docs.oracle.com/javase/7/docs/api/java/util/IllegalArgumentException.html)`IllegalArgumentException`. 
* An empty input string shoudl result an an empty relation list. 

Use the main method of the class `CenterEmbedding` to test your code. Several test sentences have been provided. 

## Problem 3 - Tree Traversals using Stacks and Queues - 30 points

The class `BinaryTree` contains a Binary Tree implementation similar to the one that will be discussed in class (the week of October 11). This version is not generic and only stores Integers. 
Familiarize yourself with the code. 

The method `sumTreeRec` (and its driver method `sumTree`) perform a recursive tree traversal to compute the sum of the Integers in the tree. 

a) Write the non-recursive method `public Integer sumTreeStack()` that traverses the nodes in the tree in the same order as `sumTreeRec` and returns their sum, but uses a Stack to keep track of Nodes that still need to be visited.
You can use the provided `ArrayStack` class or [`java.util.LinkedList`](https://docs.oracle.com/javase/7/docs/api/java/util/LinkedList.html). If you use a LinkedList, you may only use the methods `push, pop, isEmpty, and peek`. 

b) Write the non-recursive method `public List<Integer> levelOrder()` that should return a list of integers in level order. For example, consider the following tree: `
BinaryTree tree = btree(1, btree(2, btree(3), btree(4)), btree(5));
` which looks like this 
```
    1
   / \
  2   5
 / \
3   4               
```
The level-order traversal should return the list [1, 2, 5, 3, 5].

Use a Queue to store Nodes that still need to be visited. You can use java.util.LinkedList but you must limit yourself to the queue methods this class provides. That is, you must either use `addLast`/`removeFirst` or `offerLast`/`pollFirst`.  You may also use your `TwoStackQueue` from problem 1, at the risk that it may contain bugs. 