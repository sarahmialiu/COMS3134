/**
 * Represent a timestamp consisting of a date (day/month/year) and time 
 * in hours and minutes (24h format.
 */
public class DateTime implements Comparable<DateTime> {
    
    public int year;
    public int month;
    public int day; 
    public int hours;
    public int minutes;    
    public boolean pm; 

    
    public DateTime(int year, int day, int month, int h, int m) {        
        this.year = year; 
        this.month = month; 
        this.day = day;     
        this.hours = h; 
        this.minutes = m;                
    }
    
    public DateTime(String datetime) {
        String[] fields = datetime.split(" ");
        String[] dateFields = fields[0].split("/");
        month = Integer.parseInt(dateFields[0]);
        day = Integer.parseInt(dateFields[1]);
        year = Integer.parseInt(dateFields[2]);
        
        String[] timeFields = fields[1].split(":"); 
        hours = Integer.parseInt(timeFields[0]);
        minutes = Integer.parseInt(timeFields[1]);;
    }
    
    public String toString() {
        return Integer.toString(month)+"/"+Integer.toString(day)+"/"+Integer.toString(year)+"  "+
            String.format("%02d" , hours)+":"+String.format("%02d", minutes);
    }
    
    // Returns 
    // 0 if the tweets occurred at the same time, 
    // 1 if the tweet occurred after the given parameter, 
    // -1 if the tweet occurred before the given parameter.
    public int compareTo(DateTime dt){
        if (year == dt.year){
            if (month == dt.month){
                if (day == dt.day){
                    if (hours == dt.hours){
                        if (minutes == dt.minutes) return 0;
                        else if (minutes > dt.minutes) return 1;
                        else return -1;
                    } 
                    else if (hours > dt.hours) return 1;
                    else return -1;
                } 
                else if (day > dt.day) return 1;
                else return -1;
            } 
            else if (month > dt.month) return 1;
            else return -1;
        } 
        else if (year > dt.year) return 1;
        else return -1;
    }
    
    @Override
    public int hashCode(){
        int hash = 17;
        hash = hash * 31 + year;
        hash = hash * 19 + month;
        hash = hash * 23 + day;
        hash = hash * 11 + hours;
        hash = hash * 7 + minutes;
        return hash;
    }
    
    @Override
    public boolean equals(Object obj){
        if (!(obj instanceof DateTime)) return false;

        DateTime dt = (DateTime) obj;
        
        return dt.year == year 
            && dt.month == month 
            && dt.day == day 
            && dt.hours == hours 
            && dt.minutes == minutes;
    }
    
}