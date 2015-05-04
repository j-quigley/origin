package avis;


public class ItemBook extends Item {

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
	 * Setter of the property <tt>auteur</tt>
	 * @param auteur  The auteur to set.
	 * @uml.property  name="auteur"
	 */
	public void setAuteur(String auteur) {
		this.auteur = auteur;
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
	 * Setter of the property <tt>genre</tt>
	 * @param genre  The genre to set.
	 * @uml.property  name="genre"
	 */
	public void setGenre(String genre) {
		this.genre = genre;
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
	 * Setter of the property <tt>nbPages</tt>
	 * @param nbPages  The nbPages to set.
	 * @uml.property  name="nbPages"
	 */
	public void setNbPages(int nbPages) {
		this.nbPages = nbPages;
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

	/**
	 * Setter of the property <tt>titre</tt>
	 * @param titre  The titre to set.
	 * @uml.property  name="titre"
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}

}
