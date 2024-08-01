import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

/**
 * Write a description of FourthRatings here.
 * 
 * @author HimanshuGupta
 * @version (a version number or a date)
 */
public class FourthRatings {
    
    public double getAverageByID(String id, int minRaters)
    {
        double sum =0.0;
        int count = 0;
        for(Rater rr : RaterDatabase.getRaters())
        {
            if(rr.hasRating(id))
            {
                count++;
                sum = sum + rr.getRating(id);
            
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
                Rating r = new Rating(mID, avgRating);
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
    
    private double dotProduct(Rater me, Rater r)
    {
        HashMap<String, Double> mRatings = new HashMap<String, Double>();
        double sum=0.0;
        if(me != null && r != null)
        {
            for(String mId : me.getItemsRated())
            {
                mRatings.put(mId, me.getRating(mId)-5);
            }
            for(String mId : r.getItemsRated())
            {
                double rating = r.getRating(mId)-5;
                if(mRatings.containsKey(mId))
                {
                    double myRating = mRatings.get(mId);
                    sum = sum+(myRating*rating);
                    //mRatings.put(mId, myRating*rating);
                }
            }
        }
        return sum;
    }
    
    private ArrayList<Rating> getSimilarities(String id)
    {
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);
        for(Rater r : RaterDatabase.getRaters())
        {
            if(!r.getID().equals(id))
            {
                double avgRating = dotProduct(me, r);
                if(avgRating > 0)
                {
                    Rating rating = new Rating(r.getID(), avgRating);
                    ratings.add(rating);
                }
            }
        }
        
        Collections.sort(ratings, Collections.reverseOrder());
        return ratings;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minRaters)
    {
        return getSimilarRatingsByFilter(id, numSimilarRaters, minRaters, new TrueFilter());
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minRaters, Filter f)
    {
        ArrayList<Rating> myRatings = new ArrayList<Rating>();
        ArrayList<Rating> simRating = getSimilarities(id);
        ArrayList<String> movieIds = new ArrayList();
        
        HashMap<String, Double> similarMap = new HashMap();
        int mapSize = simRating.size();
        int minIndex = Math.min(mapSize, numSimilarRaters);
        
        for(Rating similar:simRating.subList(0,minIndex))
        {
            if(similar.getValue()>0)
            {
                similarMap.put(similar.getItem(), similar.getValue());
            }  
        }
        for(String mID : MovieDatabase.filterBy(f))
        {
            int count =0;
            double total =0;
        
            for(Rater curRater:RaterDatabase.getRaters())
            {
                double rating = -1;
                if(similarMap.containsKey(curRater.getID()) && curRater.hasRating(mID))
                {
                    rating = curRater.getRating(mID) * similarMap.get(curRater.getID());
                }
            
                if(rating ==-1){}
            
                else{
                    count++;
                    total = total + rating;
                }
            }
            
            if(count< minRaters || total==0){}
            else
            {
                myRatings.add(new Rating(mID, total/count));
            }

        }
        
        Collections.sort(myRatings, Collections.reverseOrder());
        return myRatings;
    }
    
}



