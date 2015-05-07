package avis;


public class Review {

	/** 
	 * @uml.property name="member"
	 * @uml.associationEnd multiplicity="(1 1)" inverse="review:avis.Member"
	 */
	private Member author = null;

	/** 
	 * Getter of the property <tt>member</tt>
	 * @return  Returns the member.
	 * @uml.property  name="member"
	 */
	public Member getMember() {
		return author;
	}


	/**
	 * @uml.property  name="note"
	 */
	private float note;

	/**
	 * Getter of the property <tt>note</tt>
	 * @return  Returns the note.
	 * @uml.property  name="note"
	 */
	public float getNote() {
		return note;
	}

	/**
	 * @uml.property  name="commentaire"
	 */
	private String commentaire;

	/**
	 * Getter of the property <tt>commentaire</tt>
	 * @return  Returns the commentaire.
	 * @uml.property  name="commentaire"
	 */
	public String getCommentaire() {
		return commentaire;
	}
		
		/**
		 */
	public Review(Member author_, float note_, String commentaire_){
		author = author_;
		note=note_;
		commentaire=commentaire_; 
	}
	
	public static boolean testBadEntry(String pseudo, String password,String titre, float note, String commentaire){
		if(pseudo == null) return true;
		if(password == null) return true;
		if(titre == null) return true;
		if(commentaire == null) return true;
		if(pseudo.trim().length() < 1) return true;
		if(password.trim().length() < 4) return true;
		if(titre.trim().length() < 1) return true;
		if((note < 0) || (note > 5)) return true;
		return false;
	}

}
