import java.util.*;

/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerWithFilters {
    
    public void printAverageRatings()
    {
        ThirdRatings tr = new ThirdRatings
            ("ratings_short.csv");
        
        System.out.println("number of raters: "+tr.getRaterSize());
        
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("number of movies in database: "+MovieDatabase.size());
        
        ArrayList<Rating> ratings = tr.getAverageRatings(1);
        System.out.println("number of movies with ratings: "+ratings.size());

        Collections.sort(ratings);
        for(Rating r : ratings)
        {
            System.out.println(r.getValue()+" "+r.getItem());
        }        
    }
    
    public void printAverageRatingsByYear()
    {
        ThirdRatings tr = new ThirdRatings
            ("ratings_short.csv");
        
        System.out.println("number of raters: "+tr.getRaterSize());
        
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("number of movies in database: "+MovieDatabase.size());
        
        ArrayList<Rating> ratings = 
            tr.getAverageRatingsByFilter(1, new YearAfterFilter(2000));
        System.out.println("Found "+ratings.size()+" movies");

        Collections.sort(ratings);
        for(Rating r : ratings)
        {
            System.out.println(r.getValue()+" "+MovieDatabase.getTitle(r.getItem()));
        }        
    }
    
    public void printAverageRatingsByGenre()
    {
        ThirdRatings tr = new ThirdRatings
            ("ratings_short.csv");
        
        System.out.println("number of raters: "+tr.getRaterSize());
        
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("number of movies in database: "+MovieDatabase.size());
        
        ArrayList<Rating> ratings = 
            tr.getAverageRatingsByFilter(1, new GenreFilter("Crime"));
        System.out.println("Found "+ratings.size()+" movies");

        Collections.sort(ratings);
        for(Rating r : ratings)
        {
            System.out.println(r.getValue()+" "+MovieDatabase.getTitle(r.getItem())+
                "\n\t"+ MovieDatabase.getGenres(r.getItem()));
        }        
    }
    
    public void printAverageRatingsByMinutes()
    {
        ThirdRatings tr = new ThirdRatings
            ("ratings_short.csv");
        
        System.out.println("number of raters: "+tr.getRaterSize());
        
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("number of movies in database: "+MovieDatabase.size());
        
        ArrayList<Rating> ratings = 
            tr.getAverageRatingsByFilter(1, new MinutesFilter(110, 170));
        System.out.println("Found "+ratings.size()+" movies");

        Collections.sort(ratings);
        for(Rating r : ratings)
        {
            System.out.println(r.getValue()+
                " Time: "+MovieDatabase.getMinutes(r.getItem())+
                " "+MovieDatabase.getTitle(r.getItem()));
        }        
    }
    
    public void printAverageRatingsByDirectors()
    {
        ThirdRatings tr = new ThirdRatings
            ("ratings_short.csv");
        
        System.out.println("number of raters: "+tr.getRaterSize());
        
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("number of movies in database: "+MovieDatabase.size());
        
        Filter f = new DirectorsFilter("Charles Chaplin,Michael Mann,Spike Jonze");
        ArrayList<Rating> ratings = 
            tr.getAverageRatingsByFilter(1, f);
        System.out.println("Found "+ratings.size()+" movies");

        Collections.sort(ratings);
        for(Rating r : ratings)
        {
            System.out.println(r.getValue()+" "+MovieDatabase.getTitle(r.getItem())+
                "\n\t"+ MovieDatabase.getDirector(r.getItem()));
        }        
    }
    
    public void printAverageRatingsByYearAfterAndGenre()
    {
        ThirdRatings tr = new ThirdRatings
            ("ratings_short.csv");
        
        System.out.println("number of raters: "+tr.getRaterSize());
        
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("number of movies in database: "+MovieDatabase.size());
        
        AllFilters af = new AllFilters();
        af.addFilter(new YearAfterFilter(1980));
        af.addFilter(new GenreFilter("Romance"));
        
        ArrayList<Rating> ratings = 
            tr.getAverageRatingsByFilter(1, af);
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
    
    public void printAverageRatingsByDirectorsAndMinutes()
    {
        ThirdRatings tr = new ThirdRatings
            ("ratings_short.csv");
        
        System.out.println("number of raters: "+tr.getRaterSize());
        
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("number of movies in database: "+MovieDatabase.size());
        
        AllFilters af = new AllFilters();
        af.addFilter(new DirectorsFilter("Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola"));
        af.addFilter(new MinutesFilter(30,170));
        
        ArrayList<Rating> ratings = 
            tr.getAverageRatingsByFilter(1, af);
        System.out.println("Found "+ratings.size()+" movies");

        Collections.sort(ratings);
        for(Rating r : ratings)
        {
            System.out.println(r.getValue()+
                " Time: "+MovieDatabase.getMinutes(r.getItem())+
                " "+MovieDatabase.getTitle(r.getItem())+
                "\n\t"+ MovieDatabase.getDirector(r.getItem()));
        }        
    }
}
