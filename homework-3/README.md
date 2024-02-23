# Homework 3 Programming  (100 pts)

Please remember that to submit the assignment you must click Mark as Complete under the Education menu in the toolbar.

Please make sure to look at the written portion of this assignment, which is available on Courseworks. 

## Problem 1 - Additional BST Methods (34 pts)
                                                                                                                                       
In this problem, you will implement various additional algorithms operating on binary search trees. We have provided with you a standard implementation of a generic BST in *BinarySearchTree.java*. 
Note that this class is an **abstract class**, which means that some of its methods are not implemented. An abstract class is a class with *some* unimplemented methods (it can be thought of somewhat like an interface but with some methods actually implemented). 
Any class implementing the abstract class must implement these methods. 
You will need to complete the *BetterBST* class which **extends BinarySearchTree**. Your *BetterBST* class can then be treated just like a regular *BinarySearchTree*, just with some additional functionality.

The methods that you will need to implement in *BetterBST* perform various algorithms on BST instances. You may find it convenient to use private helper methods for tree traversals as you did in homework 2. 

* ```public T smallestGreaterThan(T low)``` - given some generic comparable value _low_, find the smallest value in the BST that is larger than _low_. For example, if a binary search tree contains the values 1, 3, and 5, and the function is called with _low = 2_, it should return 3.
* ```public List<T> getRange(T low, T high)``` - Retrieve a list of all keys k in the BST, such that _low <= k < high_ for some keys _low < high_. For example, if the BST contains the values 1,3,5,7,9, and the function is called with _low = 2_ and _high = 7_, it should return a list containing 3 and 5. 

Make sure you read the BST code in depth before you start implementing *BetterBST*. In particular, take note of the various internal methods, nested classes, and instance variables that you can access from *BetterBST*.

We will test this program with our own tester class in a separate file.  You should also create a tester class for your own testing purposes (or you can write a main method in BetterBST.  Your tester class will not be graded.)
                                                                                                                                                 

## Problem 2 - Searching a Twitter Dataset  (66 pts)

The file `coachella_tweets.csv` contains 3882 tweets discussing the Coachella 2015 music festival. This dataset was originally created
for the purpose of training and testing sentiment analysis models, identifying if a users view of the festival is positive or negative. 
In this assignment, you will not work on sentiment, but instead use various Map and Set implementations in the Java standard library to index and query this database. 

In the `.csv` file, each row contains one tweet. Each row has three fields (columns), separated by commas. The file `CsvReader.java` 
already provides a class that reads in this format (described below), so you do not have to re-implement this. The fields are 

1. The username of the author. 
2. The content of the tweet. 
3. The date and time of the tweet, formatted as DD/MM/YY hh:mm, where the hour format is in a 24 hour format. For example "1/7/15 15:02" (which is 3:02pm).

The goal of this problem is to read the complete data into memory and index it using different maps. Specifically, you will index
the tweets using

* A [`java.util.HashMap`](https://docs.oracle.com/javase/9/docs/api/java/util/HashMap.html) in which the keys are usernames (of type String), which allows you to efficiently find all tweets sent by a user. 
* A `java.util.HashMap` in which the keys are keywords (of type String) appearing in the tweets, which allows you to efficiently find all tweets mentioning a specific term. 
* A [`java.util.TreeMap`](https://docs.oracle.com/javase/9/docs/api/java/util/TreeMap.html) in which the keys are dates and times (represented as instances of the class DateTime. The `TreeMap` allows you to find tweets by a specific date and time and also efficiently retrieve tweets in a certain time range. A TreeMap is a balanced Binary Search Tree. (see below)

You may find it useful to look at the API reference for each class. 

In all three maps, the values should be Lists containing instances of the class `Tweet`. The maps will be instance variables of the `TweetDB` class. 

#### Part 1 - Reading the CSVfile and Indexing by Username. (18 pts)
The class `CsvReader` contains code to read a CSV file. The constructor takes a `BufferedReader` as a parameter and opens the file. 
The `CsvReader` instance then provides a `String[] nextLine()` method, which returns a `String` array of the individual fields in each row. 
The method returns `null` once the reader has reached the end of the file. 

The file `Tweet` represents a tweet.The constructor takes the following parameters: username, content, and time stamp as a `DateTime` instance. 
`DateTime` already comes with a constructor that parses the date and time in a string format, as described above. The dates/times can be directly passed to the constructor of the `DateTime` class to create a corresponding `DateTime` instance. 

**TODO**: First, in the `TweetDB` class, complete the constructor. The parameter to the constructor is the pathname of a data file (such as `"coachella_tweets.csv"`).
Using a `CsvReader`, read through the input file and convert each row into a `Tweet` instance. 
Then, insert each `Tweet` into the `tweetsPerUser` hash map, indexed by the username. Recall that the values in the hash map should be Lists of `Tweets` (a user may have tweeted multiple times).
Finally, write the method. `public List<Tweet> getTweetsByUser(String user)` that returns all tweets written by a user. You can test the functionality in the `main` method, but the graders will call your methods from their own tester program. 

#### Part 2 - Indexing by Keyword. (15 pts)
**TODO**: Update the constructor `TweetDB` so that each `Tweet` is additionally indexed by the the words that appear in the tweet content. You should add the tweets into the tweetsPerKeyword hashmap.  
You may want to write an additional method for this, which removes punctuation and obtains keywords from the tweet. Do not spend a lot of time preprocessing the tweet text for this assignment. Simply stripping out common punctuation symbols and splitting by whitespace is sufficient. 
Then, complete the method `public List<Tweet> getTweetsByKeyword(String keyword)`, which returns all tweets with a given keyword. 
Again, you can test this functionality in the main method. 

#### Part 3 - Indexing by Date/Time (18 pts)
**TODO**: 
Step 1) Update the constructor `TweetDB` so that each `Tweet` is additionally indexed by its `DateTime` object. You should add the tweets into the `tweetsByTime` tree map. To use the `DateTime` instances as keys, you need to modify that class to implement `Comparable`. 
Step 2) Write the method `public List<Tweet> getTweetsInRange(DateTime start, DateTime end)` that returns a list of all tweets between a start time (inclusive) and end time (exclusive).
Use the [`TreeMap.subMap`](https://docs.oracle.com/javase/9/docs/api/java/util/TreeMap.html#subMap-K-K-) method. This method is similar to what you implemented in Programming part 1, that is it allows you to get all the keys in a certain range on a BST. 
Again, you can test this functionality in the main method. 

#### Part 4 - Removing Duplicates (15 pts)
You may have noticed that the output of your various search method contains duplicates. To remove these duplicates, update your `getTweetsByUser`, `getTweetsByKeyword` and `getTweetsInRange` method in the following way. Before returning the results, insert all retrieved tweets into a `java.util.HashSet`. Then turn the set back into a list. 
To make this work properly, you need to modify the `Tweet` and `DateTime` classes and override both `int hashCode()` and `boolean equals(Object other)`. Implement appropriate hash functions. 

