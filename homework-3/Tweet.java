/**
 * Represent a tweet, including the content, author's username
 * and time when it was posted. 
 */
public class Tweet {
    
    public String user;
    public DateTime datetime; 
    public String content;
    
    public Tweet(String user, DateTime datetime, String content) {
            this.user = user; 
            this.datetime = datetime;
            this.content = content;       
    }
    
    public String toString(){
        return "@"+this.user+" ["+datetime.toString()+"]: "+content;
    }
    
    @Override
    public int hashCode(){
        int hash = 17;
        hash = hash * 31 + user.hashCode();
        hash = hash * 19 + datetime.hashCode();
        hash = hash * 23 + content.hashCode();
        return hash;
    }
    
    @Override
    public boolean equals(Object obj){
        if (!(obj instanceof Tweet)) return false;

        Tweet t = (Tweet) obj;

        return t.user.equals(user) &&
                t.datetime.equals(datetime) &&
                t.content.equals(content);
    }
    
}