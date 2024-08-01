import java.util.*;

/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv"); 
    }
    
    public SecondRatings(String movieFile, String ratingFile)
    {
        FirstRatings fRate = new FirstRatings();
        myMovies = fRate.loadMovies(movieFile);
        myRaters = fRate.loadRaters(ratingFile);
        
    }
    
    public int getMovieSize()
    {
        return myMovies.size();
    }
    
    public int getRaterSize()
    {
        return myRaters.size();
    }
    
    public double getAverageByID(String id, int minRaters)
    {
        double sum =0.0;
        int count = 0;
        for(Rater rr : myRaters)
        {
            for(String rg : rr.getItemsRated())
            {
                if(rg.equals(id))
                {
                    count++;
                    sum = sum + rr.getRating(rg);
                }
            }
        }
        if(count > minRaters)
        {
            return sum/count;
        }
        
        return 0.0;
    }
    
    public ArrayList<Rating> getAverageRatings(int minRaters)
    {
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        for(Movie m : myMovies)
        {
            double avgRating = getAverageByID(m.getID(), minRaters);
            
            if(avgRating > 0.0)
            {
                Rating r = new Rating(m.getTitle(), avgRating);
                ratings.add(r);
            }
            
        }
        return ratings;
    }
    
    public String getID(String title)
    {
        for(Movie m : myMovies)
        {
            if(m.getTitle().equals(title))
            {
                return m.getID();
            }
        }
        return "NO SUCH TITLE";
    }
    
}



