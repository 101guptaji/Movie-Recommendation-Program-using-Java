
/**
 * Write a description of MinutesFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinutesFilter implements Filter {
    private int minMinutes, maxMinutes;
    
    public MinutesFilter(int min, int max) {
        minMinutes = min;
        maxMinutes = max;
    }
    
    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getMinutes(id) >= minMinutes 
                &&  MovieDatabase.getMinutes(id) <= maxMinutes ;
    }

}
