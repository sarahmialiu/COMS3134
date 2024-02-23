import java.util.LinkedList;
import java.util.List; 

public class CenterEmbeddings {

  private static final String[] transitive = {"knew", "chased", "liked", "loved", "saw"};
  private static final String[] intransitive = {"snored", "laughed", "ran"};
  
  private static class Relation {
    
    String predicate; 
    String subject; 
    String object;    
    
    public Relation(String predicate, String subject, String object) {
      this.predicate = predicate; 
      this.subject = subject; 
      this.object = object;
    }
    
    public String toString(){
      
      if (object != null)
        return "(" + subject + "," + predicate +"," + object +")";
      else
        return "(" + subject + "," + predicate+")";
    }
    
  }
  
  public static List<Relation> parseSentence(String sentence) throws IllegalArgumentException {
    
    List<Relation> result = new LinkedList<>();
    Stack<String> nouns = new ArrayStack<>();
    Stack<String> verbs = new ArrayStack<>();

    String[] words = sentence.split(" ");
    
    for (int i = 0; i < words.length; i++){
      String word = words[i].toLowerCase();
      boolean isVerb = false;
      
      for (String verb : transitive){
        if (word.equals(verb)) {
          isVerb = true;
          
          String sub = nouns.pop();
          String obj = "";
          
          if (nouns.isEmpty()){
            verbs.push(word);
            nouns.push(sub);
            break;
          } else obj = nouns.pop();
          
          result.add(new Relation(verb, sub, obj));
          nouns.push(obj);
        }
      }
      
      for (String verb : intransitive){
        if (word.equals(verb)) {
          isVerb = true;
          
          String sub = nouns.pop();
          
          result.add(new Relation(verb, sub, null)); 
        }
      }
      
      if (!word.equals("the") && !word.equals("that") && !word.equals("a") && !isVerb){
        if (!verbs.isEmpty()) result.add(new Relation(verbs.pop(), nouns.pop(), word));
        
        nouns.push(word);
      }
      
    }
    
    return result;
  }
    
  
  
  public static void main(String[] args) {
    
    String test1 = "the child laughed";    
    String test2 = "the child that the the woman loved laughed";    
    String test3 = "the child that the the woman that the man knew loved laughed";    
    String test4 = "the child saw the cat"; // hard 
    String test5 = "the child that the man knew saw the cat"; // hard
    String test6 = "the child saw the cat that the man loved"; // even harder 
    
    List<Relation> result = parseSentence(test6);
    for (Relation r :result) {
      System.out.println(r);
    } 
  }
  
  
}