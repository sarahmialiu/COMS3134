import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap; 
import java.util.TreeMap; 
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.HashSet;

public class TweetDB {
   
    
    HashMap<String, List<Tweet>> tweetsPerUser;
    HashMap<String, List<Tweet>> tweetsPerKeyword;  
    TreeMap<DateTime, List<Tweet>> tweetsByTime;
    
    public TweetDB(String pathToFile) throws FileNotFoundException, IOException {
        tweetsPerUser = new HashMap<>();               
        tweetsPerKeyword = new HashMap<>();   
        tweetsByTime = new TreeMap<>();         
        
        BufferedReader br = new BufferedReader(new FileReader(pathToFile));
        CsvReader csv = new CsvReader(br);
        
        String[] fields = csv.nextLine();       
        while (fields != null) {            
            Tweet twt = new Tweet(fields[0], new DateTime(fields[2]), fields[1]);
            
            // Sorting tweets by username
            if (tweetsPerUser.containsKey(twt.user)) tweetsPerUser.get(twt.user).add(twt);
            else {
                List<Tweet> twtList = new ArrayList<Tweet>();
                twtList.add(twt);
                
                tweetsPerUser.put(twt.user, twtList);
            }
            
            // Sorting tweets by keyword
            for (String word : processTweet(twt)){
                if (tweetsPerKeyword.containsKey(word)) tweetsPerKeyword.get(word).add(twt);
                else {
                    List<Tweet> twtList = new ArrayList<Tweet>();
                    twtList.add(twt);
                
                    tweetsPerKeyword.put(word, twtList);
                }
            }
            
            // Sorting tweets by date/time
            if (tweetsByTime.containsKey(twt.datetime)) tweetsByTime.get(twt.datetime).add(twt);
            else {
                List<Tweet> twtList = new ArrayList<Tweet>();
                twtList.add(twt);
                
                tweetsByTime.put(twt.datetime, twtList);
            }
            
            fields = csv.nextLine();
        }
       
        
    } 
    
    // Processes a given tweet's content by returning a string array of words in the tweet.
    // Maintains capitalization.
    private String[] processTweet(Tweet tweet) {
        return tweet.content.replaceAll("\\p{Punct}", "").split(" ");
    }
    
    // Returns an arraylist with tweets sent by the given user.
    // Returns an empty arraylist if user cannot be found in the database.
    public List<Tweet> getTweetsByUser(String user) {
        if (!tweetsPerUser.containsKey(user)) {
            System.out.println("No user named: " + user);
            return new ArrayList<Tweet>();
        }
        
        HashSet<Tweet> hs = new HashSet<Tweet>();
        for (Tweet t : tweetsPerUser.get(user)) hs.add(t);
        
        return new ArrayList<Tweet>(hs);
        
    }
    
    // Returns an arraylist with tweets containing the given keyword.
    // Returns an empty arraylist if no tweets contain the given keyword.
    // Case-sensitive.
    public List<Tweet> getTweetsByKeyword(String kw) {
        if (!tweetsPerKeyword.containsKey(kw)) {
            System.out.println("No tweet containing keyword: " + kw);
            return new ArrayList<Tweet>();
        }
        
        HashSet<Tweet> hs = new HashSet<Tweet>();
        for (Tweet t : tweetsPerKeyword.get(kw)) hs.add(t);
        
        return new ArrayList<Tweet>(hs);        
    }
                    
    // Returns an arraylist with tweets from between the two given times.
    // Returns and empty arraylist if no tweets are found between the start and end dates.
    public List<Tweet> getTweetsInRange(DateTime start, DateTime end) {
        if (start.compareTo(end) > 0){
            System.out.println("Invalid start and end date. Start time must be before end date.");
            return new ArrayList<Tweet>();
        } 
        
        List<Tweet> result = new ArrayList<Tweet>();
        
        for (DateTime dt : tweetsByTime.subMap(start, true, end, false).keySet())
            result.addAll(tweetsByTime.get(dt));
        
        HashSet<Tweet> hs = new HashSet<Tweet>();
        for (Tweet t : result) hs.add(t);
        
        return new ArrayList<Tweet>(hs);        
    }  
    
    public static void main(String[] args) {
          
        try {
            TweetDB tdb = new TweetDB("coachella_tweets.csv");
            
            // Part 1: Search by username 
            for(Tweet t : tdb.getTweetsByUser("hannah_frog"))
                System.out.println(t);
            
            //Part 2: Search by keyword
            for(Tweet t : tdb.getTweetsByKeyword("Officially"))
                System.out.println(t);
            
            //Part 3: Search by date/time interval          
            for(Tweet t : tdb.getTweetsInRange(new DateTime("1/6/15 00:00"), new DateTime("1/6/15 05:00")))
                System.out.println(t);
            
            
        } catch (FileNotFoundException e) {
            System.out.println(".csv File not found.");
        } catch (IOException e) {
            System.out.println("Error reading from .csv file.");
        }
        
        
        
        
    }
    
}