import java.util.*;

/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ThirdRatings {
    //private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv"); 
    }
    
    public ThirdRatings(String ratingFile)
    {
        FirstRatings fRate = new FirstRatings();
        //myMovies = fRate.loadMovies(movieFile);
        myRaters = fRate.loadRaters(ratingFile);
        
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
        if(count >= minRaters)
        {
            return sum/count;
        }
        
        return 0.0;
    }
    
    public ArrayList<Rating> getAverageRatings(int minRaters)
    {
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        ArrayList<String> myMovies = MovieDatabase.filterBy(new TrueFilter());
        for(String mID : myMovies)
        {
            double avgRating = getAverageByID(mID, minRaters);
            
            if(avgRating > 0.0)
            {
                Rating r = new Rating(MovieDatabase.getTitle(mID), avgRating);
                ratings.add(r);
            }
            
        }
        return ratings;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minRaters, Filter f)
    {
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        ArrayList<String> myMovies = MovieDatabase.filterBy(f);
        for(String mID : myMovies)
        {
            double avgRating = getAverageByID(mID, minRaters);
            //System.out.println(MovieDatabase.getTitle(mID)+" "+MovieDatabase.getGenres(mID));
            if(avgRating > 0.0)
            {
                Rating r = new Rating(mID, avgRating);
                ratings.add(r);
            }
        }
        
        return ratings;
    }
}
