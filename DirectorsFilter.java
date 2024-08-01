
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter {
    private String[] myDirectors;
    
    public DirectorsFilter(String directors) {
        myDirectors = directors.split(",");
    }
    
    @Override
    public boolean satisfies(String id) {
        for(String d : myDirectors)
        {
            if(MovieDatabase.getDirector(id).contains(d))
                return true;
        }
        return false;
    }

}
