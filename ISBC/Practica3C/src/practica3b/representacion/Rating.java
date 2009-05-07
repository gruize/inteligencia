package practica3b.representacion;


/**
 * Bean that represents a rating
 * @author Juan A. Recio-Garcia
 * @version 1.0
 *
 */
public class Rating {
    Integer id;
    Integer user;
    Integer movie;
    Float rating;

    private static int _id = 0;
    
    public static Rating fromString(String line)	{
            String[] fields = line.split("::");

            Rating rating = new Rating();
            rating.setId(_id++);
            rating.setUser(Integer.valueOf(fields[0]));
            rating.setMovie(Integer.valueOf(fields[1]));
            rating.setRating(Float.valueOf(fields[2]));

            return rating;
    }

    public Rating(){

    }



     /**
     * @param id
     * @param user
     * @param movie
     * @param rating
     */
    public Rating(Integer id, Integer user, Integer movie, Float rating) {
            super();
            this.id = id;
            this.user = user;
            this.movie = movie;
            this.rating = rating;
    }

	public String toString()
    {
	return id+","+rating;
    }
    
    /**
     * @return Returns the id.
     */
    public Integer getId()
    {
        return id;
    }
    /**
     * @param id The id to set.
     */
    public void setId(Integer id)
    {
        this.id = id;
    }
    /**
     * @return Returns the rating.
     */
    public Float getRating()
    {
        return rating;
    }
    /**
     * @param rating The rating to set.
     */
    public void setRating(Float rating)
    {
        this.rating = rating;
    }

	public Integer getMovie() {
		return movie;
	}

	public void setMovie(Integer movie) {
		this.movie = movie;
	}

	public Integer getUser() {
		return user;
	}

	public void setUser(Integer user) {
		this.user = user;
	}

	public String toString(String sep) {
		return id.toString()+sep+rating.toString();
	}

    
}
