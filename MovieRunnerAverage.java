import java.util.*;

/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerAverage {

    public void printAverageRatings()
    {
        SecondRatings sr = new SecondRatings
            ("ratedmovies_short.csv", "ratings_short.csv");
        
        System.out.println("number of movies: "+sr.getMovieSize());
        System.out.println("number of raters: "+sr.getRaterSize());
        
        ArrayList<Rating> ratings = sr.getAverageRatings(3);
        Collections.sort(ratings);
        for(Rating r : ratings)
        {
            System.out.println(r.getValue()+" "+r.getItem());
        }        
    }
    
    public void getAverageRatingOneMovie()
    {
        SecondRatings sr = new SecondRatings
            ("ratedmovies_short.csv", "ratings_short.csv");
        
        //Scanner sc = new Scanner(System.in);
        //String movie = sc.nextLine();
        String movie = "The Godfather";
        String movieID = sr.getID(movie);
        double avgRating = sr.getAverageByID(movieID, 0);
        System.out.println("the average for the movie "+movie+" would be "+avgRating);
        
    }

}
