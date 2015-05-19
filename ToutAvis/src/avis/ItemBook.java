package avis;


public class ItemBook extends Item {

	public ItemBook(Member author_, String titre_, String auteur_, String genre_, int nbPages_) {
		super(author_);
		titre = titre_;
		auteur = auteur_;
		genre = genre_;
		nbPages = nbPages_;
	}

	/**
	 * @uml.property  name="auteur"
	 */
	private String auteur;

	/**
	 * Getter of the property <tt>auteur</tt>
	 * @return  Returns the auteur.
	 * @uml.property  name="auteur"
	 */
	public String getAuteur() {
		return auteur;
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
	 * @uml.property  name="nbPages"
	 */
	private int nbPages;

	/**
	 * Getter of the property <tt>nbPages</tt>
	 * @return  Returns the nbPages.
	 * @uml.property  name="nbPages"
	 */
	public int getNbPages() {
		return nbPages;
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
	
	public String toString(){
		String result = "Livre :"+titre+" genre : "+genre+" auteur : "+auteur+" nombres de pages : "+nbPages+" moyenne des notes : "+getMoyenne()+"\n";
		result=result+("Createur : "+author.getPseudo()+"\n");
		result=result+("Liste des commentaires : \n");
		for(Review r : reviews){
			result=result+(r.toString()+"\n");
		}
		return result;
	}
	
	public static boolean testBadEntry(String pseudo, String password, String titre, String genre, String auteur, int nbPages){
		if(pseudo == null) return true;
		if(password == null) return true;
		if(titre == null) return true;
		if(genre == null) return true;
		if(auteur == null) return true;
		if(pseudo.trim().length() < 1) return true;
		if(password.trim().length() < 4) return true;
		if(titre.trim().length() < 1) return true;
		if(nbPages <= 0) return true;
		return false;
	}


}
