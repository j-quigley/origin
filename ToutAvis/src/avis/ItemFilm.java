package avis;


public class ItemFilm extends Item {

	/**
	 * @uml.property  name="duree"
	 */
	private int duree;
	//TEST
	
	

	public ItemFilm(Member author_, String titre_, String genre_, String realisateur_, String scenariste_, int duree_) {
		super(author_);
		titre = titre_;
		genre = genre_;
		realisateur = realisateur_;
		scenariste = scenariste_;
		duree = duree_;
	}


	/**
	 * @uml.property  name="genre"
	 */
	private String genre;

	public String getGenre(){
		return genre;
	}

	/**
	 * @uml.property  name="realisateur"
	 */
	private String realisateur;


	/**
	 * @uml.property  name="scenariste"
	 */
	private String scenariste;



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
	
	public String toString(){
		String result = "Film :"+titre+" genre : "+genre+" realisateur : "+realisateur+" scenariste : "+scenariste+" duree : "+duree+" moyenne des notes : "+getMoyenne()+"\n";
		result=result+("Createur : "+author.getPseudo()+"\n");
		result=result+("Liste des commentaires : \n");
		for(Review r : reviews){
			result=result+(r.toString()+"\n");
		}
		return result;
	}

	public static boolean testBadEntry(String pseudo, String password, String titre, String genre, String realisateur, String scenariste, int duree){
		if(pseudo == null) return true;
		if(password == null) return true;
		if(titre == null) return true;
		if(genre == null) return true;
		if(realisateur == null) return true;
		if(scenariste == null) return true;
		if(pseudo.trim().length() < 1) return true;
		if(genre.trim().length() < 1) return true;
		if(password.trim().length() < 4) return true;
		if(titre.trim().length() < 1) return true;
		if(realisateur.trim().length() < 1) return true;
		if(scenariste.trim().length() < 1) return true;
		if(duree < 1) return true;
		return false;
	}
}	
