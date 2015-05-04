package avis;


public class ItemFilm extends Item {

	/**
	 * @uml.property  name="duree"
	 */
	private int duree;

	/**
	 * Getter of the property <tt>duree</tt>
	 * @return  Returns the duree.
	 * @uml.property  name="duree"
	 */
	public int getDuree() {
		return duree;
	}

	/**
	 * @uml.property  name="genre"
	 */
	private String genre;

	/**
	 * Getter of the property <tt>genre</tt>
	 * @return  Returns the genre.
	 * @uml.property  name="genre"
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * @uml.property  name="realisateur"
	 */
	private String realisateur;

	/**
	 * Getter of the property <tt>realisateur</tt>
	 * @return  Returns the realisateur.
	 * @uml.property  name="realisateur"
	 */
	public String getRealisateur() {
		return realisateur;
	}

	/**
	 * @uml.property  name="scenariste"
	 */
	private String scenariste;

	/**
	 * Getter of the property <tt>scenariste</tt>
	 * @return  Returns the scenariste.
	 * @uml.property  name="scenariste"
	 */
	public String getScenariste() {
		return scenariste;
	}

	/**
	 * @uml.property  name="titre"
	 */
	private String titre;

	/**
	 * Getter of the property <tt>titre</tt>
	 * @return  Returns the titre.
	 * @uml.property  name="titre"
	 */
	public String getTitre() {
		return titre;
	}

	public static boolean testBadEntry(String pseudo, String password, String titre, String genre, String realisateur, String scenariste, int duree){
		if(pseudo == null) return true;
		if(password == null) return true;
		if(titre == null) return true;
		if(genre == null) return true;
		if(realisateur == null) return true;
		if(scenariste == null) return true;
		if(pseudo.trim().length() < 1) return true;
		if(password.trim().length() < 4) return true;
		if(titre.length() < 1) return true;
		if(duree < 1) return true;
		return false;
	}
}	
