import java.util.*;

/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerSimilarRatings {
    
    public void printAverageRatings()
    {
        FourthRatings fr = new FourthRatings();
        
        RaterDatabase.initialize("ratings_short.csv");
        System.out.println("number of raters: "+RaterDatabase.size());
        
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("number of movies in database: "+MovieDatabase.size());
        
        ArrayList<Rating> ratings = fr.getAverageRatings(1);
        System.out.println("Found: "+ratings.size()+" movies");

        Collections.sort(ratings);
        for(Rating r : ratings)
        {
            System.out.println(r.getValue()+" "+MovieDatabase.getTitle(r.getItem()));
        }        
    }
    
    public void printAverageRatingsByYearAfterAndGenre()
    {
        FourthRatings fr = new FourthRatings();
        
        RaterDatabase.initialize("ratings_short.csv");
        System.out.println("number of raters: "+RaterDatabase.size());
        
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("number of movies in database: "+MovieDatabase.size());
        
        AllFilters af = new AllFilters();
        af.addFilter(new YearAfterFilter(1980));
        af.addFilter(new GenreFilter("Romance"));
        
        ArrayList<Rating> ratings = 
            fr.getAverageRatingsByFilter(1, af);
        System.out.println("Found "+ratings.size()+" movies");

        Collections.sort(ratings);
        for(Rating r : ratings)
        {
            System.out.println(r.getValue()+
                " "+MovieDatabase.getYear(r.getItem())+
                " "+MovieDatabase.getTitle(r.getItem())+
                "\n\t"+ MovieDatabase.getGenres(r.getItem()));
        }        
    }
    
    
    public void printSimilarRatings()
    {
        FourthRatings fr = new FourthRatings();
        
        RaterDatabase.initialize("ratings.csv");
        System.out.println("number of raters: "+RaterDatabase.size());
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("number of movies in database: "+MovieDatabase.size());
        
        ArrayList<Rating> ratings = 
            fr.getSimilarRatings("65", 20, 5);
        System.out.println("Found: "+ratings.size()+" movies");

        for(Rating r : ratings)
        {
            String mID = r.getItem();
            System.out.println(MovieDatabase.getTitle(mID));
        }        
    }
    
    public void printSimilarRatingsByGenre()
    {
        FourthRatings fr = new FourthRatings();
        
        RaterDatabase.initialize("ratings.csv");
        System.out.println("number of raters: "+RaterDatabase.size());
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("number of movies in database: "+MovieDatabase.size());
        
        Filter f = new GenreFilter("Action");
        ArrayList<Rating> ratings = 
            fr.getSimilarRatingsByFilter("65", 20, 5, f);
        System.out.println("Found: "+ratings.size()+" movies");

        for(Rating r : ratings)
        {
            String mID = r.getItem();
            System.out.println(MovieDatabase.getTitle(mID));
        }        
    }

    public void printSimilarRatingsByDirector()
    {
        FourthRatings fr = new FourthRatings();
        
        RaterDatabase.initialize("ratings.csv");
        System.out.println("number of raters: "+RaterDatabase.size());
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("number of movies in database: "+MovieDatabase.size());
        
        Filter f = new DirectorsFilter("Clint Eastwood,Sydney Pollack,David Cronenberg,Oliver Stone");
        ArrayList<Rating> ratings = 
            fr.getSimilarRatingsByFilter("1034", 10, 3, f);
        System.out.println("Found: "+ratings.size()+" movies");

        for(Rating r : ratings)
        {
            String mID = r.getItem();
            System.out.println(MovieDatabase.getTitle(mID));
        }        
    }
    
    public void printSimilarRatingsByGenreAndMinutes ()
    {
        FourthRatings fr = new FourthRatings();
        
        RaterDatabase.initialize("ratings.csv");
        System.out.println("number of raters: "+RaterDatabase.size());
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("number of movies in database: "+MovieDatabase.size());
        
        AllFilters af = new AllFilters();
        af.addFilter(new GenreFilter("Adventure"));
        af.addFilter(new MinutesFilter(100,200));        
        ArrayList<Rating> ratings = 
            fr.getSimilarRatingsByFilter("65", 10, 5, af);
        System.out.println("Found: "+ratings.size()+" movies");

        for(Rating r : ratings)
        {
            String mID = r.getItem();
            System.out.println(MovieDatabase.getTitle(mID));
        }        
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes ()
    {
        FourthRatings fr = new FourthRatings();
        
        RaterDatabase.initialize("ratings.csv");
        System.out.println("number of raters: "+RaterDatabase.size());
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("number of movies in database: "+MovieDatabase.size());
        
        AllFilters af = new AllFilters();
        af.addFilter(new YearAfterFilter(2000));
        af.addFilter(new MinutesFilter(80,100));        
        ArrayList<Rating> ratings = 
            fr.getSimilarRatingsByFilter("65", 10, 5, af);
        System.out.println("Found: "+ratings.size()+" movies");

        for(Rating r : ratings)
        {
            String mID = r.getItem();
            System.out.println(MovieDatabase.getTitle(mID));
        }        
    }
}
