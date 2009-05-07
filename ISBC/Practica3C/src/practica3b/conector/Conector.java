package practica3b.conector;

public class Conector {
    
    private static MoviesDatabaseCreator instancia = null;

    public static MoviesDatabaseCreator getInstancia(){
        if ( instancia  == null){
            instancia = new MoviesDatabaseCreator();
            instancia.parseMovies(1950);
            instancia.parseRatings(3000);
        }
        return instancia;
    }
    
}
