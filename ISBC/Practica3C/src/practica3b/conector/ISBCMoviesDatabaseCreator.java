package practica3b.conector;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import practica3b.representacion.Movie;
import practica3b.representacion.Rating;

/**
 * Utility to parse the MovieLens data base 2009 edition
 * @author Juan A. Recio-Garcia
 * @version 1.0
 */
public class ISBCMoviesDatabaseCreator {

	HashMap<Integer,Movie> movies;
	ArrayList<Rating> ratings;
	
	public ISBCMoviesDatabaseCreator()
	{
		movies = new HashMap<Integer,Movie>();
		ratings = new ArrayList<Rating>();
	}
	
	
	public void parseMovies(int maxyearold)
	{
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
	
	public void parseRatings(String filename)
	{
		ratings.clear();
		String line = null;
		try {
			Scanner scanner = new Scanner(new File(filename));
			
			ArrayList<Integer> ratedMovies = obtainRatedMovies(scanner.nextLine());
			int id=0;
			while(scanner.hasNextLine())
			{
				line = scanner.nextLine();

				String[] rats = line.split("\t");
				int userID = Integer.valueOf(rats[0]);

				
				for(int i=1;i<rats.length;i++)
				{
					float r = convert(rats[i]);
					if(r>0)
						ratings.add(new Rating(id++,userID,ratedMovies.get(i-1),r));
				}
			
			}
			scanner.close();
		} catch (Exception e) {
			System.err.println(line);
			e.printStackTrace();
		}
		System.out.println(ratings.size()+" ratings loaded.");
	}		

	
	private float convert(String code) {
		if (code.equals("b")) return 0.5f;
		else if (code.equals("c")) return 1f;
		else if (code.equals("d")) return 1.5f;
		else if (code.equals("e")) return 2f;
		else if (code.equals("f")) return 2.5f;
		else if (code.equals("g")) return 3f;
		else if (code.equals("h")) return 3.5f;
		else if (code.equals("i")) return 4f;
		else if (code.equals("j")) return 4.5f;
		else if (code.equals("k")) return 5f;
		else
			return 0;
	}


	private ArrayList<Integer> obtainRatedMovies(String string) {

		ArrayList<Integer> res = new ArrayList<Integer>();
		
		String[] movs = string.split("\t");
		for(int i=1; i<movs.length;i++)
			res.add(Integer.valueOf(movs[i]));		
		
		return res;
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
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ISBCMoviesDatabaseCreator creator = new ISBCMoviesDatabaseCreator();
		creator.parseMovies(1990);
		creator.parseRatings("ISBC_Apr_2.txt");
		creator.export("ratingsPeliculasAlumnos.txt");
		

	}

}
