package practica3b.conector;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import java.util.logging.Level;
import java.util.logging.Logger;
import practica3b.representacion.Movie;
import practica3b.representacion.Rating;
import practica3b.representacion.User;

/**
 * Utility to parse the MovieLens data base 2009 edition
 * @author Juan A. Recio-Garcia
 * @version 1.0
 */
public class MoviesDatabaseCreator {

    private HashMap<Integer,Movie> movies;
    private ArrayList<Rating> ratings;
    private ArrayList<User> users;
   
    public MoviesDatabaseCreator(){
            movies = new HashMap<Integer,Movie>();
            ratings = new ArrayList<Rating>();
            users = new ArrayList<User>();
    }

    public void parseUsers(){
        BufferedReader entrada;
        String linea;
        try {
            entrada = new BufferedReader(new FileReader(new File("usuarios.dat")));
            while ((linea = entrada.readLine()) != null) {
                User u = User.fromString(linea);
                users.add(u);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MoviesDatabaseCreator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
                Logger.getLogger(MoviesDatabaseCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void parseMovies(int maxyearold){
            movies.clear();
            String line = null;
            try {
                    Scanner scanner = new Scanner(new File("movies.dat"), "UTF-8");
                    while(scanner.hasNextLine())
                    {
                            line = scanner.nextLine();
                            Movie m = Movie.fromString(line);
                            if(m.getYear()>=maxyearold)
                                    movies.put(m.getId(),m);
                    }
                    scanner.close();
            } catch (Exception e) {
                    System.err.println(line);
                    e.printStackTrace();
            }
            System.out.println(movies.size()+" movies loaded.");
    }

    public void parseRatings(int maxUsers){
            ratings.clear();
            String line = null;
            try {
                    Scanner scanner = new Scanner(new File("ratings.dat"), "UTF-8");
                    boolean _continue = true;
                    while(scanner.hasNextLine() && _continue)
                    {
                            line = scanner.nextLine();
                            Integer user = Integer.valueOf(line.split("::")[0]);
                            if(user>maxUsers)
                                    _continue = false;
                            else
                            {
                                    Rating r = Rating.fromString(line);
                                    ratings.add(r);
                            }
                    }
                    scanner.close();
            } catch (Exception e) {
                    System.err.println(line);
                    e.printStackTrace();
            }
            System.out.println(ratings.size()+" ratings loaded.");
    }		


    public void export(String filename)
    {
            try{
                    BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filename), false));

                    String sep = "|";
                    for(Rating r: ratings)
                    {
                            Movie m = movies.get(r.getMovie());
                            if(m==null)
                                    continue;

                            String line = r.getUser().toString()+sep+m.toString(sep)+sep+r.toString(sep);
                            bw.write(line);
                            bw.newLine();
                    }

                    bw.close();
            }catch(Exception e)
            {
                    System.err.println(e);
            }
            System.out.println("Done.");
    }
    
    public static void main(String[] args) {
            MoviesDatabaseCreator creator = new MoviesDatabaseCreator();
            creator.parseMovies(1990);
            creator.parseRatings(1000);
            creator.export("ratingsPeliculas.txt");
    }
    
    public void setMovies(HashMap<Integer, Movie> movies) {
        this.movies = movies;
    }

    public void setRatings(ArrayList<Rating> ratings) {
        this.ratings = ratings;
    }
    
    public HashMap<Integer, Movie> getMovies() {
        return movies;
    }

    public ArrayList<Rating> getRatings() {
        return ratings;
    }

}
