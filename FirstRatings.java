import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
/**
 * create a new class named FirstRatings to process the movie and ratings data and to answer questions about them.
 * 
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FirstRatings {

    //Write a method named loadMovies that has one parameter, a String named filename. This method should process every record from the CSV file whose name is filename, a file of movie information, and return an ArrayList of type Movie with all of the movie data from the file.
    public ArrayList<Movie> loadMovies(String fileName)
    {
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        FileResource fr = new FileResource("data/"+fileName);
        CSVParser parse = fr.getCSVParser();
        for(CSVRecord rc : parse)
        {
            Movie m = new Movie (rc.get("id"), rc.get("title"), rc.get("year"), rc.get("genre"), rc.get("director"),
            rc.get("country"), rc.get("poster"), Integer.parseInt(rc.get("minutes")));
            
            movieList.add(m);
        }
        return movieList;
    }
    
    public void testLoadMovies()
    {
        ArrayList<Movie> movies = loadMovies("ratedmovies_short.csv");
        //ArrayList<Movie> movies = loadMovies("ratedmoviesfull.csv");
        System.out.println("No. of Movies are: "+movies.size());
        /*
        for(Movie m : movies)
        {
            System.out.println(m);
        }*/
        ArrayList<Movie> cMovies = new ArrayList<Movie>();
        for(Movie m : movies)
        {
            if(m.getGenres().contains("Comedy"))
            {
                cMovies.add(m);
            }
        }
        System.out.println("No. of Comedy Movies are: "+cMovies.size());
        
        ArrayList<Movie> bigMovies = new ArrayList<Movie>();
        for(Movie m : movies)
        {
            if(m.getMinutes() > 150)
            {
                bigMovies.add(m);
            }
        }
        System.out.println("No. of Movies > 150 minutes are: "+bigMovies.size());
        
        HashMap<String, Integer> directorMovies = new HashMap<String, Integer>();
        for(Movie m : movies)
        {
            String[] directors = m.getDirector().split(",");
            for(String d : directors)
            {
                if(!directorMovies.containsKey(d))
                {
                    directorMovies.put(d,1);
                }
                else
                {
                    int num = directorMovies.get(d);
                    directorMovies.put(d,num+1);
                }
            }
        }
        int maxMovies = 0;
        for(String d : directorMovies.keySet())
        {
            if(directorMovies.get(d) > maxMovies)
                maxMovies = directorMovies.get(d);
        }
        System.out.println("Max movies by any director is: "+maxMovies);
        System.out.println("Name of those Directors are: ");
        for(String d : directorMovies.keySet())
        {
            if(directorMovies.get(d) == maxMovies)
            {
                System.out.println(d);
            }
        }
    }
    
    public ArrayList<Rater> loadRaters(String fileName)
    {
        ArrayList<Rater> raterList = new ArrayList<Rater>();
        HashMap<String, ArrayList<Rating>> myMap = new HashMap<String, ArrayList<Rating>>();
        
        FileResource fr = new FileResource("data/"+fileName);
        CSVParser parse = fr.getCSVParser();
        for(CSVRecord rc : parse)
        {
            String raterId = rc.get("rater_id");
            String movieId = rc.get("movie_id");
            double rating = Double.parseDouble(rc.get("rating"));
            if(!myMap.containsKey(raterId))
            {
                Rating r = new Rating(movieId, rating);
                ArrayList<Rating> rateList = new ArrayList<Rating>();
                rateList.add(r);
                myMap.put(raterId, rateList);
            }
            else
            {
                myMap.get(raterId).add(new Rating(movieId, rating));
            }        
        }
        //System.out.println(myMap);
        for(String rID: myMap.keySet())
        {
            Rater r = new EfficientRater(rID);
            for(Rating rat : myMap.get(rID))
            {
                r.addRating(rat.getItem(), rat.getValue());
            }
            raterList.add(r);
        }
                
        return raterList;
    }
    
    public void testLoadRaters()
    {
        ArrayList<Rater> raters = loadRaters("ratings_short.csv");
        System.out.println("Total no. of raters are: "+raters.size());
        /*
        for(Rater r : raters)
        {
            System.out.println("Rater id: "+r.getID()+" No. of ratings: "+r.numRatings());
            ArrayList<String> movieIds = r.getItemsRated();
            for(String m : movieIds)
            {
                System.out.println(m+"\t"+r.getRating(m));
            }
            
        }*/
        
        String raterId = "2";
        for(Rater r : raters)
        {
            if(r.getID().equals(raterId))
            {
                System.out.println("number of ratings for rater:"+raterId+" is "+r.getItemsRated().size());
                break;
            }
            
        }
        
        int maxRating = 0;
        for(Rater r : raters)
        {
            if(r.getItemsRated().size() > maxRating)
            {
                maxRating = r.getItemsRated().size();
            }
            
        }
        System.out.println("maximum number of ratings by any rater: "+maxRating);
        
        String movieId = "1798709";
        int count = 0;
        for(Rater r : raters)
        {
            for(String movie: r.getItemsRated())
            {
                if(movie.equals(movieId))
                {
                    count++;
                }
            }
        }
        System.out.println("number of ratings "+movieId+" movie has "+count);
        
        HashSet<String> movies = new HashSet<String>();
        for(Rater r : raters)
        {
            for(String movie: r.getItemsRated())
            {
                movies.add(movie);
            }
        }
        System.out.println("Total no. of movies rated "+movies.size());
        
    }
}







