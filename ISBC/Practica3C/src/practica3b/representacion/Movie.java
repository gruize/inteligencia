package practica3b.representacion;


/**
 * Bean that represents a movie.
 * @author Juan A. Recio-Garcia
 * @version 1.0
 */
public class Movie {
	
	public static Movie fromString(String line)	{
		String[] fields = line.split("::");
		
		Movie movie = new Movie();
		movie.setId(Integer.valueOf(fields[0]));
		
		String[] aux = fields[1].split("\\(|\\)");

		movie.setTitle(aux[0].trim());
	
		if(aux.length==2)
			movie.setYear(Integer.valueOf(aux[1]));
		else
		{
			movie.setOtherTitle(aux[1].trim());
			movie.setYear(Integer.valueOf(aux[aux.length-1]));
		}
			
		
		String[] genres = fields[2].split("\\|");
		for(String g : genres)
		{
			if(!movie.genreAction) movie.setGenreAction(g.equals("Action"));
			if(!movie.genreAdventure) movie.setGenreAdventure(g.equals("Adventure"));
			if(!movie.genreAnimation) movie.setGenreAnimation(g.equals("Animation"));
			if(!movie.genreChildren) movie.setGenreChildren(g.equals("Children's"));
			if(!movie.genreComedy) movie.setGenreComedy(g.equals("Comedy"));
			if(!movie.genreCrime) movie.setGenreCrime(g.equals("Crime"));
			if(!movie.genreDocumentary) movie.setGenreDocumentary(g.equals("Documentary"));
			if(!movie.genreDrama) movie.setGenreDrama(g.equals("Drama"));
			if(!movie.genreFantasy) movie.setGenreFantasy(g.equals("Fantasy"));
			if(!movie.genreFilmNoir) movie.setGenreFilmNoir(g.equals("Film-Noir"));
			if(!movie.genreHorror) movie.setGenreHorror(g.equals("Horror"));
			if(!movie.genreMusical) movie.setGenreMusical(g.equals("Musical"));
			if(!movie.genreMystery) movie.setGenreMystery(g.equals("Mystery"));
			if(!movie.genreRomance) movie.setGenreRomance(g.equals("Romance"));
			if(!movie.genreSciFi) movie.setGenreSciFi(g.equals("Sci-Fi"));
			if(!movie.genreThriller) movie.setGenreThriller(g.equals("Thriller"));
			if(!movie.genreWar) movie.setGenreWar(g.equals("War"));
			if(!movie.genreWestern) movie.setGenreWestern(g.equals("Western"));

		}
		return movie;
	}
	
	
	
	
	
    Integer id;
    String title;
    String otherTitle;
    Integer year;
    
    Boolean genreAction = false;
    Boolean genreAdventure = false;
    Boolean genreAnimation = false;
    Boolean genreChildren = false;
    Boolean genreComedy = false;
    Boolean genreCrime = false;
    Boolean genreDocumentary = false;
    Boolean genreDrama = false;
    Boolean genreFantasy = false;
    Boolean genreFilmNoir = false;
    Boolean genreHorror = false;
    Boolean genreMusical = false;
    Boolean genreMystery = false;
    Boolean genreRomance = false;
    Boolean genreSciFi = false;
    Boolean genreThriller = false;
    Boolean genreWar = false;
    Boolean genreWestern = false;
    
    
    public String toString(String sep)
    {
    	return id+sep+title+sep+year+sep+getGenreAction()+sep+getGenreAdventure()+sep+getGenreAnimation()+sep+getGenreChildren()+sep+getGenreComedy()+sep+getGenreCrime()+sep+getGenreDocumentary()+sep+getGenreDrama()+sep+getGenreFantasy()+sep+getGenreFilmNoir()+sep+getGenreHorror()+sep+getGenreMusical()+sep+getGenreMystery()+sep+getGenreRomance()+sep+getGenreSciFi()+sep+getGenreThriller()+sep+getGenreWar()+sep+getGenreWestern();
    }
    
    public String toString()
    {
	String res = id+","+title+","+year+","+otherTitle;
	if(genreAction)
	    res+= ",Action";
	if(genreAdventure)
	    res+= ",Adventure";
	if(genreAnimation)
	    res+= ",Animation";
	if(genreChildren)
	    res+= ",Children";
	if(genreComedy)
	    res+= ",Comedy";
	if(genreCrime)
	    res+= ",Crime";
	if(genreDocumentary)
	    res+= ",Documentary";
	if(genreDrama)
	    res+= ",Drama";
	if(genreFantasy)
	    res+= ",Fantasy";
	if(genreFilmNoir)
	    res+= ",FilmNoir";
	if(genreHorror)
	    res+= ",Horror";
	if(genreMusical)
	    res+= ",Musical";
	if(genreMystery)
	    res+= ",Mystery";
	if(genreRomance)
	    res+= ",Romance";
	if(genreSciFi)
	    res+= ",SciFi";
	if(genreThriller)
	    res+= ",Thriller";
	if(genreWar)
	    res+= ",War";
	if(genreWestern)
	    res+= ",Western";
	
	return res;
    }
    
    
    
    public String getOtherTitle() {
		return otherTitle;
	}



	public void setOtherTitle(String otherTitle) {
		this.otherTitle = otherTitle;
	}



	/**
     * @return Returns the genreAction.
     */
    public Boolean getGenreAction()
    {
        return genreAction;
    }
    /**
     * @param genreAction The genreAction to set.
     */
    public void setGenreAction(Boolean genreAction)
    {
        this.genreAction = genreAction;
    }
    /**
     * @return Returns the genreAdventure.
     */
    public Boolean getGenreAdventure()
    {
        return genreAdventure;
    }
    /**
     * @param genreAdventure The genreAdventure to set.
     */
    public void setGenreAdventure(Boolean genreAdventure)
    {
        this.genreAdventure = genreAdventure;
    }
    /**
     * @return Returns the genreAnimation.
     */
    public Boolean getGenreAnimation()
    {
        return genreAnimation;
    }
    /**
     * @param genreAnimation The genreAnimation to set.
     */
    public void setGenreAnimation(Boolean genreAnimation)
    {
        this.genreAnimation = genreAnimation;
    }
    /**
     * @return Returns the genreChildren.
     */
    public Boolean getGenreChildren()
    {
        return genreChildren;
    }
    /**
     * @param genreChildren The genreChildren to set.
     */
    public void setGenreChildren(Boolean genreChildren)
    {
        this.genreChildren = genreChildren;
    }
    /**
     * @return Returns the genreComedy.
     */
    public Boolean getGenreComedy()
    {
        return genreComedy;
    }
    /**
     * @param genreComedy The genreComedy to set.
     */
    public void setGenreComedy(Boolean genreComedy)
    {
        this.genreComedy = genreComedy;
    }
    /**
     * @return Returns the genreCrime.
     */
    public Boolean getGenreCrime()
    {
        return genreCrime;
    }
    /**
     * @param genreCrime The genreCrime to set.
     */
    public void setGenreCrime(Boolean genreCrime)
    {
        this.genreCrime = genreCrime;
    }
    /**
     * @return Returns the genreDocumentary.
     */
    public Boolean getGenreDocumentary()
    {
        return genreDocumentary;
    }
    /**
     * @param genreDocumentary The genreDocumentary to set.
     */
    public void setGenreDocumentary(Boolean genreDocumentary)
    {
        this.genreDocumentary = genreDocumentary;
    }
    /**
     * @return Returns the genreDrama.
     */
    public Boolean getGenreDrama()
    {
        return genreDrama;
    }
    /**
     * @param genreDrama The genreDrama to set.
     */
    public void setGenreDrama(Boolean genreDrama)
    {
        this.genreDrama = genreDrama;
    }
    /**
     * @return Returns the genreFantasy.
     */
    public Boolean getGenreFantasy()
    {
        return genreFantasy;
    }
    /**
     * @param genreFantasy The genreFantasy to set.
     */
    public void setGenreFantasy(Boolean genreFantasy)
    {
        this.genreFantasy = genreFantasy;
    }
    /**
     * @return Returns the genreFilmNoir.
     */
    public Boolean getGenreFilmNoir()
    {
        return genreFilmNoir;
    }
    /**
     * @param genreFilmNoir The genreFilmNoir to set.
     */
    public void setGenreFilmNoir(Boolean genreFilmNoir)
    {
        this.genreFilmNoir = genreFilmNoir;
    }
    /**
     * @return Returns the genreHorror.
     */
    public Boolean getGenreHorror()
    {
        return genreHorror;
    }
    /**
     * @param genreHorror The genreHorror to set.
     */
    public void setGenreHorror(Boolean genreHorror)
    {
        this.genreHorror = genreHorror;
    }
    /**
     * @return Returns the genreMusical.
     */
    public Boolean getGenreMusical()
    {
        return genreMusical;
    }
    /**
     * @param genreMusical The genreMusical to set.
     */
    public void setGenreMusical(Boolean genreMusical)
    {
        this.genreMusical = genreMusical;
    }
    /**
     * @return Returns the genreMystery.
     */
    public Boolean getGenreMystery()
    {
        return genreMystery;
    }
    /**
     * @param genreMystery The genreMystery to set.
     */
    public void setGenreMystery(Boolean genreMystery)
    {
        this.genreMystery = genreMystery;
    }
    /**
     * @return Returns the genreRomance.
     */
    public Boolean getGenreRomance()
    {
        return genreRomance;
    }
    /**
     * @param genreRomance The genreRomance to set.
     */
    public void setGenreRomance(Boolean genreRomance)
    {
        this.genreRomance = genreRomance;
    }
    /**
     * @return Returns the genreSciFi.
     */
    public Boolean getGenreSciFi()
    {
        return genreSciFi;
    }
    /**
     * @param genreSciFi The genreSciFi to set.
     */
    public void setGenreSciFi(Boolean genreSciFi)
    {
        this.genreSciFi = genreSciFi;
    }
    /**
     * @return Returns the genreThriller.
     */
    public Boolean getGenreThriller()
    {
        return genreThriller;
    }
    /**
     * @param genreThriller The genreThriller to set.
     */
    public void setGenreThriller(Boolean genreThriller)
    {
        this.genreThriller = genreThriller;
    }

    /**
     * @return Returns the genreWar.
     */
    public Boolean getGenreWar()
    {
        return genreWar;
    }
    /**
     * @param genreWar The genreWar to set.
     */
    public void setGenreWar(Boolean genreWar)
    {
        this.genreWar = genreWar;
    }
    /**
     * @return Returns the genreWestern.
     */
    public Boolean getGenreWestern()
    {
        return genreWestern;
    }
    /**
     * @param genreWestern The genreWestern to set.
     */
    public void setGenreWestern(Boolean genreWestern)
    {
        this.genreWestern = genreWestern;
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
     * @return Returns the year.
     */
    public Integer getYear()
    {
        return year;
    }
    /**
     * @param year The year to set.
     */
    public void setYear(Integer releaseDate)
    {
        this.year = releaseDate;
    }
    /**
     * @return Returns the title.
     */
    public String getTitle()
    {
        return title;
    }
    /**
     * @param title The title to set.
     */
    public void setTitle(String title)
    {
        this.title = title;
    } 
}
